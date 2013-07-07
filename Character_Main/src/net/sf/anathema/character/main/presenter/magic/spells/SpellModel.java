package net.sf.anathema.character.main.presenter.magic.spells;

import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.character.main.template.magic.ISpellMagicTemplate;
import net.sf.anathema.hero.model.Hero;

public abstract class SpellModel {

  private Hero hero;

  protected SpellModel(Hero hero) {
    this.hero = hero;
  }

  public abstract CircleType[] getCircles();

  protected final ISpellMagicTemplate getSpellMagicTemplate() {
    return hero.getTemplate().getMagicTemplate().getSpellMagic();
  }
}