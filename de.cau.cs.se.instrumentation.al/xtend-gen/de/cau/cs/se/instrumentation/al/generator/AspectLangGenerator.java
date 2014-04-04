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
package de.cau.cs.se.instrumentation.al.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.se.instrumantation.model.structure.Container;
import de.cau.cs.se.instrumantation.model.structure.Method;
import de.cau.cs.se.instrumantation.model.structure.MethodModifier;
import de.cau.cs.se.instrumantation.model.structure.Parameter;
import de.cau.cs.se.instrumantation.model.structure.Type;
import de.cau.cs.se.instrumantation.model.structure.TypeReference;
import de.cau.cs.se.instrumentation.al.aspectLang.Aspect;
import de.cau.cs.se.instrumentation.al.aspectLang.Collector;
import de.cau.cs.se.instrumentation.al.aspectLang.ContainerNode;
import de.cau.cs.se.instrumentation.al.aspectLang.InsertionPoint;
import de.cau.cs.se.instrumentation.al.aspectLang.LocationQuery;
import de.cau.cs.se.instrumentation.al.aspectLang.Node;
import de.cau.cs.se.instrumentation.al.aspectLang.Query;
import de.cau.cs.se.instrumentation.rl.recordLang.RecordType;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.MapExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Generates code from your model files on save.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#TutorialCodeGeneration
 */
