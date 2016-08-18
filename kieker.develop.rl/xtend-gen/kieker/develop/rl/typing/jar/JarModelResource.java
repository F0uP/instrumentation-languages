/**
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
 */
package kieker.develop.rl.typing.jar;

import com.google.common.base.Objects;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import kieker.develop.rl.recordLang.BaseType;
import kieker.develop.rl.recordLang.BooleanLiteral;
import kieker.develop.rl.recordLang.Classifier;
import kieker.develop.rl.recordLang.ComplexType;
import kieker.develop.rl.recordLang.Constant;
import kieker.develop.rl.recordLang.FloatLiteral;
import kieker.develop.rl.recordLang.IntLiteral;
import kieker.develop.rl.recordLang.Literal;
import kieker.develop.rl.recordLang.Model;
import kieker.develop.rl.recordLang.Property;
import kieker.develop.rl.recordLang.RecordLangFactory;
import kieker.develop.rl.recordLang.RecordType;
import kieker.develop.rl.recordLang.StringLiteral;
import kieker.develop.rl.recordLang.TemplateType;
import kieker.develop.rl.recordLang.Type;
import kieker.develop.rl.typing.BaseTypes;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.xtext.xbase.lib.CollectionExtensions;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * broadly based on org.spp.cocome.behavior.pcm.handler.PCMModelResource
 * 
 * @author Yannic Kropp -- initial contribution
 * @author Reiner Jung
 */
@SuppressWarnings("all")
public class JarModelResource extends ResourceImpl {
  private final RecordLangFactory rlFactory = RecordLangFactory.eINSTANCE;
  
  private IProject project;
  
  private final Collection<Type> modelTypes = new ArrayList<Type>();
  
  /**
   * Integrate a foreign model.
   * 
   * @param uri of the foreign model
   * @param applicationModel the application model
   */
  public JarModelResource(final URI uri, final IProject project) {
    super(uri);
    this.project = project;
  }
  
