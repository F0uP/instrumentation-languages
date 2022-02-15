/**
 */
package analysismodel.assembly.util;

import analysismodel.assembly.*;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see analysismodel.assembly.AssemblyPackage
 * @generated
 */
public class AssemblyAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AssemblyPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = AssemblyPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssemblySwitch<Adapter> modelSwitch =
		new AssemblySwitch<Adapter>() {
			@Override
			public Adapter caseAssemblyModel(AssemblyModel object) {
				return createAssemblyModelAdapter();
			}
			@Override
			public Adapter caseEStringToAssemblyComponentMapEntry(Map.Entry<String, AssemblyComponent> object) {
				return createEStringToAssemblyComponentMapEntryAdapter();
			}
			@Override
			public Adapter caseAssemblyComponent(AssemblyComponent object) {
				return createAssemblyComponentAdapter();
			}
			@Override
			public Adapter caseEStringToAssemblyOperationMapEntry(Map.Entry<String, AssemblyOperation> object) {
				return createEStringToAssemblyOperationMapEntryAdapter();
			}
			@Override
			public Adapter caseAssemblyOperation(AssemblyOperation object) {
				return createAssemblyOperationAdapter();
			}
			@Override
			public Adapter caseAssemblyStorage(AssemblyStorage object) {
				return createAssemblyStorageAdapter();
			}
			@Override
			public Adapter caseEStringToAssemblyStorageMapEntry(Map.Entry<String, AssemblyStorage> object) {
				return createEStringToAssemblyStorageMapEntryAdapter();
			}
			@Override
			public Adapter caseAssemblyProvidedInterface(AssemblyProvidedInterface object) {
				return createAssemblyProvidedInterfaceAdapter();
			}
			@Override
			public Adapter caseEStringToAssemblyProvidedInterfaceMapEntry(Map.Entry<String, AssemblyProvidedInterface> object) {
				return createEStringToAssemblyProvidedInterfaceMapEntryAdapter();
			}
			@Override
			public Adapter caseAssemblyRequiredInterface(AssemblyRequiredInterface object) {
				return createAssemblyRequiredInterfaceAdapter();
			}
			@Override
			public Adapter caseEStringToInterfaceAssemblyOperationMapEntry(Map.Entry<String, AssemblyOperation> object) {
				return createEStringToInterfaceAssemblyOperationMapEntryAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link analysismodel.assembly.AssemblyModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see analysismodel.assembly.AssemblyModel
	 * @generated
	 */
	public Adapter createAssemblyModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>EString To Assembly Component Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createEStringToAssemblyComponentMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link analysismodel.assembly.AssemblyComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see analysismodel.assembly.AssemblyComponent
	 * @generated
	 */
	public Adapter createAssemblyComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>EString To Assembly Operation Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createEStringToAssemblyOperationMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link analysismodel.assembly.AssemblyOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see analysismodel.assembly.AssemblyOperation
	 * @generated
	 */
	public Adapter createAssemblyOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link analysismodel.assembly.AssemblyStorage <em>Storage</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see analysismodel.assembly.AssemblyStorage
	 * @generated
	 */
	public Adapter createAssemblyStorageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>EString To Assembly Storage Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createEStringToAssemblyStorageMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link analysismodel.assembly.AssemblyProvidedInterface <em>Provided Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see analysismodel.assembly.AssemblyProvidedInterface
	 * @generated
	 */
	public Adapter createAssemblyProvidedInterfaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>EString To Assembly Provided Interface Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createEStringToAssemblyProvidedInterfaceMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link analysismodel.assembly.AssemblyRequiredInterface <em>Required Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see analysismodel.assembly.AssemblyRequiredInterface
	 * @generated
	 */
	public Adapter createAssemblyRequiredInterfaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>EString To Interface Assembly Operation Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createEStringToInterfaceAssemblyOperationMapEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //AssemblyAdapterFactory
