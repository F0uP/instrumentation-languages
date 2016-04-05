/*
 * generated by Xtext
 */
package kieker.develop.rl;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.ISetup;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Generated from StandaloneSetup.xpt!
 */
@SuppressWarnings("all")
public class RecordLangStandaloneSetupGenerated implements ISetup {

	@Override
	public Injector createInjectorAndDoEMFRegistration() {
		org.eclipse.xtext.common.TerminalsStandaloneSetup.doSetup();

		final Injector injector = this.createInjector();
		this.register(injector);
		return injector;
	}

	public Injector createInjector() {
		return Guice.createInjector(new kieker.develop.rl.RecordLangRuntimeModule());
	}

	public void register(final Injector injector) {
		if (!EPackage.Registry.INSTANCE.containsKey("http://www.cau.de/cs/se/instrumentation/rl/RecordLang")) {
			EPackage.Registry.INSTANCE.put("http://www.cau.de/cs/se/instrumentation/rl/RecordLang", kieker.develop.rl.recordLang.RecordLangPackage.eINSTANCE);
		}

		final org.eclipse.xtext.resource.IResourceFactory resourceFactory = injector.getInstance(org.eclipse.xtext.resource.IResourceFactory.class);
		final org.eclipse.xtext.resource.IResourceServiceProvider serviceProvider = injector.getInstance(org.eclipse.xtext.resource.IResourceServiceProvider.class);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("irl", resourceFactory);
		org.eclipse.xtext.resource.IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap().put("irl", serviceProvider);

	}
}
