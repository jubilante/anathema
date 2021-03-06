package net.sf.anathema.herotype.solar.model.curse;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface VirtueFlawModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("GreatCurse");

  boolean isVirtueFlawChangable();

  VirtueFlaw getVirtueFlaw();

  TraitType[] getFlawVirtueTypes();

  void addVirtueFlawChangableListener(IBooleanValueChangedListener listener);
}