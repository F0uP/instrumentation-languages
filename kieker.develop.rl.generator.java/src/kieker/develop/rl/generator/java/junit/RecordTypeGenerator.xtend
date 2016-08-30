package kieker.develop.rl.generator.java.junit

import java.io.File
import java.util.ArrayList
import java.util.Collection
import java.util.List
import kieker.develop.rl.generator.AbstractRecordTypeGenerator
import kieker.develop.rl.recordLang.ConstantLiteral
import kieker.develop.rl.recordLang.FloatLiteral
import kieker.develop.rl.recordLang.IntLiteral
import kieker.develop.rl.recordLang.Literal
import kieker.develop.rl.recordLang.Model
import kieker.develop.rl.recordLang.Property
import kieker.develop.rl.recordLang.RecordType
import kieker.develop.rl.recordLang.StringLiteral
import kieker.develop.rl.recordLang.Type

import static extension kieker.develop.rl.generator.java.JavaTypeMapping.*
import static extension kieker.develop.rl.typing.PropertyResolution.*

class RecordTypeGenerator extends AbstractRecordTypeGenerator {
	
	/**
	 * Return the unique id.
	 */
	override getId() '''java.junit'''
	
	/**
	 * Return the preferences activation description.
	 */
	override getDescription() '''JUnit tests for records'''
	
	/**
	 * No junit test for abstract record types.
	 */
	override boolean supportsAbstractRecordType()  { false }
	
	override getDirectoryName(Type type) '''«(type.eContainer as Model).name.createTestPackageName.replace('.',File::separator)»'''

	override getFileName(Type type) '''«type.getDirectoryName»«File::separator»TestGenerated«type.name».java'''
	
	override getOutletType() '''junit'''
	
	override generate(RecordType type) {
		if (type.abstract)
			return null
		val allDataProperties = type.collectAllDataProperties
		'''
		«header»package «(type.eContainer as Model).name.createTestPackageName»;
		
		import java.nio.ByteBuffer;
		
		import org.junit.Assert;
		import org.junit.Test;
		
		import «(type.eContainer as Model).name».«type.name»;
		import kieker.common.util.registry.IRegistry;
		import kieker.common.util.registry.Registry;
		
		import kieker.test.common.junit.AbstractGeneratedKiekerTest;
		import kieker.test.common.util.record.BookstoreOperationExecutionRecordFactory;
				
		/**
		 * Creates {@link OperationExecutionRecord}s via the available constructors and
		 * checks the values passed values via getters.
		 * 
		 * @author «if (type.author == null) author else type.author»
		 * 
		 * @since «if (type.since == null) version else type.since»
		 */
		public class TestGenerated«type.name» extends AbstractGeneratedKiekerTest {
		
			public TestGenerated«type.name»() {
				// empty default constructor
			}
		
			/**
			 * Tests {@link «type.name»#Test«type.name»(String, String, long, long, long, String, int, int)}.
			 */
			@Test
			public void testToArray() { // NOPMD (assert missing)
			for (int i=0;i<ARRAY_LENGTH;i++) {
					// initialize
					«type.name» record = new «type.name»(«allDataProperties.map[property | createPropertyValueSet(property)].join(', ')»);
					
					// check values
					«allDataProperties.createAllGetterValueAssertions(type)»
					
					Object[] values = record.toArray();
					
					Assert.assertNotNull("Record array serialization failed. No values array returned.", values);
					Assert.assertEquals("Record array size does not match expected number of properties «allDataProperties.size».", «allDataProperties.size», values.length);
					
					// check all object values exist
					«allDataProperties.createAllValueExistAssertions»
					
					// check all types
					«allDataProperties.createAllTypeAssertions»
										
					// check all object values 
					«allDataProperties.createAllValueAssertions»
				}
			}
			
			/**
			 * Tests {@link «type.name»#Test«type.name»(String, String, long, long, long, String, int, int)}.
			 */
			@Test
			public void testBuffer() { // NOPMD (assert missing)
				for (int i=0;i<ARRAY_LENGTH;i++) {
					// initialize
					«type.name» record = new «type.name»(«allDataProperties.map[property | createPropertyValueSet(property)].join(', ')»);
					
					// check values
					«allDataProperties.createAllGetterValueAssertions(type)»
				}
			}
			
			/**
			 * Tests {@link «type.name»#Test«type.name»(String, String, long, long, long, String, int, int)}.
			 */
			@Test
			public void testParameterConstruction() { // NOPMD (assert missing)
				for (int i=0;i<ARRAY_LENGTH;i++) {
					// initialize
					«type.name» record = new «type.name»(«allDataProperties.map[property | createPropertyValueSet(property)].join(', ')»);
					
					// check values
					«allDataProperties.createAllGetterValueAssertions(type)»
				}
			}
			
			@Test
			public void testEquality() {
				int i = 0;
				«type.name» oneRecord = new «type.name»(«allDataProperties.map[property | createPropertyValueSet(property)].join(', ')»);
				i = 0;
				«type.name» copiedRecord = new «type.name»(«allDataProperties.map[property | createPropertyValueSet(property)].join(', ')»);
				
				Assert.assertEquals(oneRecord, copiedRecord);
			}
			
			@Test
			public void testUnequality() {
				int i = 0;
				«type.name» oneRecord = new «type.name»(«allDataProperties.map[property | createPropertyValueSet(property)].join(', ')»);
				i = 1;
				«type.name» anotherRecord = new «type.name»(«allDataProperties.map[property | createPropertyValueSet(property)].join(', ')»);
				
				Assert.assertNotEquals(oneRecord, anotherRecord);
			}
		}
		'''
	}
	
