package de.cau.cs.se.instrumentation.al.modelhandling

import de.cau.cs.se.instrumentation.al.mapping.MappingModel
import de.cau.cs.se.instrumentation.al.aspectLang.ApplicationModel
import org.eclipse.emf.ecore.resource.ResourceSet
import java.util.Collection
import de.cau.cs.se.instrumentation.al.aspectLang.Technology

interface IModelMapper {
	/**
	 * Load an application model and return a mapping model.
	 * 
	 * @param model an application model reference
	 * @param resourceSet the resource set for the model
	 * 
	 * @returns a mapping model
	 */
	def MappingModel loadModel(ApplicationModel model, ResourceSet resourceSet)
	
	/**
	 * Return the name of the extension to be used in the language.
	 */
	def String name()
	
	/**
	 * Returns a collection of technologies for the target level
	 * of the transformation processing the application model.
	 */
	def Collection<Technology> targetTechnologies()
}