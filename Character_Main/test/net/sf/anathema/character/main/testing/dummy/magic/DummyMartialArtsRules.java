package net.sf.anathema.character.main.testing.dummy.magic;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.main.template.magic.MartialArtsCharmConfiguration;
import net.sf.anathema.character.main.template.magic.MartialArtsRules;

public class DummyMartialArtsRules implements MartialArtsRules {

  @Override
  public boolean isCharmAllowed(ICharm martialArtsCharm, MartialArtsCharmConfiguration charmConfiguration, boolean isExperienced) {
    return false;
  }

  @Override
  public MartialArtsLevel getStandardLevel() {
    return null;
  }

  @Override
  public void setHighLevelAtCreation(boolean highLevelAtCreation) {
    //nothing to do
  }
}