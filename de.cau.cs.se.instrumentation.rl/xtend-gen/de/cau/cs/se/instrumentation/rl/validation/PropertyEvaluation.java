package de.cau.cs.se.instrumentation.rl.validation;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import de.cau.cs.se.instrumentation.rl.recordLang.Classifier;
import de.cau.cs.se.instrumentation.rl.recordLang.PartialRecordType;
import de.cau.cs.se.instrumentation.rl.recordLang.Property;
import de.cau.cs.se.instrumentation.rl.recordLang.RecordType;
import de.cau.cs.se.instrumentation.rl.recordLang.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class PropertyEvaluation {
  /**
   * Collect recursively a list of all data properties. Meaning aliases must be ignored.
   * 
   * @param type
   * 		a recordType
   * 
   * @returns
   * 		a complete list of all properties in a record
   */
  protected static Collection<Property> _collectAllDataProperties(final RecordType type) {
    ArrayList<Property> _arrayList = new ArrayList<Property>();
    final ArrayList<Property> list = _arrayList;
    Collection<Property> _collectAllProperties = PropertyEvaluation.collectAllProperties(type);
    final Function1<Property,Boolean> _function = new Function1<Property,Boolean>() {
      public Boolean apply(final Property property) {
        Property _referTo = property.getReferTo();
        boolean _equals = Objects.equal(_referTo, null);
        return Boolean.valueOf(_equals);
      }
    };
    Iterable<Property> _filter = IterableExtensions.<Property>filter(_collectAllProperties, _function);
    Iterables.<Property>addAll(list, _filter);
    return list;
  }
  
  /**
   * Collect recursively a list of all data properties. Meaning aliases must be ignored.
   * 
   * @param type
   * 		a recordType
   * 
   * @returns
   * 		a complete list of all properties in a record
   */
  protected static Collection<Property> _collectAllDataProperties(final PartialRecordType type) {
    ArrayList<Property> _arrayList = new ArrayList<Property>();
    final ArrayList<Property> list = _arrayList;
    Collection<Property> _collectAllProperties = PropertyEvaluation.collectAllProperties(type);
    final Function1<Property,Boolean> _function = new Function1<Property,Boolean>() {
      public Boolean apply(final Property property) {
        Property _referTo = property.getReferTo();
        boolean _equals = Objects.equal(_referTo, null);
        return Boolean.valueOf(_equals);
      }
    };
    Iterable<Property> _filter = IterableExtensions.<Property>filter(_collectAllProperties, _function);
    Iterables.<Property>addAll(list, _filter);
    return list;
  }
  
  /**
   * Collect recursively a list of all data and alias properties.
   * 
   * @param type
   * 		a recordType
   * 
   * @returns
   * 		a complete list of all properties in a record
   */
  protected static Collection<Property> _collectAllProperties(final RecordType type) {
    Collection<Property> _xifexpression = null;
    RecordType _parent = type.getParent();
    boolean _notEquals = (!Objects.equal(_parent, null));
    if (_notEquals) {
      RecordType _parent_1 = type.getParent();
      Collection<Property> _collectAllProperties = PropertyEvaluation.collectAllProperties(_parent_1);
      _xifexpression = _collectAllProperties;
    } else {
      ArrayList<Property> _arrayList = new ArrayList<Property>();
      _xifexpression = _arrayList;
    }
    final Collection<Property> result = _xifexpression;
    EList<PartialRecordType> _parents = type.getParents();
    boolean _notEquals_1 = (!Objects.equal(_parents, null));
    if (_notEquals_1) {
      EList<PartialRecordType> _parents_1 = type.getParents();
      final Procedure1<PartialRecordType> _function = new Procedure1<PartialRecordType>() {
        public void apply(final PartialRecordType it) {
          Collection<Property> _collectAllProperties = PropertyEvaluation.collectAllProperties(it);
          PropertyEvaluation.addAllUnique(result, _collectAllProperties);
        }
      };
      IterableExtensions.<PartialRecordType>forEach(_parents_1, _function);
    }
    EList<Property> _properties = type.getProperties();
    return PropertyEvaluation.addAllUnique(result, _properties);
  }
  
  /**
   * Collect recursively a list of all data and alias properties.
   * 
   * @param type
   * 		a recordType
   * 
   * @returns
   * 		a complete list of all properties in a record
   */
  protected static Collection<Property> _collectAllProperties(final PartialRecordType type) {
    ArrayList<Property> _arrayList = new ArrayList<Property>();
    final Collection<Property> result = _arrayList;
    EList<PartialRecordType> _parents = type.getParents();
    boolean _notEquals = (!Objects.equal(_parents, null));
    if (_notEquals) {
      EList<PartialRecordType> _parents_1 = type.getParents();
      final Procedure1<PartialRecordType> _function = new Procedure1<PartialRecordType>() {
        public void apply(final PartialRecordType iface) {
          Collection<Property> _collectAllProperties = PropertyEvaluation.collectAllProperties(iface);
          PropertyEvaluation.addAllUnique(result, _collectAllProperties);
        }
      };
      IterableExtensions.<PartialRecordType>forEach(_parents_1, _function);
    }
    EList<Property> _properties = type.getProperties();
    return PropertyEvaluation.addAllUnique(result, _properties);
  }
  
  /**
   * Collect recursively a list of all properties declared in templates referenced by the given type.
   * 
   * @param type
   * 		a recordType
   * 
   * @returns
   * 		a complete list of all properties in a record
   */
  protected static Collection<Property> _collectAllTemplateProperties(final RecordType type) {
    EList<PartialRecordType> _parents = type.getParents();
    boolean _notEquals = (!Objects.equal(_parents, null));
    if (_notEquals) {
      ArrayList<Property> _arrayList = new ArrayList<Property>();
      final Collection<Property> result = _arrayList;
      EList<PartialRecordType> _parents_1 = type.getParents();
      final Procedure1<PartialRecordType> _function = new Procedure1<PartialRecordType>() {
        public void apply(final PartialRecordType iface) {
          Collection<Property> _collectAllTemplateProperties = PropertyEvaluation.collectAllTemplateProperties(iface);
          PropertyEvaluation.addAllUnique(result, _collectAllTemplateProperties);
        }
      };
      IterableExtensions.<PartialRecordType>forEach(_parents_1, _function);
      return result;
    } else {
      ArrayList<Property> _arrayList_1 = new ArrayList<Property>();
      return _arrayList_1;
    }
  }
  
  /**
   * Collect recursively a list of all properties declared in templates referenced by the given type.
   * 
   * @param type
   * 		a recordType
   * 
   * @returns
   * 		a complete list of all properties in a record
   */
  protected static Collection<Property> _collectAllTemplateProperties(final PartialRecordType type) {
    BasicEList<Property> _basicEList = new BasicEList<Property>();
    final EList<Property> result = _basicEList;
    EList<PartialRecordType> _parents = type.getParents();
    boolean _notEquals = (!Objects.equal(_parents, null));
    if (_notEquals) {
      EList<PartialRecordType> _parents_1 = type.getParents();
      final Procedure1<PartialRecordType> _function = new Procedure1<PartialRecordType>() {
        public void apply(final PartialRecordType iface) {
          Collection<Property> _collectAllTemplateProperties = PropertyEvaluation.collectAllTemplateProperties(iface);
          PropertyEvaluation.addAllUnique(result, _collectAllTemplateProperties);
        }
      };
      IterableExtensions.<PartialRecordType>forEach(_parents_1, _function);
    }
    EList<Property> _properties = type.getProperties();
    return PropertyEvaluation.addAllUnique(result, _properties);
  }
  
  /**
   * Add elements of the second list to the first list if it is not already in there.
   */
  public static Collection<Property> addAllUnique(final Collection<Property> list, final Iterable<Property> addList) {
    final Procedure1<Property> _function = new Procedure1<Property>() {
      public void apply(final Property item) {
        boolean _containsProperty = PropertyEvaluation.containsProperty(list, item);
        boolean _not = (!_containsProperty);
        if (_not) {
          list.add(item);
        }
      }
    };
    IterableExtensions.<Property>forEach(addList, _function);
    return list;
  }
  
  /**
   * Check if a property of a given name and of the same type does already exist in the collected list of properties.
   * 
   * @param list property collection
   * @param item the property to check against the list
   * 
   * @returns
   * 		true if a property of the same name and type already exists, else false
   */
  public static boolean containsProperty(final Collection<Property> list, final Property item) {
    for (final Property p : list) {
      boolean _and = false;
      String _name = p.getName();
      String _name_1 = item.getName();
      boolean _equals = _name.equals(_name_1);
      if (!_equals) {
        _and = false;
      } else {
        Classifier _findType = PropertyEvaluation.findType(p);
        EClassifier _class_ = _findType.getClass_();
        String _name_2 = _class_.getName();
        Classifier _findType_1 = PropertyEvaluation.findType(item);
        EClassifier _class__1 = _findType_1.getClass_();
        String _name_3 = _class__1.getName();
        boolean _equals_1 = _name_2.equals(_name_3);
        _and = (_equals && _equals_1);
      }
      if (_and) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Determine the type of a property. Even if it is an alias.
   * 
   * @param property the property
   * 
   * @param the type classifier
   */
  public static Classifier findType(final Property property) {
    Classifier _type = property.getType();
    boolean _notEquals = (!Objects.equal(_type, null));
    if (_notEquals) {
      return property.getType();
    } else {
      Property _referTo = property.getReferTo();
      return PropertyEvaluation.findType(_referTo);
    }
  }
  
  public static Collection<Property> collectAllDataProperties(final Type type) {
    if (type instanceof PartialRecordType) {
      return _collectAllDataProperties((PartialRecordType)type);
    } else if (type instanceof RecordType) {
      return _collectAllDataProperties((RecordType)type);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(type).toString());
    }
  }
  
  public static Collection<Property> collectAllProperties(final Type type) {
    if (type instanceof PartialRecordType) {
      return _collectAllProperties((PartialRecordType)type);
    } else if (type instanceof RecordType) {
      return _collectAllProperties((RecordType)type);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(type).toString());
    }
  }
  
  public static Collection<Property> collectAllTemplateProperties(final Type type) {
    if (type instanceof PartialRecordType) {
      return _collectAllTemplateProperties((PartialRecordType)type);
    } else if (type instanceof RecordType) {
      return _collectAllTemplateProperties((RecordType)type);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(type).toString());
    }
  }
}
