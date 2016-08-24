package kieker.develop.rl.generator.java.record.uid;

import kieker.develop.rl.generator.java.record.uid.EVisibility;
import kieker.develop.rl.recordLang.Classifier;

@SuppressWarnings("all")
public class Constructor {
  private String name;
  
  private EVisibility visibility;
  
  private Classifier[] signature;
  
  public Constructor(final String name, final EVisibility visibility, final Classifier[] signature) {
    this.name = name;
    this.visibility = visibility;
    this.signature = signature;
  }
  
  public String getName() {
    return this.name;
  }
  
  public EVisibility getVisibility() {
    return this.visibility;
  }
  
  public Classifier[] getSignature() {
    return this.signature;
  }
}
