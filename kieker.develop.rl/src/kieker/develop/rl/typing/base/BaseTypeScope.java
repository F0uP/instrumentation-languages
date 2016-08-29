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
package kieker.develop.rl.typing.base;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractScope;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import kieker.develop.rl.recordLang.RecordLangFactory;
import kieker.develop.rl.recordLang.Type;
import kieker.develop.rl.typing.ITypeProvider;

/**
 *
 *
 * @author Christian Schneider
 */
public class BaseTypeScope extends AbstractScope {

	private final ITypeProvider typeProvider;

	private final IQualifiedNameConverter qualifiedNameConverter;

	private final Predicate<IEObjectDescription> filter;

	/**
	 * Create base type scope.
	 *
	 * @param typeProvider
	 *            provider for the base types
	 * @param qualifiedNameConverter
	 *            name converter
	 * @param filter
	 *            filter for the results
	 */
	public BaseTypeScope(final ITypeProvider typeProvider,
			final IQualifiedNameConverter qualifiedNameConverter,
			final Predicate<IEObjectDescription> filter) {
		super(IScope.NULLSCOPE, false);
		this.typeProvider = typeProvider;
		this.qualifiedNameConverter = qualifiedNameConverter;
		this.filter = filter;
	}

	@Override
	public IEObjectDescription getSingleElement(final QualifiedName name) {
		final Type type = this.typeProvider
				.findTypeByName(this.qualifiedNameConverter.toString(name));
		if (type == null) {
			return null;
		}
		final IEObjectDescription result = EObjectDescription.create(name, type);
		if ((this.filter != null) && !this.filter.apply(result)) {
			return null;
		}
		return result;
	}

	@Override
	public Iterable<IEObjectDescription> getElements(final QualifiedName name) {
		final IEObjectDescription result = this.getSingleElement(name);
		if (result != null) {
			return Collections.singleton(result);
		}
		return Collections.emptySet();
	}

	@Override
	public Iterable<IEObjectDescription> getElements(final EObject object) {
		if (object instanceof JvmIdentifiableElement) {
			final Set<IEObjectDescription> result = Collections.singleton(EObjectDescription
					.create(this.qualifiedNameConverter
							.toQualifiedName(((JvmIdentifiableElement) object)
									.getQualifiedName()),
							object));
			return this.filterResult(result);
		}
		return Collections.emptySet();
	}

	/**
	 * Filter result.
	 *
	 * @param unfiltered
	 *            list of unfiltered results
	 * @return Returns the filtered results
	 */
	protected Iterable<IEObjectDescription> filterResult(
			final Iterable<IEObjectDescription> unfiltered) {
		if (this.filter == null) {
			return unfiltered;
		}
		return Iterables.filter(unfiltered, this.filter);
	}

	@Override
	public Iterable<IEObjectDescription> getAllElements() {
		return this.filterResult(this.internalGetAllElements());
	}

	/**
	 * Get all types.
	 *
	 * @return Returns the list all types of the scope.
	 */
	protected Iterable<IEObjectDescription> internalGetAllElements() {
		final List<IEObjectDescription> types = Lists.newArrayList();

		for (final Type t : this.typeProvider.getAllTypes()) {
			types.add(this.createScopedElement(t.getName()));
		}

		return types;
	}

	/**
	 * Create scoped element.
	 *
	 * @param fullyQualifiedName
	 *            name of the element
	 *
	 * @return Returns the scoped element
	 */
	protected IEObjectDescription createScopedElement(final String fullyQualifiedName) {
		final InternalEObject proxy = this.createProxy(fullyQualifiedName);
		final IEObjectDescription eObjectDescription = EObjectDescription.create(
				this.qualifiedNameConverter.toQualifiedName(fullyQualifiedName),
				proxy);
		return eObjectDescription;
	}

	/**
	 * Create a proxy element.
	 *
	 * @param fullyQualifiedName
	 *            name of element
	 * @return Returns the proxy element
	 */
	protected InternalEObject createProxy(final String fullyQualifiedName) {
		final URI uri = BaseTypeURIHelper.getFullURIForClass(fullyQualifiedName);
		final InternalEObject proxy = (InternalEObject) RecordLangFactory.eINSTANCE.createBaseType();
		proxy.eSetProxyURI(uri);
		return proxy;
	}

	@Override
	protected Iterable<IEObjectDescription> getAllLocalElements() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
