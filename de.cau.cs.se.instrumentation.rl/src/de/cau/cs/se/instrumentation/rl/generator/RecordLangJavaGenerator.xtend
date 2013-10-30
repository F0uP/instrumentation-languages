package de.cau.cs.se.instrumentation.rl.generator

import de.cau.cs.se.instrumentation.rl.recordLang.RecordType
import de.cau.cs.se.instrumentation.rl.recordLang.Constant
import de.cau.cs.se.instrumentation.rl.recordLang.Property
import de.cau.cs.se.instrumentation.rl.recordLang.Literal
import de.cau.cs.se.instrumentation.rl.recordLang.StringLiteral
import de.cau.cs.se.instrumentation.rl.recordLang.IntLiteral
import de.cau.cs.se.instrumentation.rl.recordLang.Classifier
import de.cau.cs.se.instrumentation.rl.recordLang.Model
import de.cau.cs.se.instrumentation.rl.recordLang.FloatLiteral
import de.cau.cs.se.instrumentation.rl.recordLang.BooleanLiteral
import de.cau.cs.se.instrumentation.rl.recordLang.ConstantLiteral
import org.eclipse.emf.common.util.EList
import java.io.File

class RecordLangJavaGenerator extends RecordLangGenericGenerator {
	
	/**
	 * Primary code generation template.
	 * 
	 * @params type
	 * 		one record type to be used to create monitoring record
	 * @params author
	 * 		generic author name for the record
	 * @params version
	 * 		generic kieker version for the record
	 */
	override createContent(RecordType type, String author, String version) {
		val serialUID = '123456789L'
		val allProperties = type.collectAllProperties
		'''
		/***************************************************************************
		 * Copyright 2013 Kieker Project (http://kieker-monitoring.net)
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
		
		package «(type.eContainer as Model).name»;
		
		import kieker.common.record.AbstractMonitoringRecord;
		import kieker.common.record.IMonitoringRecord;
		import kieker.common.record.flow.IFlowRecord;
		
		/**
		 * @author «author»
		 * 
		 * @since «version»
		 */
		public final class «type.name» extends «if (type.parent!=null) type.parent.name else 'AbstractMonitoringRecord'» implements IMonitoringRecord.Factory, IFlowRecord {
		
			«type.constants.map[const | createDefaultConstant(const)].join»
					
			private static final long serialVersionUID = «serialUID»;
			private static final Class<?>[] TYPES = {
				«allProperties.map[property |createPropertyType(property)].join»
			};
		
			«type.properties.map[property |createProperty(property)].join»
		
			/**
			 * Creates a new instance of this class using the given parameters.
			 * 
			 «type.properties.map[property |createPropertyComment(property)].join»
			 */
			public «type.name»(«allProperties.map[property |createPropertyParameter(property)].join(', ')») {
				«if (type.parent!=null) 'super('+type.parent.collectAllProperties.map[name].join(', ')+');'»
				«type.properties.map[property | createPropertyAssignment(property)].join»
			}
		
			/**
			 * Creates a new instance of this class using the given parameter.
			 * 
			 * @param values
			 *            The array containing the values for the fields of this class. This should normally be the array resulting in a call to {@link #toArray()}.
			 */
			public «type.name»(final Object[] values) { // NOPMD (values stored directly)
				AbstractMonitoringRecord.checkArray(values, TYPES);
				«IF (type.parent!=null)»
					final Object[] parentValues = new Object[«type.parent.collectAllProperties.size»];
					for(int i=0;i<«type.parent.collectAllProperties.size»;i++)
						parentValues[i]=values[i];
					super(parentValues);
				«ENDIF»
				«type.properties.createPropertyGenericAssignments(if (type.parent!=null) type.parent.collectAllProperties.size else 0)»
			}
		
			/**
			 * {@inheritDoc}
			 */
			public final Object[] toArray() {
				return new Object[] { «type.properties.map[property | createPropertyArray(property)].join(',\n')»  };
			}
		
			/**
			 * {@inheritDoc}
			 */
			public void writeBytes(final ByteBuffer buffer, final IRegistry<String> stringRegistry) throws BufferOverflowException {
				«allProperties.map[property | createPropertyBinarySerialization(property)].join('\n')»
			}

			/**
			 * {@inheritDoc}
			 */
			public final Class<?>[] getValueTypes() {
				return TYPES; // NOPMD
			}
		
			/**
			 * {@inheritDoc}
			 * 
			 * @deprecated This record uses the {@link kieker.common.record.IMonitoringRecord.Factory} mechanism. Hence, this method is not implemented.
			 */
			@Deprecated
			public final void initFromArray(final Object[] values) {
				throw new UnsupportedOperationException();
			}
			
			/**
			 * {@inheritDoc}
			 * 
			 * @deprecated This record uses the {@link kieker.common.record.IMonitoringRecord.BinaryFactory} mechanism. Hence, this method is not implemented.
			 */
			@Deprecated
			public final void initFromBytes(final ByteBuffer buffer, final IRegistry<String> stringRegistry) throws BufferUnderflowException {
				throw new UnsupportedOperationException();
			}
		
			«type.properties.map[property | createPropertyGetter(property)].join»
		}
		'''
	}
	
	def createPropertyBinarySerialization(Property property) '''
		buffer.«property.createPropertyFunctionCall»;'''
	
