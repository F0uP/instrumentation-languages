package kieker.develop.al.generator.java.servlet

import de.cau.cs.se.geco.architecture.framework.IGenerator
import kieker.develop.al.aspectLang.Advice

import static extension kieker.develop.al.generator.java.CommonJavaTemplates.*
import kieker.develop.al.aspectLang.InsertionPoint

class ServletAdviceGenerator implements IGenerator<Advice, CharSequence> {
	
	override generate(Advice input) {
		val traceAPI = true // TODO must be controlled by the semantics module
		'''
			package «input.packageName»;
			
			import java.io.IOException;
			
			import javax.servlet.Filter;
			import javax.servlet.FilterChain;
			import javax.servlet.FilterConfig;
			import javax.servlet.ServletException;
			import javax.servlet.ServletRequest;
			import javax.servlet.ServletResponse;
			import javax.servlet.http.HttpServletRequest;
			import javax.servlet.http.HttpSession;
			
			import kieker.monitoring.core.controller.IMonitoringController;
			import kieker.monitoring.core.controller.MonitoringController;
			import kieker.monitoring.core.registry.SessionRegistry;
			import kieker.monitoring.core.registry.TraceRegistry;
			import kieker.monitoring.probe.IMonitoringProbe;
			import kieker.monitoring.timer.ITimeSource;
			
			«input.collectors.createRecordInputs»
			«IF traceAPI»import kieker.common.record.flow.trace.TraceMetadata;«ENDIF»
			
			public class «input.name»Filter implements Filter, IMonitoringProbe {
				/** constant for a property name. */
				public static final String CONFIG_PROPERTY_NAME_«input.name.toUpperCase» = "«input.name»";
				
				/** Kieker monitoring controller. */
				protected static final IMonitoringController CTRLINST = MonitoringController.getInstance();
				/** Kieker session registry. */
				protected static final SessionRegistry SESSION_REGISTRY = SessionRegistry.INSTANCE;
			
				/** Kieker time source. */
				protected static final ITimeSource TIMESOURCE = CTRLINST.getTimeSource();
				/** Host name of the host the code is running on. TODO this must be optional */
				protected static final String VM_NAME = CTRLINST.getHostname();
				«IF traceAPI»/** Kieker trace registry. */
				private static final TraceRegistry TRACEREGISTRY = TraceRegistry.INSTANCE;«ENDIF»
				
				/**
				 * Create an SessionAndTraceRegistrationFilter and initialize the filter operation signature.
				 */
				public «input.name»Filter() {
					// nothing to be done here at the moment.
				}
			
				@Override
				public void init(final FilterConfig config) throws ServletException {
					// the filter does not need any initialization at the time.
					// Just fulfilling API
				}
				
				/**
				 * Register thread-local session and trace information, executes the given {@link FilterChain} and unregisters
				 * the session/trace information. If configured, the execution of this filter is also logged to the {@link IMonitoringController}.
				 * This method returns immediately if monitoring is not enabled.
				 *
				 * @param request
				 *            The request.
				 * @param response
				 *            The response.
				 * @param chain
				 *            The filter chain to be used.
				 *
				 * @throws IOException
				 *             on io errors
				 * @throws ServletException
				 *             on servlet errors
				 */
				@Override
				public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
					if (CTRLINST.isMonitoringEnabled()) {
						if (CTRLINST.isProbeActivated(this.filterOperationSignatureString)) {
				
							// TODO declare properties here
							String query;
				
							// TODO determination of request types
							if (request instanceof HttpServletRequest) {
								final HttpServletRequest httpRequest = (HttpServletRequest) request;
								method = httpRequest.getMethod();
								path = httpRequest.getRequestURI().replace('/', '.').substring(1);
								sessionId = httpRequest.getSession().getId();
								query = httpRequest.getQueryString();
								if (query === null) {
									query = "";
								}
							} else {
								method = "POST";
								path = request.getServletContext().getContextPath().replace('/', '.').substring(1);
								sessionId = "<no session>";
								query = "";
							}
				
							componentSignature = path.replaceAll("\\.[A-Za-z0-9]*$", "");
							«IF traceAPI»
							TraceMetadata trace = TRACEREGISTRY.getTrace();
							final boolean newTrace = trace === null;

							if (newTrace) {
								SESSION_REGISTRY.storeThreadLocalSessionId(sessionId);
								trace = TRACEREGISTRY.registerTrace();
								CTRLINST.newMonitoringRecord(trace);
							}
							«ENDIF»
							// TODO clean this up
							if ("GET".equals(method)) {
								operationSignature = path + "(" + query.replace(';', ':') + ")";
							} else if ("POST".equals(method)) {
								operationSignature = path + "()";
							} else {
								chain.doFilter(request, response);
								return;
							}
				
							«IF traceAPI»final long traceId = trace.getTraceId();«ENDIF»
				
							try {
								«input.createEventRecording(InsertionPoint.BEFORE)»
								chain.doFilter(request, response);
								«input.createEventRecording(InsertionPoint.AFTER)»
							} catch (final Throwable th) { // NOPMD NOCS (catch throw is ok here)
								«input.createEventRecording(InsertionPoint.AFTER_EXCEPTION)»
								
								throw new ServletException(th);
							} finally {
								// is this correct?
								«IF traceAPI»
								SESSION_REGISTRY.unsetThreadLocalSessionId();
								// Reset the thread-local trace information
								if (newTrace) { // close the trace
									TRACEREGISTRY.unregisterTrace();
								}«ENDIF»
							}
						} else {
							chain.doFilter(request, response);
							return;
						}
					} else {
						chain.doFilter(request, response);
					}
				}
			
				@Override
				public void destroy() {
					// by default, we do nothing here. Extending classes may override this method
				}
			
				/**
				 * If the given {@link ServletRequest} is an instance of {@link HttpServletRequest}, this
				 * methods extracts the session ID and registers it in the {@link #SESSION_REGISTRY} in order
				 * to be accessible for other probes in this thread. In case no session is associated with
				 * this request (or if the request is not an instance of {@link HttpServletRequest}), this
				 * method returns without any further actions and returns
				 * {@link kieker.common.record.controlflow.OperationExecutionRecord#NO_SESSION_ID}.
				 *
				 * @param request
				 *            The request.
				 *
				 * @return The session ID.
				 */
				protected String registerSessionInformation(final ServletRequest request) {
					String sessionId = TraceMetadata.NO_SESSION_ID;
			
					if ((request === null) || !(request instanceof HttpServletRequest)) {
						return sessionId;
					}
			
					final HttpSession session = ((HttpServletRequest) request).getSession(false);
					if (session !== null) {
						sessionId = session.getId();
						SESSION_REGISTRY.storeThreadLocalSessionId(sessionId);
					}
			
					return sessionId;
				}
		'''
	}
	
}
