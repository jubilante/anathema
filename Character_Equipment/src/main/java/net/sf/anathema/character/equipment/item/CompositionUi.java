package net.sf.anathema.character.equipment.item;

import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.framework.environment.Resources;

public class CompositionUi extends AbstractUIConfiguration<MaterialComposition> {
  private Resources resources;

  public CompositionUi(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected String labelForExistingValue(MaterialComposition value) {
    return resources.getString("MaterialComposition." + value.getId());
  }
}