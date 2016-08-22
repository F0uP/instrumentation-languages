package kieker.develop.al.generator;

import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend.lib.Property;
import org.eclipse.xtend2.lib.StringConcatenation;
import java.util.Set;
import java.util.function.Consumer;
import kieker.develop.al.aspectLang.Advice;
import kieker.develop.al.aspectLang.AdviceParameter;
import kieker.develop.al.aspectLang.AdviceParameterDeclaration;
import kieker.develop.al.aspectLang.AspectModel;
import kieker.develop.al.aspectLang.Collector;
import kieker.develop.al.aspectLang.Event;
import kieker.develop.al.aspectLang.FloatLiteral;
import kieker.develop.al.aspectLang.InsertionPoint;
import kieker.develop.al.aspectLang.IntLiteral;
import kieker.develop.al.aspectLang.InternalFunction;
import kieker.develop.al.aspectLang.InternalFunctionProperty;
import kieker.develop.al.aspectLang.Literal;
import kieker.develop.al.aspectLang.LocationQuery;
import kieker.develop.al.aspectLang.ReferenceValue;
import kieker.develop.al.aspectLang.ReflectionFunction;
import kieker.develop.al.aspectLang.ReflectionProperty;
import kieker.develop.al.aspectLang.RuntimeProperty;
import kieker.develop.al.aspectLang.StringLiteral;
import kieker.develop.al.aspectLang.TypeReference;
import kieker.develop.al.aspectLang.Value;
import kieker.develop.al.mapping.NamedType;
import kieker.develop.rl.generator.InternalErrorException;
import kieker.develop.rl.generator.java.JavaTypeMapping;
import kieker.develop.rl.recordLang.BaseType;
import kieker.develop.rl.recordLang.Classifier;
import kieker.develop.rl.recordLang.Model;
import kieker.develop.rl.recordLang.Property;
import kieker.develop.rl.recordLang.RecordType;
import kieker.develop.rl.typing.PropertyResolution;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;
import org.eclipse.xtext.xbase.lib.StringExtensions;


/**
 * TODO Class name should be improved.
 */
@SuppressWarnings("all")
public class CommonJavaTemplates {
  /**
   * Return the package name for a given advice.
   */
  public static String getPackageName(final Advice advice) {
    EObject _eContainer = advice.eContainer();
    return ((AspectModel) _eContainer).getName();
  }
  
