package net.sf.anathema.hero.dummy.template;

import net.sf.anathema.character.main.template.ConfiguredModel;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.template.TemplateType;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.main.template.presentation.IPresentationProperties;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.ArrayList;
import java.util.List;

public class SimpleDummyCharacterTemplate implements HeroTemplate {

  private final String subtype;
  private final CharacterType type;

  public SimpleDummyCharacterTemplate(CharacterType type, String subtype) {
    this.type = type;
    this.subtype = subtype;
  }

  @Override
  public BonusPointCosts getBonusPointCosts() {
    return null;
  }

  @Override
  public ICreationPoints getCreationPoints() {
    return new TestCreationPoints();
  }

  @Override
  public IExperiencePointCosts getExperienceCost() {
    return null;
  }

  @Override
  public IPresentationProperties getPresentationProperties() {
    return null;
  }

  @Override
  public ITemplateType getTemplateType() {
    if (subtype == null) {
      return new TemplateType(type);
    }
    return new TemplateType(type, new SimpleIdentifier(subtype));
  }

  @Override
  public List<ConfiguredModel> getModels() {
    return new ArrayList<>();
  }
}