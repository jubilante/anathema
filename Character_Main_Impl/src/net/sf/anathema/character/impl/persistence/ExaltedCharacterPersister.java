package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_EXALTED_CHARACTER_ROOT;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.impl.model.ExaltedCharacter;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.concept.INatureProvider;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.AbstractSingleFileItemPersister;
import net.sf.anathema.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.framework.repository.AnathemaItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ExaltedCharacterPersister extends AbstractSingleFileItemPersister {

  private final RepositoryItemPersister repositoryItemPerister = new RepositoryItemPersister();
  private final CharacterDescriptionPersister descriptionPersister = new CharacterDescriptionPersister();
  private final CharacterStatisticPersister statisticsPersister;
  private final IItemType characterType;

  public ExaltedCharacterPersister(IItemType characterType, INatureProvider natureProvider, ICharacterGenerics generics) {
    this.characterType = characterType;
    this.statisticsPersister = new CharacterStatisticPersister(natureProvider, generics);
  }

  @Override
  public void save(OutputStream stream, IItem item) throws IOException {
    Element rootElement = DocumentHelper.createElement(TAG_EXALTED_CHARACTER_ROOT);
    repositoryItemPerister.save(rootElement, item);
    save(rootElement, (ICharacter) item.getItemData());
    DocumentUtilities.save(DocumentHelper.createDocument(rootElement), stream);
  }

  private void save(Element rootElement, ICharacter character) {
    descriptionPersister.save(rootElement, character.getDescription());
    if (character.hasStatistics()) {
      statisticsPersister.save(rootElement, character.getStatistics());
    }
  }

  @Override
  public IItem load(Document characterXml) throws PersistenceException {
    Element documentRoot = characterXml.getRootElement();
    ICharacter character = new ExaltedCharacter();
    IItem item = new AnathemaItem(characterType, character); 
    repositoryItemPerister.load(documentRoot, item);
    descriptionPersister.load(documentRoot, character.getDescription());
    statisticsPersister.load(documentRoot, character);
    return item;
  }
}