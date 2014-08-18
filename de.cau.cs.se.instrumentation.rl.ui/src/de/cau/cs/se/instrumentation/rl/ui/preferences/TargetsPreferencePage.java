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
package de.cau.cs.se.instrumentation.rl.ui.preferences;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import de.cau.cs.se.instrumentation.rl.generator.LanguageSetup;
import de.cau.cs.se.instrumentation.rl.preferences.TargetsPreferences;


/**
 * Implements a simple preference page for IRL.
 * 
 * Created after: http://xtextcasts.org/episodes/21-preference-page
 * 
 * @author Reiner Jung
 * 
 */
public class TargetsPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	/**
	 * Default constructor.
	 */
	public TargetsPreferencePage() {
		super(GRID);
		final IPreferenceStore store = new ScopedPreferenceStore(InstanceScope.INSTANCE, TargetsPreferences.PLUGIN_ID);
		this.setPreferenceStore(store);
		this.setDescription("Target and Output Configuration");
	}

	@Override
	public void createFieldEditors() {
		for (final String language : LanguageSetup.getPresentLanguages()) {
			this.addField(new BooleanFieldEditor(TargetsPreferences.GENERATOR_ACTIVE + language, language, this.getFieldEditorParent()));
		}

		// SpacerFieldEditor spacer = new SpacerFieldEditor(getFieldEditorParent());
		// addField(spacer);

		this.addField(new StringFieldEditor(TargetsPreferences.AUTHOR_NAME, "Author", this.getFieldEditorParent()));
		this.addField(new StringFieldEditor(TargetsPreferences.VERSION_ID, "Version", this.getFieldEditorParent()));
	}

	/**
	 * Initializes the preference page of the given workbench.
	 * 
	 * @param workbench the workbench
	 */
	public void init(final IWorkbench workbench) {
	}

}