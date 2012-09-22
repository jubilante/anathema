package net.sf.anathema.character.equipment.impl.item.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import net.sf.anathema.character.equipment.ItemCost;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentTemplate;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.framework.itemdata.model.IItemDescription;
import net.sf.anathema.framework.itemdata.model.ItemDescription;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;

public class EquipmentTemplateEditModel implements IEquipmentTemplateEditModel {

  private final IItemDescription description = new ItemDescription();
  private final IEquipmentDatabase database;
  private IEquipmentTemplate editedTemplate;
  private final List<IEquipmentStats> statses = new ArrayList<IEquipmentStats>();
  private final Announcer<IChangeListener> statsChangeControl = Announcer.to(IChangeListener.class);
  private final Announcer<IChangeListener> magicalMaterialControl = Announcer.to(IChangeListener.class);
  private final Announcer<IChangeListener> compositionControl = Announcer.to(IChangeListener.class);
  private final Announcer<IChangeListener> costControl = Announcer.to(IChangeListener.class);
  private String editTemplateId;
  private MaterialComposition composition;
  private MagicalMaterial material;
  private ItemCost cost;

  public EquipmentTemplateEditModel(IEquipmentDatabase database) {
    this.database = database;
  }

  @Override
  public IItemDescription getDescription() {
    return description;
  }

  @Override
  public void setEditTemplate(String templateId) {
    Preconditions.checkNotNull(templateId);
    this.editTemplateId = templateId;
    editedTemplate = database.loadTemplate(templateId);
    getDescription().getName().setText(editedTemplate.getName());
    getDescription().getContent().setText(editedTemplate.getDescription());
    setMaterialComposition(editedTemplate.getComposition());
    setMagicalMaterial(editedTemplate.getMaterial());
    setCost(editedTemplate.getCost());
    statses.clear();
    addAll(statses, editedTemplate.getStats());
    fireStatsChangedEvent();
  }

  @Override
  public String getEditTemplateId() {
    return editTemplateId;
  }

  private void fireStatsChangedEvent() {
    statsChangeControl.announce().changeOccurred();
  }

  @Override
  public void setNewTemplate() {
    editTemplateId = null;
    editedTemplate = null;
    getDescription().getName().setText(null);
    getDescription().getContent().setText(new ITextPart[0]);
    setMaterialComposition(MaterialComposition.None);
    setCost( new ItemCost("Resources",0) );
    statses.clear();
    fireStatsChangedEvent();
  }
  
  @Override
  public void copyNewTemplate( String salt ) {
    editTemplateId += salt;
    getDescription().getName().setText(editTemplateId);
    editedTemplate = createTemplate();
    fireStatsChangedEvent();
  }

  @Override
  public boolean isDirty() {
    List<IEquipmentStats> currentStats = getAllCurrentStats();
    List<IEquipmentStats> previousStats = getAllPreviousStats();
    if (currentStats.size() != previousStats.size() || !currentStats.containsAll(previousStats)) {
      return true;
    }
    if (editedTemplate == null) {
      return !getDescription().getName().isEmpty() || !getDescription().getContent().isEmpty();
    }
    return !Objects.equal(editedTemplate.getName(), getDescription().getName().getText()) || !Objects.equal(editedTemplate.getDescription(), getDescription().getContent().getText()) ||
            !(editedTemplate.getComposition() == getMaterialComposition()) || !(editedTemplate.getMaterial() == getMagicalMaterial()) ||
            (getCost() != null && !getCost().equals(editedTemplate.getCost()));
  }

  private List<IEquipmentStats> getAllPreviousStats() {
    List<IEquipmentStats> allStats = new ArrayList<IEquipmentStats>();
    if (editedTemplate != null) {
      addAll(allStats, editedTemplate.getStats());
    }
    return allStats;
  }

  private List<IEquipmentStats> getAllCurrentStats() {
    List<IEquipmentStats> allStats = new ArrayList<IEquipmentStats>();
    allStats.addAll(statses);
    return allStats;
  }

  @Override
  public void addStatistics(IEquipmentStats stats) {
    statses.add(stats);
    fireStatsChangedEvent();
  }

  @Override
  public void removeStatistics(IEquipmentStats... stats) {
    for (IEquipmentStats stat : stats) {
      statses.remove(stat);
    }
    fireStatsChangedEvent();
  }

  @Override
  public IEquipmentStats[] getStats() {
    return statses.toArray(new IEquipmentStats[statses.size()]);
  }

  @Override
  public void addStatsChangeListener(IChangeListener changeListener) {
    statsChangeControl.addListener(changeListener);
  }

  @Override
  public IEquipmentTemplate createTemplate() {
    String name = getDescription().getName().getText();
    String descriptionText = getDescription().getContent().getText();
    EquipmentTemplate template = new EquipmentTemplate(name, descriptionText, composition, material,
            database.getCollectionFactory(), cost);
    for (IEquipmentStats stats : statses) {
      template.addStats(stats);
    }
    return template;
  }

  @Override
  public void addMagicalMaterialChangeListener(IChangeListener listener) {
    magicalMaterialControl.addListener(listener);
  }

  @Override
  public void addCompositionChangeListener(IChangeListener listener) {
    compositionControl.addListener(listener);
  }
  
  @Override
  public void addCostChangeListener(IChangeListener listener) {
    costControl.addListener(listener);
  }
  
  @Override
  public void setCost(ItemCost newCost) {
	if (newCost != null && newCost.equals(cost)) {
		return;
	}
	this.cost = newCost;
	costControl.announce().changeOccurred();
  }
  
  @Override
  public ItemCost getCost() {
	return cost;
  }

  @Override
  public MagicalMaterial getMagicalMaterial() {
    return material;
  }

  @Override
  public void setMagicalMaterial(MagicalMaterial material) {
    if (material == this.material) {
      return;
    }
    if (!composition.requiresMaterial() && material != null) {
      return;
    }
    this.material = material;
    magicalMaterialControl.announce().changeOccurred();
  }

  @Override
  public void setMaterialComposition(MaterialComposition composition) {
    if (composition == this.composition) {
      return;
    }
    this.composition = composition;
    if (composition.requiresMaterial()) {
      setMagicalMaterial(MagicalMaterial.Orichalcum);
    } else {
      setMagicalMaterial(null);
    }
    compositionControl.announce().changeOccurred();
  }

  @Override
  public MaterialComposition getMaterialComposition() {
    return composition;
  }

  @Override
  public void replaceStatistics(IEquipmentStats oldStats, IEquipmentStats newStats) {
    int oldIndex = statses.indexOf(oldStats);
    statses.remove(oldStats);
    statses.add(oldIndex, newStats);
    fireStatsChangedEvent();
  }
}