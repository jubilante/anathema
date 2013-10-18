package net.sf.anathema.charmdatabase.management.model;

import net.sf.anathema.character.main.magic.basic.attribute.MagicAttribute;
import net.sf.anathema.character.main.magic.basic.cost.ICostList;
import net.sf.anathema.character.main.magic.basic.source.SourceBook;
import net.sf.anathema.character.main.magic.basic.source.SourceBookImpl;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.duration.Duration;
import net.sf.anathema.character.main.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.type.ICharmTypeModel;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface ICharmEditModel {

	static final String CUSTOM_SOURCE_STRING = "Custom";
	static final SourceBook CUSTOM_SOURCE = new SourceBookImpl(CUSTOM_SOURCE_STRING);
	
	static final boolean EDIT_ENABLED = true;
	
	void setNewTemplate();
	
	void setEditCharm(Charm charm);
	
	boolean isDirty();

	ITextualDescription getDescription();

	ITextualDescription getName();
	
	
	Identifier getCharmType();
	
	void setCharmType(Identifier newValue);
	
	void addCharmTypeChangedListening(ChangeListener listener);
	

	Identifier getCharmGroup();
	
	void setCharmGroup(Identifier newValue);
	
	void addCharmGroupChangedListening(ChangeListener listener);
	
	CharmLearnPrerequisite[] getCharmPrerequisites();
	
	void setCharmPrerequisites(CharmLearnPrerequisite[] charms);
	
	void addCharmPrerequisitesChangedListening(ChangeListener listener);
	
	
	ValuedTraitType[] getCharmTraitMinimums();
	
	void setCharmTraitMinimums(ValuedTraitType[] traits);
	
	void addCharmTraitMinimumsChangedListening(ChangeListener listener);
	
	
	
	ICostList getCharmTemporaryCosts();
	
	void setCharmTemporaryCosts(ICostList costs);
	
	void addCharmTemporaryCostsChangedListening(ChangeListener listener);
	
	
	
	MagicAttribute[] getCharmKeywords();
	
	void setCharmKeywords(MagicAttribute[] keywords);
	
	void addCharmKeywordsChangedListening(ChangeListener listener);
	
	
	
	
	ICharmTypeModel getCharmActionType();
	
	void setCharmActionType(ICharmTypeModel type);
	
	void addCharmActionTypeChangedListening(ChangeListener listener);
	
	
	
	
	Duration getCharmDuration();
	
	void setCharmDuration(Duration duration);
	
	void addCharmDurationTypeChangedListening(ChangeListener listener);
	
	
	SourceBook[] getCharmSources();
	
	void setCharmSources(SourceBook[] sources);
	
	void addCharmSourcesChangedListening(ChangeListener listener);
	
	
	void addCanonCharmSelectionListening(ChangeListener listener);
	
	void addCustomCharmSelectionListening(ChangeListener listener);
}