  /**
   * Return an EObject with the name specified by the uriFragment.
   * 
   * @param uriFragment
   * 
   * @return the EObject identified by the uriFragment or null if no such object exists.
   */
  @Override
  public EObject getEObject(final String uriFragment) {
    EList<EObject> _contents = this.getContents();
    boolean _isEmpty = _contents.isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      EList<EObject> _contents_1 = this.getContents();
      for (final EObject object : _contents_1) {
        if ((object instanceof Model)) {
          final EList<ComplexType> types = ((Model) object).getTypes();
          final Function1<ComplexType, Boolean> _function = (ComplexType type) -> {
            return Boolean.valueOf((type.getName().endsWith(("." + uriFragment)) || 
              type.getName().equals(uriFragment)));
          };
          final ComplexType result = IterableExtensions.<ComplexType>findFirst(types, _function);
          boolean _notEquals = (!Objects.equal(result, null));
          if (_notEquals) {
            return result;
          }
        }
      }
      return super.getEObject(uriFragment);
    } else {
      return super.getEObject(uriFragment);
    }
  }
  
  /**
   * Compute the uriFragment for a given EObject.
   * 
   * @param eObject the object the fragment is computed for.
   * 
   * @return returns the uriFragment for the given object.
   */
  @Override
  public String getURIFragment(final EObject eObject) {
    if ((eObject instanceof Type)) {
      return ((Type) eObject).getName();
    } else {
      return super.getURIFragment(eObject);
    }
  }
  
  /**
   * load the resource iff it is not already loaded.
   */
  @Override
  public void load(final Map<?, ?> options) throws IOException {
    if ((!this.isLoaded)) {
      this.doLoad(null, null);
    }
  }
  
  /**
   * Saving this resource is not allowed, as it is a virtual resource.
   */
  @Override
  public void save(final Map<?, ?> options) throws IOException {
    throw new UnsupportedOperationException();
  }
  
  /**
   * This routine is called from ResourceImpl load after the load method above is triggered.
   * It initializes the primitive type mirror.
   * 
   * @param inputStream
   * @param options
   * 
   * @throws IOException
   */
  @Override
  public void doLoad(final InputStream inputStream, final Map<?, ?> options) throws IOException {
    URI _uRI = this.getURI();
    boolean _notEquals = (!Objects.equal(_uRI, null));
    if (_notEquals) {
      this.createModel();
    } else {
      try {
        throw new IOException("URI not set on ForeignModelResource.onLoad");
      } catch (final Throwable _t) {
        if (_t instanceof IOException) {
          final IOException e = (IOException)_t;
          e.printStackTrace();
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    }
  }
  
  /**
   * Return prepared set over all inferred types.
   */
  public Iterable<Type> getAllTypes() {
    return this.modelTypes;
  }
  
  /**
   * Create an result model for a given ecore model.
   */
  private synchronized Boolean createModel() {
    try {
      boolean _xifexpression = false;
      if ((!this.isLoaded)) {
        boolean _xblockexpression = false;
        {
          final IJavaProject javaProject = JavaCore.create(this.project);
          final IType iface = javaProject.findType("kieker.common.record.IMonitoringRecord");
          boolean _xifexpression_1 = false;
          boolean _notEquals = (!Objects.equal(iface, null));
          if (_notEquals) {
            boolean _xblockexpression_1 = false;
            {
              final ITypeHierarchy hierarchy = iface.newTypeHierarchy(javaProject, null);
              IType[] _allTypes = hierarchy.getAllTypes();
              final Function1<IType, Boolean> _function = (IType it) -> {
                boolean _xblockexpression_2 = false;
                {
                  final String name = it.getFullyQualifiedName();
                  _xblockexpression_2 = (!((name.equals("java.io.Serializable") || 
                    name.equals("java.lang.Comparable")) || 
                    name.equals("java.lang.Object")));
                }
                return Boolean.valueOf(_xblockexpression_2);
              };
              final Iterable<IType> types = IterableExtensions.<IType>filter(((Iterable<IType>)Conversions.doWrapArray(_allTypes)), _function);
              final HashMap<String, Model> models = new HashMap<String, Model>();
              final Consumer<IType> _function_1 = (IType type) -> {
                IPackageFragment _packageFragment = type.getPackageFragment();
                String _elementName = _packageFragment.getElementName();
                Model _get = models.get(_elementName);
                boolean _equals = Objects.equal(_get, null);
                if (_equals) {
                  IPackageFragment _packageFragment_1 = type.getPackageFragment();
                  String _elementName_1 = _packageFragment_1.getElementName();
                  Model _createModel = this.createModel(type);
                  models.put(_elementName_1, _createModel);
                }
              };
              types.forEach(_function_1);
              final HashMap<IType, ComplexType> typeMap = new HashMap<IType, ComplexType>();
              final Consumer<IType> _function_2 = (IType type) -> {
                final ComplexType modelType = this.createType(type);
                IPackageFragment _packageFragment = type.getPackageFragment();
                String _elementName = _packageFragment.getElementName();
                Model _get = models.get(_elementName);
                EList<ComplexType> _types = _get.getTypes();
                _types.add(modelType);
                typeMap.put(type, modelType);
                this.modelTypes.add(modelType);
              };
              types.forEach(_function_2);
              final Consumer<IType> _function_3 = (IType type) -> {
                this.linkType(type, typeMap);
              };
              types.forEach(_function_3);
              boolean _xifexpression_2 = false;
              Collection<Model> _values = models.values();
              boolean _notEquals_1 = (!Objects.equal(_values, null));
              if (_notEquals_1) {
                EList<EObject> _contents = this.getContents();
                Collection<Model> _values_1 = models.values();
                _xifexpression_2 = _contents.addAll(_values_1);
              }
              _xblockexpression_1 = _xifexpression_2;
            }
            _xifexpression_1 = _xblockexpression_1;
          } else {
            final IMarker m = this.project.createMarker(IMarker.PROBLEM);
            m.setAttribute(IMarker.LINE_NUMBER, 0);
            m.setAttribute(IMarker.MESSAGE, "The project does not contain the interface kieker.common.record.IMonitoringRecord");
            m.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_LOW);
            m.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO);
          }
          _xblockexpression = _xifexpression_1;
        }
        _xifexpression = _xblockexpression;
      }
      return Boolean.valueOf(_xifexpression);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Link types.
   */
  private void linkType(final IType type, final Map<IType, ComplexType> typeMap) {
    try {
      final ComplexType modelType = typeMap.get(type);
      boolean _matched = false;
      if (modelType instanceof TemplateType) {
        _matched=true;
        final ITypeHierarchy hierarchy = type.newSupertypeHierarchy(null);
        IType[] _superInterfaces = hierarchy.getSuperInterfaces(type);
        final Consumer<IType> _function = (IType iface) -> {
          final ComplexType template = typeMap.get(iface);
          boolean _notEquals = (!Objects.equal(template, null));
          if (_notEquals) {
            EList<TemplateType> _parents = ((TemplateType)modelType).getParents();
            _parents.add(((TemplateType) template));
          }
        };
        ((List<IType>)Conversions.doWrapArray(_superInterfaces)).forEach(_function);
      }
      if (!_matched) {
        if (modelType instanceof RecordType) {
          _matched=true;
          final ITypeHierarchy hierarchy = type.newSupertypeHierarchy(null);
          IType[] _superInterfaces = hierarchy.getSuperInterfaces(type);
          final Consumer<IType> _function = (IType iface) -> {
            final ComplexType template = typeMap.get(iface);
            boolean _notEquals = (!Objects.equal(template, null));
            if (_notEquals) {
              EList<TemplateType> _parents = ((RecordType)modelType).getParents();
              _parents.add(((TemplateType) template));
            }
          };
          ((List<IType>)Conversions.doWrapArray(_superInterfaces)).forEach(_function);
          final IType superType = hierarchy.getSuperclass(type);
          boolean _notEquals = (!Objects.equal(superType, null));
          if (_notEquals) {
            ComplexType _get = typeMap.get(superType);
            ((RecordType)modelType).setParent(((RecordType) _get));
          }
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private Model createModel(final IType type) {
    final Model model = this.rlFactory.createModel();
    IPackageFragment _packageFragment = type.getPackageFragment();
    String _elementName = _packageFragment.getElementName();
    model.setName(_elementName);
    return model;
  }
  
  private ComplexType createType(final IType type) {
    try {
      ComplexType _xifexpression = null;
      boolean _isInterface = type.isInterface();
      if (_isInterface) {
        _xifexpression = this.createTemplateType(type);
      } else {
        _xifexpression = this.createRecordType(type);
      }
      return _xifexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private ComplexType createRecordType(final IType type) {
    final RecordType result = this.rlFactory.createRecordType();
    String _elementName = type.getElementName();
    result.setName(_elementName);
    this.createAttributes(result, type);
    return result;
  }
  
  private ComplexType createTemplateType(final IType type) {
    final TemplateType result = this.rlFactory.createTemplateType();
    String _elementName = type.getElementName();
    result.setName(_elementName);
    this.createAttributes(result, type);
    return result;
  }
  
  public void createAttributes(final ComplexType result, final IType type) {
    try {
      IField[] _fields = type.getFields();
      final Consumer<IField> _function = (IField field) -> {
        try {
          if (((Flags.isPublic(field.getFlags()) && Flags.isStatic(field.getFlags())) && Flags.isFinal(field.getFlags()))) {
            if ((((!field.getElementName().startsWith("TYPE_SIZE")) && 
              (!"TYPES".equals(field.getElementName()))) && 
              (!field.getElementName().startsWith("CACHED_KIEKERRECORDS")))) {
              final Constant constant = this.rlFactory.createConstant();
              String _elementName = field.getElementName();
              constant.setName(_elementName);
              String _typeSignature = field.getTypeSignature();
              Classifier _createType = this.createType(_typeSignature);
              constant.setType(_createType);
              Object _constant = field.getConstant();
              Literal _createLiteral = this.createLiteral(_constant);
              constant.setValue(_createLiteral);
              EList<Constant> _constants = result.getConstants();
              _constants.add(constant);
            }
          } else {
            if (((Flags.isPrivate(field.getFlags()) && Flags.isFinal(field.getFlags())) && (!Flags.isStatic(field.getFlags())))) {
              final Property property = this.rlFactory.createProperty();
              String _elementName_1 = field.getElementName();
              property.setName(_elementName_1);
              String _typeSignature_1 = field.getTypeSignature();
              Classifier _createType_1 = this.createType(_typeSignature_1);
              property.setType(_createType_1);
              EList<Property> _properties = result.getProperties();
              _properties.add(property);
            }
          }
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      ((List<IField>)Conversions.doWrapArray(_fields)).forEach(_function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private Literal createLiteral(final Object object) {
    Object _switchResult = null;
    boolean _matched = false;
    if (object instanceof Integer) {
      _matched=true;
      final IntLiteral result = this.rlFactory.createIntLiteral();
      result.setValue(((Integer) object).intValue());
      return result;
    }
    if (!_matched) {
      if (object instanceof Byte) {
        _matched=true;
        final IntLiteral result = this.rlFactory.createIntLiteral();
        result.setValue(((Byte) object).byteValue());
        return result;
      }
    }
    if (!_matched) {
      if (object instanceof Boolean) {
        _matched=true;
        final BooleanLiteral result = this.rlFactory.createBooleanLiteral();
        result.setValue(((Boolean)object));
        return result;
      }
    }
    if (!_matched) {
      if (object instanceof Character) {
        _matched=true;
        final StringLiteral result = this.rlFactory.createStringLiteral();
        String _string = ((Character)object).toString();
        result.setValue(_string);
        return result;
      }
    }
    if (!_matched) {
      if (object instanceof Double) {
        _matched=true;
        final FloatLiteral result = this.rlFactory.createFloatLiteral();
        float _floatValue = ((Double)object).floatValue();
        result.setValue(Float.valueOf(_floatValue));
        return result;
      }
    }
    if (!_matched) {
      if (object instanceof Float) {
        _matched=true;
        final FloatLiteral result = this.rlFactory.createFloatLiteral();
        result.setValue(((Float)object));
        return result;
      }
    }
    if (!_matched) {
      if (object instanceof Long) {
        _matched=true;
        final IntLiteral result = this.rlFactory.createIntLiteral();
        int _intValue = ((Long)object).intValue();
        result.setValue(_intValue);
        return result;
      }
    }
    if (!_matched) {
      if (object instanceof Short) {
        _matched=true;
        final IntLiteral result = this.rlFactory.createIntLiteral();
        short _shortValue = ((Short)object).shortValue();
        result.setValue(_shortValue);
        return result;
      }
    }
    if (!_matched) {
      if (object instanceof String) {
        _matched=true;
        final StringLiteral result = this.rlFactory.createStringLiteral();
        result.setValue(((String)object));
        return result;
      }
    }
    if (!_matched) {
      _switchResult = null;
    }
    return ((Literal)_switchResult);
  }
  
  private Classifier createType(final String typeId) {
    final Classifier classifier = this.rlFactory.createClassifier();
    switch (typeId) {
      case "B":
        BaseType _type = BaseTypes.BYTE.getType();
        classifier.setType(_type);
        break;
      case "C":
        BaseType _type_1 = BaseTypes.CHAR.getType();
        classifier.setType(_type_1);
        break;
      case "D":
        BaseType _type_2 = BaseTypes.DOUBLE.getType();
        classifier.setType(_type_2);
        break;
      case "F":
        BaseType _type_3 = BaseTypes.FLOAT.getType();
        classifier.setType(_type_3);
        break;
      case "I":
        BaseType _type_4 = BaseTypes.INT.getType();
        classifier.setType(_type_4);
        break;
      case "J":
        BaseType _type_5 = BaseTypes.LONG.getType();
        classifier.setType(_type_5);
        break;
      case "S":
        BaseType _type_6 = BaseTypes.SHORT.getType();
        classifier.setType(_type_6);
        break;
      case "Z":
        BaseType _type_7 = BaseTypes.BOOLEAN.getType();
        classifier.setType(_type_7);
        break;
      case "Ljava.lang.String;":
        BaseType _type_8 = BaseTypes.STRING.getType();
        classifier.setType(_type_8);
        break;
    }
    return classifier;
  }
  
  /**
   * find all types in a package fragment recursively.
   */
  private Collection<IType> findAllTypes(final IPackageFragment fragment) {
    try {
      final ArrayList<IType> result = new ArrayList<IType>();
      IJavaElement[] _children = fragment.getChildren();
      final Consumer<IJavaElement> _function = (IJavaElement element) -> {
        try {
          boolean _matched = false;
          if (element instanceof IPackageFragment) {
            _matched=true;
            String _elementName = ((IPackageFragment)element).getElementName();
            boolean _startsWith = _elementName.startsWith("kieker");
            if (_startsWith) {
              Collection<IType> _findAllTypes = this.findAllTypes(((IPackageFragment)element));
              result.addAll(_findAllTypes);
            }
          }
          if (!_matched) {
            if (element instanceof IClassFile) {
              _matched=true;
              IType _type = ((IClassFile)element).getType();
              result.add(_type);
            }
          }
          if (!_matched) {
            if (element instanceof ICompilationUnit) {
              _matched=true;
              IType[] _types = ((ICompilationUnit)element).getTypes();
              CollectionExtensions.<IType>addAll(result, _types);
            }
          }
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      ((List<IJavaElement>)Conversions.doWrapArray(_children)).forEach(_function);
      return result;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
