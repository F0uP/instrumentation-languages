/*
* generated by Xtext
*/
package de.cau.cs.se.instrumentation.al.ui.labeling

import com.google.inject.Inject
import de.cau.cs.se.instrumentation.al.applicationLang.ContainerNode
import de.cau.cs.se.instrumantation.model.structure.NamedElement
import de.cau.cs.se.instrumentation.al.applicationLang.Aspect
import de.cau.cs.se.instrumentation.al.applicationLang.WildcardNode
import de.cau.cs.se.instrumentation.al.applicationLang.SubPathNode
import de.cau.cs.se.instrumentation.al.applicationLang.Collector
import de.cau.cs.se.instrumentation.al.applicationLang.LocationQuery
import de.cau.cs.se.instrumentation.al.applicationLang.Query

/**
 * Provides labels for a EObjects.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#labelProvider
 */
class ApplicationLangLabelProvider extends org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider {

	@Inject
	new(org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}
	
	def text(Aspect e) { 'aspect' }
	
	def text(ContainerNode e) {
		(e.container as NamedElement).name
	}
	
	def text (WildcardNode e) { '*' }
	
	def text (SubPathNode e) { '**' }
	
	def text(Query e) { 'query' }
	def text(LocationQuery e) { 'path' }
	 
	def text (Collector e) { e.type.name + " " + e.insertionPoint.name.toLowerCase }
	
	// Labels and icons can be computed like this:
	
//	def text(Greeting ele) {
//		'A greeting to ' + ele.name
//	}
//
//	def image(Greeting ele) {
//		'Greeting.gif'
//	}
}
