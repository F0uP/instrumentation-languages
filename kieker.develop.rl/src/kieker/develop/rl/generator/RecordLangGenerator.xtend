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
package kieker.develop.rl.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IGenerator2
import org.eclipse.xtext.generator.IFileSystemAccess
import kieker.develop.rl.recordLang.RecordType
import kieker.develop.rl.recordLang.TemplateType
import kieker.develop.rl.preferences.TargetsPreferences
import org.eclipse.core.runtime.preferences.IEclipsePreferences
import org.osgi.service.prefs.Preferences
import java.util.Calendar
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.generator.IGeneratorContext

/**
 * Generates one single files per record for java, c, and perl. 
 */
class RecordLangGenerator implements IGenerator2 {
				
	override void doGenerate(Resource resource, IFileSystemAccess2 fsa, IGeneratorContext context) {
		if (resource.URI.platformResource) {			
			// list all generators to support RecordType
			val preferenceStore = TargetsPreferences.preferenceStore
					
			val project = resource.URI.segmentsList.get(1)
			
			val projectStore = preferenceStore.node(project)
			
			projectStore.v()
																	
			preferenceStore.runGenerators(resource, fsa)
		}
	}
	
	def void v(Preferences preferences) {
		preferences.childrenNames.forEach[System.out.println(">> " + it)]
	}
	
	
	private def runGenerators(IEclipsePreferences preferenceStore, Resource resource, IFileSystemAccess fsa) {
		val version = TargetsPreferences.getVersionID(preferenceStore)
			val author = TargetsPreferences.getAuthorName(preferenceStore)
								
			/** Generator invocation for RecordTypes */																	
			for (Class<?> generator : GeneratorConfiguration.RECORD_TYPE_GENERATORS) {
				val cg = generator.getConstructor().newInstance() as AbstractRecordTypeGenerator
				cg.author = author
				cg.version = version
				cg.header = TargetsPreferences.getHeaderComment(preferenceStore, cg.id).
					replace("THIS-YEAR", Calendar.getInstance().get(Calendar.YEAR).toString)
				
				if (TargetsPreferences.isGeneratorActive(preferenceStore, cg.id)) {
					resource.allContents.filter(typeof(RecordType)).forEach[type |
						if (cg.supportsAbstractRecordType || (!cg.supportsAbstractRecordType && !type.abstract)) {
							fsa.generateFile(cg.getFileName(type),	cg.outletType, cg.generate(type))	
						}
					]
				}
			}
			
			/** Generator invocation for TemplateTypes */
			for (Class<?> generator : GeneratorConfiguration.TEMPLATE_TYPE_GENERATORS) {
				val cg = generator.getConstructor().newInstance() as AbstractTemplateTypeGenerator
				cg.author = author
				cg.version = version
				cg.header = TargetsPreferences.getHeaderComment(preferenceStore, cg.id).
					replace("THIS-YEAR", Calendar.getInstance().get(Calendar.YEAR).toString)
				
				if (TargetsPreferences.isGeneratorActive(preferenceStore, cg.id)) {
					resource.allContents.filter(typeof(TemplateType)).forEach[type | 
						fsa.generateFile(cg.getFileName(type), cg.outletType, cg.generate(type))
					]
				}
			}
	}
	
	override afterGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {
		
	}
	
	override beforeGenerate(Resource input, IFileSystemAccess2 fsa, IGeneratorContext context) {
		
	}
}
