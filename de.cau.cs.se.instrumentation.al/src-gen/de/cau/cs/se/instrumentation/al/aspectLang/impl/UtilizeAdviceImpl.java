/**
 */
package de.cau.cs.se.instrumentation.al.aspectLang.impl;

import de.cau.cs.se.instrumentation.al.aspectLang.Advice;
import de.cau.cs.se.instrumentation.al.aspectLang.AspectLangPackage;
import de.cau.cs.se.instrumentation.al.aspectLang.ParamExpression;
import de.cau.cs.se.instrumentation.al.aspectLang.UtilizeAdvice;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Utilize Advice</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.se.instrumentation.al.aspectLang.impl.UtilizeAdviceImpl#getAdvice <em>Advice</em>}</li>
 *   <li>{@link de.cau.cs.se.instrumentation.al.aspectLang.impl.UtilizeAdviceImpl#getParameterAssignments <em>Parameter Assignments</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UtilizeAdviceImpl extends MinimalEObjectImpl.Container implements UtilizeAdvice
{
  /**
   * The cached value of the '{@link #getAdvice() <em>Advice</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAdvice()
   * @generated
   * @ordered
   */
  protected Advice advice;

  /**
   * The cached value of the '{@link #getParameterAssignments() <em>Parameter Assignments</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameterAssignments()
   * @generated
   * @ordered
   */
  protected EList<ParamExpression> parameterAssignments;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected UtilizeAdviceImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return AspectLangPackage.Literals.UTILIZE_ADVICE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Advice getAdvice()
  {
    if (advice != null && advice.eIsProxy())
    {
      InternalEObject oldAdvice = (InternalEObject)advice;
      advice = (Advice)eResolveProxy(oldAdvice);
      if (advice != oldAdvice)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, AspectLangPackage.UTILIZE_ADVICE__ADVICE, oldAdvice, advice));
      }
    }
    return advice;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Advice basicGetAdvice()
  {
    return advice;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAdvice(Advice newAdvice)
  {
    Advice oldAdvice = advice;
    advice = newAdvice;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AspectLangPackage.UTILIZE_ADVICE__ADVICE, oldAdvice, advice));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ParamExpression> getParameterAssignments()
  {
    if (parameterAssignments == null)
    {
      parameterAssignments = new EObjectContainmentEList<ParamExpression>(ParamExpression.class, this, AspectLangPackage.UTILIZE_ADVICE__PARAMETER_ASSIGNMENTS);
    }
    return parameterAssignments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case AspectLangPackage.UTILIZE_ADVICE__PARAMETER_ASSIGNMENTS:
        return ((InternalEList<?>)getParameterAssignments()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case AspectLangPackage.UTILIZE_ADVICE__ADVICE:
        if (resolve) return getAdvice();
        return basicGetAdvice();
      case AspectLangPackage.UTILIZE_ADVICE__PARAMETER_ASSIGNMENTS:
        return getParameterAssignments();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case AspectLangPackage.UTILIZE_ADVICE__ADVICE:
        setAdvice((Advice)newValue);
        return;
      case AspectLangPackage.UTILIZE_ADVICE__PARAMETER_ASSIGNMENTS:
        getParameterAssignments().clear();
        getParameterAssignments().addAll((Collection<? extends ParamExpression>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case AspectLangPackage.UTILIZE_ADVICE__ADVICE:
        setAdvice((Advice)null);
        return;
      case AspectLangPackage.UTILIZE_ADVICE__PARAMETER_ASSIGNMENTS:
        getParameterAssignments().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case AspectLangPackage.UTILIZE_ADVICE__ADVICE:
        return advice != null;
      case AspectLangPackage.UTILIZE_ADVICE__PARAMETER_ASSIGNMENTS:
        return parameterAssignments != null && !parameterAssignments.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //UtilizeAdviceImpl