	def createPropertyFunctionCall(Property property) {
		switch (property.type.class_.name) {
			case 'key' : '''putInt(stringRegistry.get(this.get«property.name.toFirstUpper»()))'''
			case 'string' : '''putString(this.get«property.name.toFirstUpper»())'''
			case 'byte' : '''putByte(this.get«property.name.toFirstUpper»())'''
			case 'short' : '''putShort(this.get«property.name.toFirstUpper»())'''
			case 'int' : '''putInt(this.get«property.name.toFirstUpper»())'''
			case 'long' : '''putLong(this.get«property.name.toFirstUpper»())'''
			case 'float' : '''putFloat(this.get«property.name.toFirstUpper»())'''
			case 'double' : '''putDouble(this.get«property.name.toFirstUpper»())'''
			case 'char' : '''putChar(this.get«property.name.toFirstUpper»())'''
			case 'boolean' : '''putBoolean(this.get«property.name.toFirstUpper»())'''
		}
	}

	
	/**
	 * Creates a getter for a given property.
	 * 
	 * @param property
	 * 		a property of the record type
	 * 
	 * @returns the resulting getter as a CharSequence
	 */
	def createPropertyGetter(Property property) '''
	public final «property.type.createTypeName» get«property.name.toFirstUpper»() {
		return this.«property.name»;
	}
	
	'''
	
	/**
	 * Returns the name of a property for the object array composition
	 * 
	 * @param property
	 * 		 a property of the record type
	 * 
	 * @returns the resulting array entry
	 */
	def createPropertyArray(Property property) '''this.«property.name»'''
	
	/**
	 * Create all assignments for the generic constructor based on property name and an array.
	 * 
	 * @param property
	 * 		a property of the record type
	 * @param offset
	 * 		the array offset
	 * 
	 * @returns all assignments for the given property list
	 */
	def createPropertyGenericAssignments(EList<Property> list, int offset) {
		val EList<CharSequence> r = new org.eclipse.emf.common.util.BasicEList<CharSequence>()
		list.forEach[property, index| r.add(property.createPropertyGenericAssignment(index+offset))]
		return r.join
	}
	
	/**
	 * Create an assignment with a property as assignment target and an array value as source.
	 * Used in the generic constructor.
	 * 
	 * @param property
	 * 		a property of the record type
	 * @param index
	 * 		the array index
	 * 
	 * @returns one assignment
	 */
	def String createPropertyGenericAssignment(Property property, int index) '''this.«property.name» = («property.type.createTypeName») values[«index»];
	'''
	
	/**
	 * Create an assignment with a property as assignment target and an array value as source.
	 * 
	 * @param property
	 * 		a property of the record type
	 * @param index
	 * 		the array index
	 * 
	 * @returns one assignment
	 */
	def createPropertyAssignment(Property property) '''this.«property.name» = «property.name»;
	'''
	
	/**
	 * Create one entry for the constructor parameter sequence.
	 * 
	 * @param property
	 * 		a property of the record type
	 * 
	 * @returns one parameter for the given property
	 */
	def createPropertyParameter(Property property) '''final «property.type.createTypeName» «property.name»'''
	
	/**
	 * Create an arbitrary comment for a property of a monitoring record.
	 * 
	 * @param property
	 * 		a property of the record type
	 * 
	 * @returns one comment
	 */
	def createPropertyComment(Property property)
	'''
	* @param «property.name»
	*            «property.name»
	'''
	
	/**
	 * Create a property declaration for a monitoring record.
	 * 
	 * @param property
	 * 		a property of the record type
	 * 
	 * @returns  one property declaration
	 */
	def createProperty(Property property) '''private final «property.type.createTypeName» «property.name»;
	'''
	
	/**
	 * Create one entry of the property type array.
	 * 
	 * @param property
	 * 		a property of the record type
	 * 
	 * @returns one type entry 
	 */
	def createPropertyType(Property property) '''«property.type.createTypeName».class, // «property.name»
	''' 
	
	/**
	 * create a constant name from a standard name camel case name.
	 */
	def createConstName(String name) {
		// CaMeL -> CA_ME_L
		return name.replaceAll("","")
	}
	
	/**
	 * Create a property constant based on the language default expression.
	 * 
	 * @param constant
	 * 		a default constant object
	 * 
	 * @returns a constant declaration
	 */
	def createDefaultConstant(Constant constant) '''
		public static final «constant.type.createTypeName» «constant.name» = «constant.value.createValue»;
	'''
	
	/**
	 * Determine the right Java string for a given system type.
	 * 
	 * @param classifier
	 * 		a classifier representing a type
	 * 
	 * @returns a java type name
	 */
	override createTypeName(Classifier classifier) {
		switch (classifier.class_.name) {
			case 'string' : 'String'
			case 'key' : 'String'
			default : classifier.class_.name
		}	
	}
	
	/**
	 * Compute the directory name for a record type.
	 */
	override directoryName(RecordType type) '''java«File::separator»«(type.eContainer as Model).name.replace('.',File::separator)»'''
	
	
	
	/**
	 * Dispatch for literals
	 */
	dispatch def CharSequence createValue(StringLiteral literal) '''"«literal.value»"'''
	dispatch def CharSequence createValue(IntLiteral literal) '''«literal.value»'''
	dispatch def CharSequence createValue(FloatLiteral literal) '''«literal.value»'''
	dispatch def CharSequence createValue(BooleanLiteral literal) '''«if (literal.value) 'true' else 'false'»'''
	dispatch def CharSequence createValue(ConstantLiteral literal) '''«literal.value.value.createValue»'''
	
	dispatch def CharSequence createValue(Literal literal) {
		'ERROR ' + literal.class.name
	}
	
	override getFileExtension() '''java'''
}