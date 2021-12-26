/***************************************************************************
 * Copyright 2020 Kieker Project (http://kieker-monitoring.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/
package kieker.develop.rl.generator.python

import java.util.Collection
import kieker.develop.rl.generator.AbstractTypeGenerator
import kieker.develop.rl.recordLang.Classifier
import kieker.develop.rl.recordLang.ComplexType
import kieker.develop.rl.recordLang.EventType
import kieker.develop.rl.recordLang.Model
import kieker.develop.rl.recordLang.Property
import static extension kieker.develop.rl.typing.PropertyResolution.*
import kieker.develop.rl.generator.Version
import kieker.develop.rl.generator.InternalErrorException
import kieker.develop.rl.typing.base.BaseTypes
import kieker. develop.rl.generator.python.PropertyHelper
class EventTypeGenerator extends AbstractTypeGenerator<EventType, CharSequence> {
	
	override accepts(ComplexType type) {
		if (type instanceof EventType)
			!type.abstract
		else
			false
	}
			
	/**
	 * Central code generation template.
	 * 
	 * @param type the event type
	 * 	in this event type (template inherited types and own types)
	 * @param header the header comment
	 * @param author the author of the EvenType
	 * @param version the version of the first occurrence of the type
	 * 
	 * @return a Java class for a Kieker EventType
	 */
	protected override createOutputModel(EventType type, Version targetVersion, String header, String author, String version) {
		val allDataProperties = type.collectAllDataProperties
		val allDeclarationProperties = type.collectAllDeclarationProperties
		'''		
		class «type.name»:
			
			def init(self, «allDataProperties.filter[!it.isTransient 
													|| (it.isTransient 
													&& !it.isIncrement
													)].map[property | createPropertyParameter(property)].join(', ')»):	
				«allDeclarationProperties.filter[!it.isIncrement].map[property | createAssignment(property)].join»
		
			def serialize(self, serializer):
				«FOR e : allDataProperties»
				
		'''
	}
	
	def createPropertyParameter(Property property){
		'''«property.name»'''
	}
	
	def createAssignment(Property property){
		'''self.«property.name» = «property.name»'''
	}

	
	/**
	 * Create python type names.
	 */
	def createTypeName(Classifier classifier) {
		switch (classifier.type.name) {
			case 'string' : 'String'
			default : classifier.type.name
		}
	}
	
	/**
	 * Create one property for the type declaration.
	 */
	def createProperty(Property property) '''«property.name» => $«property.name»'''
	
	/**
	 * Compute the absolute Python package name, which is a FQN name of the record.
	 */
	def recordName(EventType type) '''«(type.eContainer as Model).name.replace('.','::')»::«type.name»'''
		
	/**
	 * 
	 */
	def createParameterCall(Collection<Property> list) {
		list.map['''$«name»'''].join(', ')
	}
	
	
}
