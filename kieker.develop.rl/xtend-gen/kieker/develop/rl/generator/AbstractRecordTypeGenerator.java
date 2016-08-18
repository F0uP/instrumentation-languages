package kieker.develop.rl.generator;

import de.cau.cs.se.geco.architecture.framework.IGenerator;
import kieker.develop.rl.generator.AbstractTypeGenerator;
import kieker.develop.rl.recordLang.RecordType;

@SuppressWarnings("all")
public abstract class AbstractRecordTypeGenerator extends AbstractTypeGenerator implements IGenerator<RecordType, CharSequence> {
  /**
   * Return the description for the preference dialog of the generator.
   */
  public abstract String getDescription();
  
  /**
   * Returns if the generator also supports abstract record types.
   */
  public abstract boolean supportsAbstractRecordType();
  
  /**
   * Primary code generation template.
   * 
   * @param type
   * 		one record type to be used to create monitoring record
   * @param author
   * 		generic author name for the record
   * @param version
   * 		generic kieker version for the record
   * @param headerComment
   *      comment inserted as header comment of the output
   */
  @Override
  public abstract CharSequence generate(final RecordType type);
}
