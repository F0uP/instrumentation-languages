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
package kieker.develop.rl.ui.labeling

import com.google.inject.Inject

/**
 * Provides labels for a EObjects.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#labelProvider
 */
class RecordLangLabelProvider extends org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider {

	@Inject
	new(org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}

	// Labels and icons can be computed like this:
	def text(kieker.develop.rl.recordLang.Property e) {
		e.name + ' : ' + e.type.text
	}
	
	def text(kieker.develop.rl.recordLang.StringLiteral e) {
		'\'' + e.value + '\''
	}
	
	def text(kieker.develop.rl.recordLang.IntLiteral e) {
		e.value
	}
	
	def text(kieker.develop.rl.recordLang.ArrayLiteral e) {
		'array'
	}
	
	def text(kieker.develop.rl.recordLang.FloatLiteral e) {
		e.value
	}
	
	def text(kieker.develop.rl.recordLang.BooleanLiteral e) {
		if (e.value) 'true' else 'false'
	}
	
	def text(kieker.develop.rl.recordLang.Classifier e) {
		e.type.name + e.sizes.map['[' + (if (it.size != 0) it.size else '') + ']'].join
	}
	
	def image(kieker.develop.rl.recordLang.Property e) {
		'property.gif'
	}
	
	def image(kieker.develop.rl.recordLang.Constant e) {
		'constant.gif'
	}
	
	def image(kieker.develop.rl.recordLang.EventType e) {
		'record.png'
	}
	
	def image(kieker.develop.rl.recordLang.TemplateType e) {
		'template.png'
	}
	
	def image(kieker.develop.rl.recordLang.Model e) {
		'package.gif'
	}
	
//	def text(Greeting ele) {
//		'A greeting to ' + ele.name
//	}
//
//	def image(Greeting ele) {
//		'Greeting.gif'
//	}
}
