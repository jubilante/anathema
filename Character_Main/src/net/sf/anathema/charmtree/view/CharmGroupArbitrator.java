package net.sf.anathema.charmtree.view;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.ICharmGroup;

public interface CharmGroupArbitrator {

  ICharm[] getCharms(ICharmGroup charmGroup);
}