@SuppressWarnings("all")
public class AspectLangGenerator implements IGenerator {
  private final Map<String,Collection<Aspect>> aspectMap = new Function0<Map<String,Collection<Aspect>>>() {
    public Map<String,Collection<Aspect>> apply() {
      HashMap<String,Collection<Aspect>> _hashMap = new HashMap<String, Collection<Aspect>>();
      return _hashMap;
    }
  }.apply();
  
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterator<Aspect> _filter = Iterators.<Aspect>filter(_allContents, Aspect.class);
    final Procedure1<Aspect> _function = new Procedure1<Aspect>() {
      public void apply(final Aspect it) {
        AspectLangGenerator.this.addAspect(AspectLangGenerator.this.aspectMap, it);
      }
    };
    IteratorExtensions.<Aspect>forEach(_filter, _function);
    final Procedure2<String,Collection<Aspect>> _function_1 = new Procedure2<String,Collection<Aspect>>() {
      public void apply(final String key, final Collection<Aspect> value) {
        boolean _matched = false;
        if (!_matched) {
          if (Objects.equal(key,"AspectJ")) {
            _matched=true;
            AspectLangGenerator.this.createAspectJConfiguration(value, fsa);
          }
        }
        if (!_matched) {
          if (Objects.equal(key,"J2EE")) {
            _matched=true;
            AspectLangGenerator.this.createJ2EEConfiguration(value, fsa);
          }
        }
        if (!_matched) {
          if (Objects.equal(key,"Spring")) {
            _matched=true;
            AspectLangGenerator.this.createSpringConfiguration(value, fsa);
          }
        }
      }
    };
    MapExtensions.<String, Collection<Aspect>>forEach(this.aspectMap, _function_1);
  }
  
  public void addAspect(final Map<String,Collection<Aspect>> map, final Aspect aspect) {
    Annotation _annotation = null;
    if (aspect!=null) {
      _annotation=aspect.getAnnotation();
    }
    String _name = null;
    if (_annotation!=null) {
      _name=_annotation.getName();
    }
    Collection<Aspect> list = map.get(_name);
    boolean _equals = Objects.equal(list, null);
    if (_equals) {
      ArrayList<Aspect> _arrayList = new ArrayList<Aspect>();
      list = _arrayList;
      Annotation _annotation_1 = null;
      if (aspect!=null) {
        _annotation_1=aspect.getAnnotation();
      }
      String _name_1 = null;
      if (_annotation_1!=null) {
        _name_1=_annotation_1.getName();
      }
      map.put(_name_1, list);
    }
    list.add(aspect);
  }
  
  public void createAspectJConfiguration(final Collection<Aspect> aspects, final IFileSystemAccess access) {
    try {
      final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      final Document doc = docBuilder.newDocument();
      final Element aspectjElement = doc.createElement("aspectj");
      doc.appendChild(aspectjElement);
      final Element weaverElement = doc.createElement("weaver");
      weaverElement.setAttribute("options", "");
      aspectjElement.appendChild(weaverElement);
      for (final Aspect aspect : aspects) {
        {
          final Element includeElement = doc.createElement("include");
          Query _query = aspect.getQuery();
          String _computeAspectJQuery = this.computeAspectJQuery(_query);
          includeElement.setAttribute("within", _computeAspectJQuery);
          weaverElement.appendChild(includeElement);
        }
      }
      final Element aspectsElement = doc.createElement("aspects");
      aspectjElement.appendChild(aspectsElement);
      for (final Aspect aspect_1 : aspects) {
        {
          EList<Collector> _collectors = aspect_1.getCollectors();
          final Function1<Collector,Boolean> _function = new Function1<Collector,Boolean>() {
            public Boolean apply(final Collector it) {
              InsertionPoint _insertionPoint = it.getInsertionPoint();
              boolean _equals = Objects.equal(_insertionPoint, InsertionPoint.BEFORE);
              return Boolean.valueOf(_equals);
            }
          };
          Iterable<Collector> _filter = IterableExtensions.<Collector>filter(_collectors, _function);
          this.createCollector(_filter, doc, aspectsElement);
          EList<Collector> _collectors_1 = aspect_1.getCollectors();
          final Function1<Collector,Boolean> _function_1 = new Function1<Collector,Boolean>() {
            public Boolean apply(final Collector it) {
              InsertionPoint _insertionPoint = it.getInsertionPoint();
              boolean _equals = Objects.equal(_insertionPoint, InsertionPoint.AFTER);
              return Boolean.valueOf(_equals);
            }
          };
          Iterable<Collector> _filter_1 = IterableExtensions.<Collector>filter(_collectors_1, _function_1);
          this.createCollector(_filter_1, doc, aspectsElement);
        }
      }
      final TransformerFactory transformerFactory = TransformerFactory.newInstance();
      final Transformer transformer = transformerFactory.newTransformer();
      StringWriter _stringWriter = new StringWriter();
      final StringWriter writer = _stringWriter;
      DOMSource _dOMSource = new DOMSource(doc);
      StreamResult _streamResult = new StreamResult(writer);
      transformer.transform(_dOMSource, _streamResult);
      String _string = writer.toString();
      access.generateFile("aop.xml", _string);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void createCollector(final Iterable<Collector> list, final Document doc, final Element aspectsElement) {
    final Element aspectElement = doc.createElement("aspect");
    final Function1<Collector,String> _function = new Function1<Collector,String>() {
      public String apply(final Collector it) {
        RecordType _type = it.getType();
        String _name = _type.getName();
        return _name;
      }
    };
    Iterable<String> _map = IterableExtensions.<Collector, String>map(list, _function);
    String _join = IterableExtensions.join(_map, ", ");
    String _plus = ("record types are " + _join);
    aspectElement.setAttribute("name", _plus);
    aspectsElement.appendChild(aspectElement);
  }
  
  public String computeAspectJQuery(final Query query) {
    StringConcatenation _builder = new StringConcatenation();
    LocationQuery _location = query.getLocation();
    CharSequence _computeLocation = this.computeLocation(_location);
    _builder.append(_computeLocation, "");
    _builder.append(" ");
    MethodModifier _modifier = query.getModifier();
    CharSequence _computeModifier = this.computeModifier(_modifier);
    _builder.append(_computeModifier, "");
    _builder.append(" ");
    CharSequence _xifexpression = null;
    Method _method = query.getMethod();
    boolean _notEquals = (!Objects.equal(_method, null));
    if (_notEquals) {
      Method _method_1 = query.getMethod();
      CharSequence _computeMethod = this.computeMethod(_method_1);
      _xifexpression = _computeMethod;
    } else {
      _xifexpression = "*";
    }
    _builder.append(_xifexpression, "");
    return _builder.toString();
  }
  
  public CharSequence computeLocation(final LocationQuery query) {
    StringConcatenation _builder = new StringConcatenation();
    Node _node = query.getNode();
    CharSequence _computeNode = this.computeNode(_node);
    _builder.append(_computeNode, "");
    String _xifexpression = null;
    LocationQuery _specialization = query.getSpecialization();
    boolean _notEquals = (!Objects.equal(_specialization, null));
    if (_notEquals) {
      LocationQuery _specialization_1 = query.getSpecialization();
      CharSequence _computeLocation = this.computeLocation(_specialization_1);
      String _plus = ("." + _computeLocation);
      _xifexpression = _plus;
    }
    _builder.append(_xifexpression, "");
    return _builder;
  }
  
  protected CharSequence _computeNode(final ContainerNode node) {
    StringConcatenation _builder = new StringConcatenation();
    Container _container = node.getContainer();
    String _name = _container.getName();
    _builder.append(_name, "");
    return _builder;
  }
  
  protected CharSequence _computeNode(final Node node) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#");
    return _builder;
  }
  
  public CharSequence computeModifier(final MethodModifier modifier) {
    StringConcatenation _builder = new StringConcatenation();
    String _xifexpression = null;
    boolean _notEquals = (!Objects.equal(modifier, null));
    if (_notEquals) {
      String _name = modifier.getName();
      _xifexpression = _name;
    } else {
      _xifexpression = "*";
    }
    _builder.append(_xifexpression, "");
    return _builder;
  }
  
  public CharSequence computeMethod(final Method method) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = method.getName();
    _builder.append(_name, "");
    _builder.append(" (");
    EList<Parameter> _parameters = method.getParameters();
    final Function1<Parameter,CharSequence> _function = new Function1<Parameter,CharSequence>() {
      public CharSequence apply(final Parameter it) {
        CharSequence _computeParameter = AspectLangGenerator.this.computeParameter(it);
        return _computeParameter;
      }
    };
    List<CharSequence> _map = ListExtensions.<Parameter, CharSequence>map(_parameters, _function);
    String _join = IterableExtensions.join(_map, ", ");
    _builder.append(_join, "");
    _builder.append(")");
    return _builder;
  }
  
  public CharSequence computeParameter(final Parameter parameter) {
    StringConcatenation _builder = new StringConcatenation();
    TypeReference _type = parameter.getType();
    CharSequence _computeType = this.computeType(_type);
    _builder.append(_computeType, "");
    _builder.append(" ");
    String _name = parameter.getName();
    _builder.append(_name, "");
    return _builder;
  }
  
  public CharSequence computeType(final TypeReference reference) {
    StringConcatenation _builder = new StringConcatenation();
    Type _type = reference.getType();
    String _name = _type.getName();
    _builder.append(_name, "");
    return _builder;
  }
  
  public String createSpringConfiguration(final Collection<Aspect> aspects, final IFileSystemAccess access) {
    return "TODO: auto-generated method stub";
  }
  
  public String createJ2EEConfiguration(final Collection<Aspect> aspects, final IFileSystemAccess access) {
    return "TODO: auto-generated method stub";
  }
  
  public CharSequence computeNode(final Node node) {
    if (node instanceof ContainerNode) {
      return _computeNode((ContainerNode)node);
    } else if (node != null) {
      return _computeNode(node);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(node).toString());
    }
  }
}