/**
 */
package kieker.develop.al.mapping;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.develop.al.mapping.Reference#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see kieker.develop.al.mapping.MappingPackage#getReference()
 * @model
 * @generated
 */
public interface Reference extends Feature {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(TypeReference)
	 * @see kieker.develop.al.mapping.MappingPackage#getReference_Type()
	 * @model containment="true" required="true"
	 * @generated
	 */
	TypeReference getType();

	/**
	 * Sets the value of the '{@link kieker.develop.al.mapping.Reference#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(TypeReference value);

} // Reference