  /**
   * Create a code fragment containing all necessary record inputs.
   */
<<<<<<< 9b3334702959dbc026d0e6620756cf2a876e5ecc
  public static String createRecordInputs(final /* EList<Collector> */Object collectors) {
    throw new Error("Unresolved compilation problems:"
      + "\nRecordType cannot be resolved to a type."
      + "\nModel cannot be resolved to a type."
      + "\nThe method or field events is undefined for the type Object"
      + "\nThe method or field type is undefined for the type Object"
      + "\nThe method addUnique(List<RecordType>, RecordType) from the type CommonJavaTemplates refers to the missing type RecordType"
      + "\nforEach cannot be resolved"
      + "\neContainer cannot be resolved"
      + "\nname cannot be resolved"
      + "\nname cannot be resolved");
=======
  public static String createRecordInputs(final EList<Collector> collectors) {
    final ArrayList<RecordType> recordList = new ArrayList<RecordType>();
    final Consumer<Collector> _function = (Collector collector) -> {
      EList<Event> _events = collector.getEvents();
      final Consumer<Event> _function_1 = (Event it) -> {
        RecordType _type = it.getType();
        CommonJavaTemplates.addUnique(recordList, _type);
      };
      _events.forEach(_function_1);
    };
    collectors.forEach(_function);
    final Function1<RecordType, String> _function_1 = (RecordType it) -> {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("import ");
      EObject _eContainer = it.eContainer();
      String _name = ((Model) _eContainer).getName();
      _builder.append(_name, "");
      _builder.append(".");
      String _name_1 = it.getName();
      _builder.append(_name_1, "");
      _builder.append(";");
      return _builder.toString();
    };
    List<String> _map = ListExtensions.<RecordType, String>map(recordList, _function_1);
    return IterableExtensions.join(_map, "\n");
>>>>>>> minor regen
  }
  
  private static void addUnique(final /* List<RecordType> */Object list, final /* RecordType */Object type) {
    boolean _contains = list.contains(type);
    boolean _not = (!_contains);
    if (_not) {
      list.add(type);
    }
  }
  
  /**
   * Check if trace API is used.
   */
  public static boolean isTraceAPIUsed(final Advice advice) {
    EList<Collector> _collectors = advice.getCollectors();
    final Function1<Collector, Boolean> _function = (Collector it) -> {
      EList<Event> _events = it.getEvents();
      final Function1<Event, Boolean> _function_1 = (Event it_1) -> {
        EList<Value> _initializations = it_1.getInitializations();
        final Function1<Value, Boolean> _function_2 = (Value it_2) -> {
          return Boolean.valueOf(CommonJavaTemplates.isTraceAPIUsed(it_2));
        };
        return Boolean.valueOf(IterableExtensions.<Value>exists(_initializations, _function_2));
      };
      return Boolean.valueOf(IterableExtensions.<Event>exists(_events, _function_1));
    };
    return IterableExtensions.<Collector>exists(_collectors, _function);
  }
  
  private static boolean isTraceAPIUsed(final Value initialization) {
    boolean _switchResult = false;
    boolean _matched = false;
    if (initialization instanceof Literal) {
      _matched=true;
      _switchResult = false;
    }
    if (!_matched) {
      if (initialization instanceof ReferenceValue) {
        _matched=true;
        _switchResult = false;
      }
    }
    if (!_matched) {
      if (initialization instanceof InternalFunctionProperty) {
        _matched=true;
        boolean _switchResult_1 = false;
        InternalFunction _function = ((InternalFunctionProperty)initialization).getFunction();
        if (_function != null) {
          switch (_function) {
            case TRACE_ID:
              _switchResult_1 = true;
              break;
            case ORDER_INDEX:
              _switchResult_1 = true;
              break;
            default:
              _switchResult_1 = false;
              break;
          }
        } else {
          _switchResult_1 = false;
        }
        _switchResult = _switchResult_1;
      }
    }
    if (!_matched) {
      if (initialization instanceof AdviceParameter) {
        _matched=true;
        _switchResult = false;
      }
    }
    if (!_matched) {
      _switchResult = false;
    }
    return _switchResult;
  }
  
  /**
   * create code for trace information.
   */
  public static CharSequence createTraceId() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("TraceMetadata trace = TRACEREGISTRY.getTrace();");
    _builder.newLine();
    _builder.append("final boolean newTrace = trace == null;");
    _builder.newLine();
    _builder.append("if (newTrace) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("trace = TRACEREGISTRY.registerTrace();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("CTRLINST.newMonitoringRecord(trace);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("final long traceId = trace.getTraceId();");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Create data collection code based on event parameter assignments.
   */
  public static CharSequence createDataCollection(final Iterable<Collector> collectors, final Map<AdviceParameterDeclaration, Value> parameterAssignments) {
    final Map<CharSequence, CharSequence> data = new HashMap<CharSequence, CharSequence>();
    final Consumer<Collector> _function = (Collector it) -> {
      EList<Event> _events = it.getEvents();
      final Consumer<Event> _function_1 = (Event it_1) -> {
        CommonJavaTemplates.createData(it_1, data, parameterAssignments);
      };
      _events.forEach(_function_1);
    };
    collectors.forEach(_function);
    Collection<CharSequence> _values = data.values();
    return IterableExtensions.join(_values, "\n");
  }
  
  /**
   * Create data initialization for data collection.
   */
<<<<<<< 9b3334702959dbc026d0e6620756cf2a876e5ecc
  public static Map<CharSequence, CharSequence> createData(final /* Event */Object event, final Map<CharSequence, CharSequence> data, final /* Map<AdviceParameterDeclaration, Value> */Object parameterAssignments) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field type is undefined for the type Object"
      + "\nThe method or field createValueName is undefined for the type Object"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\ntype cannot be resolved"
      + "\ncollectAllDataProperties cannot be resolved"
      + "\nforEach cannot be resolved"
      + "\ninitializations cannot be resolved"
      + "\nget cannot be resolved"
      + "\ncreateValue cannot be resolved"
      + "\ntoString cannot be resolved"
      + "\ntype cannot be resolved"
      + "\ncreatePrimitiveTypeName cannot be resolved");
  }
  
  public static CharSequence createEvent(final /* Event */Object event) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field createValueName is undefined for the type Object"
      + "\ntype cannot be resolved"
      + "\nname cannot be resolved"
      + "\ntype cannot be resolved"
      + "\ncollectAllDataProperties cannot be resolved"
      + "\nmap cannot be resolved"
      + "\njoin cannot be resolved");
=======
  public static Map<CharSequence, CharSequence> createData(final Event event, final Map<CharSequence, CharSequence> data, final Map<AdviceParameterDeclaration, Value> parameterAssignments) {
    RecordType _type = event.getType();
    List<Property> _collectAllDataProperties = PropertyResolution.collectAllDataProperties(_type);
    final Procedure2<Property, Integer> _function = (Property property, Integer i) -> {
      try {
        EList<Value> _initializations = event.getInitializations();
        final Value value = _initializations.get((i).intValue());
        final CharSequence valueText = CommonJavaTemplates.createValue(value, parameterAssignments);
        Set<CharSequence> _keySet = data.keySet();
        final Function1<CharSequence, Boolean> _function_1 = (CharSequence it) -> {
          String _string = it.toString();
          String _string_1 = valueText.toString();
          return Boolean.valueOf(_string.equals(_string_1));
        };
        boolean _exists = IterableExtensions.<CharSequence>exists(_keySet, _function_1);
        boolean _not = (!_exists);
        if (_not) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("final ");
          Classifier _type_1 = property.getType();
          BaseType _type_2 = _type_1.getType();
          String _createPrimitiveTypeName = JavaTypeMapping.createPrimitiveTypeName(_type_2);
          _builder.append(_createPrimitiveTypeName, "");
          _builder.append(" ");
          CharSequence _createValueName = CommonJavaTemplates.createValueName(property);
          _builder.append(_createValueName, "");
          _builder.append(" = ");
          _builder.append(valueText, "");
          _builder.append(";");
          data.put(valueText, _builder);
        }
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    };
    IterableExtensions.<Property>forEach(_collectAllDataProperties, _function);
    return data;
  }
  
