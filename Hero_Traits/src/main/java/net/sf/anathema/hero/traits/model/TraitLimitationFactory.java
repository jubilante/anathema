package net.sf.anathema.hero.traits.model;

import net.sf.anathema.character.main.template.ITraitLimitation;
import net.sf.anathema.character.main.traits.limitation.AgeBasedLimitation;
import net.sf.anathema.character.main.traits.limitation.EssenceBasedLimitation;
import net.sf.anathema.character.main.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.hero.traits.template.LimitationTemplate;
import net.sf.anathema.hero.traits.template.LimitationType;

public class TraitLimitationFactory {
  public static ITraitLimitation createLimitation(LimitationTemplate limitation) {
    if (limitation.type == LimitationType.Static) {
      return new StaticTraitLimitation(limitation.value);
    }
    if (limitation.type == LimitationType.Age) {
      return new AgeBasedLimitation(limitation.value);
    }
    return new EssenceBasedLimitation();
  }
}
