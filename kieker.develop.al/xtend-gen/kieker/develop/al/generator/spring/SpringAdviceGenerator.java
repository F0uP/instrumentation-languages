package kieker.develop.al.generator.spring;

import de.cau.cs.se.geco.architecture.framework.IGenerator;
import kieker.develop.al.aspectLang.Advice;
import kieker.develop.al.aspectLang.Collector;
import kieker.develop.al.generator.CommonJavaTemplates;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class SpringAdviceGenerator implements IGenerator<Advice, CharSequence> {
  @Override
  public CharSequence generate(final Advice input) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _packageName = CommonJavaTemplates.getPackageName(input);
    _builder.append(_packageName, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    EList<Collector> _collectors = input.getCollectors();
    String _createRecordInputs = CommonJavaTemplates.createRecordInputs(_collectors);
    _builder.append(_createRecordInputs, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("MISSING");
    _builder.newLine();
    return _builder;
  }
}