	private def createAllValueExistAssertions(Collection<Property> properties) {
		val List<CharSequence> result = new ArrayList<CharSequence>()
		properties.forEach[property, index | result.add(property.createValueExistAssertion(index))]
		
		return result.join
	}
	
	private def CharSequence createValueExistAssertion(Property property, Integer index) '''
		Assert.assertNotNull("Array value [«index»] of type «property.type.type.createPrimitiveWrapperTypeName» must be not null.", values[«index»]); 
	'''
	
	/**
	 * This routine is ugly.
	 * Collect all value assertions
	 */
	private def createAllValueAssertions(Collection<Property> properties) {
		val List<CharSequence> result = new ArrayList<CharSequence>()
		properties.forEach[property, index | result.add(property.createValueAssertion(index))]
		
		return result.join
	}
	
	private def createValueAssertion(Property property, Integer index) '''
		Assert.assertEquals("Array value [«index»] " + values[«index»] + " does not match the desired value " + «property.createPropertyValueSet»,
			«IF property.type.type.name == 'float' || property.type.type.name == 'double'»
				«property.getCastToPrimitiveType» «createPropertyValueSet(property)», «property.getCastToPrimitiveType» («property.type.type.createPrimitiveWrapperTypeName»)values[«index»], 0.0000001
			«ELSEIF property.type.type.name == 'string'»
				«property.createPropertyValueSet» == null?"«property.createConstantValue»":«property.createPropertyValueSet», values[«index»]
			«ELSE»
				«property.createPropertyValueSet», values[«index»]
		«ENDIF»);
	'''
	
	/**
	 * Create constant value for string.
	 */
	private def String createConstantValue(Property property) {
		if (property.value != null)
			return this.createConstantValue(property.value)
		else
			return ""
	}
	
	private def String createConstantValue(Literal value) {
		switch (value) {
			StringLiteral :	return (value as StringLiteral).value
			FloatLiteral : return (value as FloatLiteral).value.toString
			IntLiteral : return (value as IntLiteral).value.toString
			ConstantLiteral : return createConstantValue((value as ConstantLiteral).value.value)
			default : return ""	
		}
	}
	
	/**
	 * Returns a string representing a type case in Java iff
	 * the type is not of type string.
	 */
	private def getCastToPrimitiveType(Property property) {
		if ('string'.equals(property.type.type.name)) {
			return ""
		} else {
			return '(' + property.type.type.name + ')'			
		}
	}
	
	/**
	 * Create assertions to test each return value from a getter of a record.
	 * 
	 * @param properties list of all data properties of a type
	 * @param type the RecordType itself
	 */
	private def createAllGetterValueAssertions(Collection<Property> properties, RecordType type) '''
		«FOR property : properties»
			Assert.assertEquals("«type.name».«property.name» values are not equal.", «IF property.type.type.name == 'float' || property.type.type.name == 'double'»
				«property.getCastToPrimitiveType» «property.createPropertyValueSet», record.get«property.name.toFirstUpper»(), 0.0000001);
			«ELSEIF property.type.type.name == 'boolean'»
				«property.createPropertyValueSet», Boolean.valueOf(record.is«property.name.toFirstUpper»()));
			«ELSEIF property.type.type.name == 'string'»
				«property.createPropertyValueSet» == null?"«property.createConstantValue»":«property.createPropertyValueSet», record.get«property.name.toFirstUpper»());
			«ELSE»
				«property.getCastToPrimitiveType» «property.createPropertyValueSet», record.get«property.name.toFirstUpper»());
			«ENDIF»
		«ENDFOR»
	'''
	
	/**
	 * This routine is ugly.
	 * Collects all type assertions.
	 */
	private def createAllTypeAssertions(Collection<Property> properties) {
		val List<CharSequence> result = new ArrayList<CharSequence>()
		properties.forEach[property, index | result.add(property.createTypeAssertion(index))]
		
		return result.join
	}
	
	private def createTypeAssertion(Property property, Integer index) '''
		Assert.assertTrue("Type of array value [«index»] " + values[«index»].getClass().getCanonicalName() + " does not match the desired type «property.type.type.createPrimitiveWrapperTypeName»", values[«index»] instanceof «property.type.type.createPrimitiveWrapperTypeName»);
	'''

	/**
	 * Produce a type conform value for input. This only works for primitive types.
	 */
	private def createPropertyValueSet(Property property) '''«property.type.type.name.toUpperCase»_VALUES.get(i % «property.type.type.name.toUpperCase»_VALUES.size())'''
	
	
	/**
	 * Reconstruct the package name out of the record package name.
	 * kieker.common.record -> kieker.test.common.junit.record
	 * All other structures are converted by adding .junit to the package hierarchy
	 */
	private def createTestPackageName(String name) {
		if (name.contains("kieker.common.record")) 
			return name.replaceAll("kieker\\.common\\.record", "kieker.test.common.junit.record")
		else
			return name + ".junit"
	}
		
		
}