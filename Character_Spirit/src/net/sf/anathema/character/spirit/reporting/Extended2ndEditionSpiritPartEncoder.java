package net.sf.anathema.character.spirit.reporting;

import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractExtendedPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.RegisteredPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.NullBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.generic.type.CharacterType.SPIRIT;

@RegisteredPartEncoder(characterType = SPIRIT)
public class Extended2ndEditionSpiritPartEncoder extends AbstractExtendedPartEncoder {

  public Extended2ndEditionSpiritPartEncoder(IResources resources) {
    super(resources);
  }

  // TODO: This should be properly edited out, not just nulled out.
  @Override
  public ContentEncoder getGreatCurseEncoder(EncoderRegistry encoderRegistry, ReportSession session) {
    return new NullBoxContentEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportSession reportSession) {
    return null;
  }
}
