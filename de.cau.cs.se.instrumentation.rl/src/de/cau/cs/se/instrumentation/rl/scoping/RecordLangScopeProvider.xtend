/*
 * generated by Xtext
 */
package de.cau.cs.se.instrumentation.rl.scoping

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EGenericType
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.Scopes
import org.eclipse.xtext.scoping.impl.FilteringScope

import com.google.common.base.Predicate

import de.cau.cs.se.instrumentation.rl.recordLang.Classifier;
import de.cau.cs.se.instrumentation.rl.recordLang.Package;
import de.cau.cs.se.instrumentation.rl.recordLang.Property;
import de.cau.cs.se.instrumentation.rl.recordLang.ReferenceProperty;
import java.util.Collection
import java.util.List
import de.cau.cs.se.instrumentation.rl.recordLang.Type
import de.cau.cs.se.instrumentation.rl.validation.PropertyEvaluation

/**
 * This class contains custom scoping description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation.html#scoping
 * on how and when to use it 
 *
 */
class RecordLangScopeProvider extends org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider {

	/**
	 * Find scope for the package property in the Package rule.
	 * 
	 * @param context
	 *            The Package-object of the resulting model.
	 * @param reference
	 *            The EReference-reference object of the AST.
	 * @return The scope for the package attribute.
	 */
	def IScope scope_Package_package(Package context, EReference reference) {
		val IScope result = new FilteringScope(delegateGetScope(context, reference),new URIPredicate())
		return result
	}
	
	/**
	 * Build a scope containing all properties.
	 */
	def IScope scope_Property_referTo(Property property, EReference reference) {
		return Scopes::scopeFor(PropertyEvaluation::collectAllProperties(property.eContainer() as Type))
	}
			
	/**
	 * 
	 * @param classifier
	 * @param reference
	 * @return
	 */
	def IScope scope_Classifier_class(Classifier context, EReference reference) {
		return Scopes::scopeFor(EcoreUtil2::getAllContentsOfType(
				context.getPackage().getPackage(), typeof(EClassifier)))
	}

	/**
	 * 
	 */
	def IScope scope_ReferenceProperty_ref(ReferenceProperty property, EReference reference) {
		// Check if the parent is a property or a nested property reference.
		switch property.eContainer() {
			// For properties you can directly access the EClassifier via getClass_.
			Property : return Scopes::scopeFor(getAllExternalProperties((property.eContainer() as Property).type.class_, typeof(EStructuralFeature)))
			/*
			 * If the present property is nested in another property, then the type for the parent
			 * property can be found in the structural feature, which is located in the ref
			 * attribute of the parent.
			 */
			ReferenceProperty : {
				val parent = (property.eContainer() as ReferenceProperty).ref
				// Also, only EReferences refer to classes and can therefore have properties
				if (parent instanceof EReference) {
					return Scopes::scopeFor(getAllExternalProperties((parent as EReference).getEReferenceType(),typeof(EStructuralFeature)))
				} else {
					// Attributes as such do not have properties.
					return null
				}
			}
			// illegal type
			default: return null
		}
	}
		
	/**
	 * Internal function to collect all properties of a imported classifier.
	 */
	def Collection<EStructuralFeature> getAllExternalProperties(EClassifier classifier,
	        Class<EStructuralFeature> type) {
		val List<EStructuralFeature> result = EcoreUtil2::getAllContentsOfType(classifier, type)
		if (classifier instanceof EClass) {
			for (EGenericType generic : (classifier as EClass).getEGenericSuperTypes() ) {
				result.addAll(getAllExternalProperties(generic.getEClassifier(), type))
			}
		}
		return result
	}

}

// this is most likely not necessary in Xtend and can be merged some how
class URIPredicate implements Predicate<IEObjectDescription> {

	override boolean apply(IEObjectDescription input) {
		val String isNSURI = input.getUserData("nsURI")
		return "true".equals(isNSURI)
	}
}
