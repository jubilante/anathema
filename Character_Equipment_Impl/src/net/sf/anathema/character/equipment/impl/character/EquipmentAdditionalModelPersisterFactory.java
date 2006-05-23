package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;

public class EquipmentAdditionalModelPersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister() {
    return new EquipmentAdditionalModelPersister();
  }
}