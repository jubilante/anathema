package net.sf.anathema.swing.hero.creation;

import net.sf.anathema.character.main.template.ICharacterExternalsTemplate;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.view.repository.ITemplateTypeAggregation;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class TemplateTypeAggregator {

  private final ITemplateRegistry characterTemplateRegistry;

  public TemplateTypeAggregator(ITemplateRegistry characterTemplateRegistry) {
    this.characterTemplateRegistry = characterTemplateRegistry;
  }

  public ITemplateTypeAggregation[] aggregateTemplates(ICharacterType type) {
    ICharacterExternalsTemplate[] templates = characterTemplateRegistry.getAllSupportedTemplates(type);
    Map<ITemplateType, TemplateTypeAggregation> aggregations = new LinkedHashMap<>();
    for (ICharacterExternalsTemplate template : templates) {
      TemplateTypeAggregation aggregation = aggregations.get(template.getTemplateType());
      if (aggregation == null) {
        aggregation = new TemplateTypeAggregation(template.getTemplateType(), template.getPresentationProperties());
        aggregations.put(template.getTemplateType(), aggregation);
      }
    }
    Collection<TemplateTypeAggregation> values = aggregations.values();
    return values.toArray(new ITemplateTypeAggregation[values.size()]);
  }
}