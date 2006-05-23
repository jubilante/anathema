package net.sf.anathema.character.equipment.impl.character.natural;

import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.generic.equipment.weapon.IWeapon;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class Clinch implements IWeapon {

  public int getAccuracy() {
    return 0;
  }

  public int getDamage() {
    return 0;
  }

  public HealthType getDamageType() {
    return HealthType.Bashing;
  }

  public Integer getDefence() {
    return 0;
  }

  public Integer getRange() {
    return null;
  }

  public Integer getRate() {
    return 1;
  }

  public int getSpeed() {
    return 6;
  }

  public IIdentificate[] getTags() {
    return new IIdentificate[] { WeaponTag.ClinchEnhancer, WeaponTag.Natural, WeaponTag.Piercing };
  }

  public ITraitType getTraitType() {
    return AbilityType.MartialArts;
  }

  public IIdentificate getName() {
    return new Identificate("Clinch"); //$NON-NLS-1$
  }
}