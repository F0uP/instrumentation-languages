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
package kieker.develop.rl.ui

import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor

import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfiguration
import org.eclipse.xtext.ide.editor.syntaxcoloring.ISemanticHighlightingCalculator;

/**
 * Use this class to register components to be used within the Eclipse IDE.
 */
@FinalFieldsConstructor
class RecordLangUiModule extends AbstractRecordLangUiModule {
	
	/**
	 * Add highlighting calculator for types.
	 * 
	 * @return return the highlighter
	 */
	def Class<? extends ISemanticHighlightingCalculator> bindISemanticHighlightingCalculator() {
		return TypesHighlightingCalculator
	}

	/**
	 * Add highlighting configuration for types.
	 * 
	 * @return return the configuration
	 */
	def Class<? extends IHighlightingConfiguration> bindIHighlightingConfiguration() {
		return TypesHighlightingConfiguration
	}
}