  public static CharSequence createEvent(final Event event) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("CTRLINST.newMonitoringRecord(new ");
    RecordType _type = event.getType();
    String _name = _type.getName();
    _builder.append(_name, "");
    _builder.append("(");
    RecordType _type_1 = event.getType();
    List<Property> _collectAllDataProperties = PropertyResolution.collectAllDataProperties(_type_1);
    final Function1<Property, CharSequence> _function = (Property it) -> {
      return CommonJavaTemplates.createValueName(it);
    };
    List<CharSequence> _map = ListExtensions.<Property, CharSequence>map(_collectAllDataProperties, _function);
    String _join = IterableExtensions.join(_map, ", ");
    _builder.append(_join, "");
    _builder.append("));");
    _builder.newLineIfNotEmpty();
    return _builder;
>>>>>>> minor regen
  }
  
  private static CharSequence createValueName(final /* kieker.develop.rl.recordLang.Property */Object property) {
    throw new Error("Unresolved compilation problems:"
      + "\nname cannot be resolved"
      + "\ntoFirstUpper cannot be resolved");
  }
  
  private static CharSequence createValue(final Value value, final Map<AdviceParameterDeclaration, Value> parameterAssignments) {
    try {
      CharSequence _switchResult = null;
      boolean _matched = false;
      if (value instanceof StringLiteral) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("\"");
        String _value = ((StringLiteral)value).getValue();
        _builder.append(_value, "");
        _builder.append("\"");
        _switchResult = _builder;
      }
      if (!_matched) {
        if (value instanceof IntLiteral) {
          _matched=true;
          int _value = ((IntLiteral)value).getValue();
          _switchResult = Integer.valueOf(_value).toString();
        }
      }
      if (!_matched) {
        if (value instanceof FloatLiteral) {
          _matched=true;
          Float _value = ((FloatLiteral)value).getValue();
          _switchResult = _value.toString();
        }
      }
      if (!_matched) {
        if (value instanceof ReferenceValue) {
          _matched=true;
          StringConcatenation _builder = new StringConcatenation();
          CharSequence _xifexpression = null;
          LocationQuery _query = ((ReferenceValue)value).getQuery();
          boolean _equals = Objects.equal(_query, null);
          if (_equals) {
            kieker.develop.al.aspectLang.Property _property = ((ReferenceValue)value).getProperty();
            _xifexpression = CommonJavaTemplates.createLocalProperty(_property);
          } else {
            _xifexpression = "MISSING";
          }
          _builder.append(_xifexpression, "");
          _switchResult = _builder;
        }
      }
      if (!_matched) {
        if (value instanceof InternalFunctionProperty) {
          _matched=true;
          CharSequence _switchResult_1 = null;
          InternalFunction _function = ((InternalFunctionProperty)value).getFunction();
          if (_function != null) {
            switch (_function) {
              case TIME:
                StringConcatenation _builder = new StringConcatenation();
                _builder.append("TIMESOURCE.getTime()");
                _switchResult_1 = _builder;
                break;
              case TRACE_ID:
                StringConcatenation _builder_1 = new StringConcatenation();
                _builder_1.append("trace.getTraceId()");
                _switchResult_1 = _builder_1;
                break;
              case ORDER_INDEX:
                StringConcatenation _builder_2 = new StringConcatenation();
                _builder_2.append("trace.getNextOrderId()");
                _switchResult_1 = _builder_2;
                break;
              default:
                break;
            }
          }
          _switchResult = _switchResult_1;
        }
      }
      if (!_matched) {
        if (value instanceof AdviceParameter) {
          _matched=true;
          AdviceParameterDeclaration _declaration = ((AdviceParameter)value).getDeclaration();
          Value _get = parameterAssignments.get(_declaration);
          _switchResult = CommonJavaTemplates.createValue(_get, parameterAssignments);
        }
      }
      if (!_matched) {
        Class<? extends Value> _class = value.getClass();
        String _name = _class.getName();
        String _plus = ("Illegal value type " + _name);
        throw new InternalErrorException(_plus);
      }
      return _switchResult;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
<<<<<<< 9b3334702959dbc026d0e6620756cf2a876e5ecc
  private static CharSequence createLocalProperty(final Property property) {
    throw new Error("Unresolved compilation problems:"
      + "\nReflectionProperty cannot be resolved to a type."
      + "\nRuntimeProperty cannot be resolved to a type."
      + "\nThe method or field createLocalReflectionProperty is undefined for the type Property"
      + "\nThe method or field createLocalRuntimeProperty is undefined for the type Property"
      + "\nUnreachable code: The case can never match. It is already handled by a previous condition.");
=======
  private static CharSequence createLocalProperty(final kieker.develop.al.aspectLang.Property property) {
    CharSequence _switchResult = null;
    boolean _matched = false;
    if (property instanceof ReflectionProperty) {
      _matched=true;
      _switchResult = CommonJavaTemplates.createLocalReflectionProperty(((ReflectionProperty)property));
    }
    if (!_matched) {
      if (property instanceof RuntimeProperty) {
        _matched=true;
        _switchResult = CommonJavaTemplates.createLocalRuntimeProperty(((RuntimeProperty)property));
      }
    }
    return _switchResult;
>>>>>>> minor regen
  }
  
  private static CharSequence createLocalReflectionProperty(final ReflectionProperty property) {
    CharSequence _switchResult = null;
    ReflectionFunction _function = property.getFunction();
    if (_function != null) {
      switch (_function) {
        case CLASS:
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("signature.getDeclaringTypeName()");
          _switchResult = _builder;
          break;
        case NAME:
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("signature.getName()");
          _switchResult = _builder_1;
          break;
        case RETURN_TYPE:
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append("signature.getReturnType().");
          _switchResult = _builder_2;
          break;
        case SIGNATURE:
          StringConcatenation _builder_3 = new StringConcatenation();
          _builder_3.append("signatureString");
          _switchResult = _builder_3;
          break;
        default:
          break;
      }
    }
    return _switchResult;
  }
  
  private static CharSequence createLocalRuntimeProperty(final RuntimeProperty property) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  public static CharSequence createEventRecording(final Advice advice, final InsertionPoint point) {
    EList<Collector> _collectors = advice.getCollectors();
    final Function1<Collector, Boolean> _function = (Collector it) -> {
      InsertionPoint _insertionPoint = it.getInsertionPoint();
      return Boolean.valueOf(Objects.equal(_insertionPoint, InsertionPoint.BEFORE));
    };
    Iterable<Collector> _filter = IterableExtensions.<Collector>filter(_collectors, _function);
    final Function1<Collector, String> _function_1 = (Collector it) -> {
      EList<Event> _events = it.getEvents();
      final Function1<Event, CharSequence> _function_2 = (Event it_1) -> {
        return CommonJavaTemplates.createEvent(it_1);
      };
      List<CharSequence> _map = ListExtensions.<Event, CharSequence>map(_events, _function_2);
      return IterableExtensions.join(_map, "\n");
    };
    Iterable<String> _map = IterableExtensions.<Collector, String>map(_filter, _function_1);
    return IterableExtensions.join(_map);
  }
  
  /**
   * Generate the correct type name. TODO is this sufficient?
   */
  public static String createTypeReference(final TypeReference reference) {
    boolean _isCollection = reference.isCollection();
    if (_isCollection) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Collection<");
      NamedType _reference = reference.getReference();
      String _name = _reference.getName();
      _builder.append(_name, "");
      _builder.append(">");
      return _builder.toString();
    } else {
      NamedType _reference_1 = reference.getReference();
      return _reference_1.getName();
    }
  }
}
