package net.sf.anathema.character.lunar.reporting;

import net.sf.anathema.character.lunar.reporting.layout.LunarAdditionalPageEncoder;
import net.sf.anathema.character.lunar.reporting.rendering.anima.AnimaEncoderFactory;
import net.sf.anathema.character.lunar.reporting.rendering.greatcurse.GreatCurse2ndEditionEncoder;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.layout.extended.AbstractExtendedPartEncoder;
import net.sf.anathema.character.reporting.pdf.layout.extended.RegisteredPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.generic.type.CharacterType.LUNAR;

@RegisteredPartEncoder(characterType = LUNAR)
public class Extended2ndEditionLunarPartEncoder extends AbstractExtendedPartEncoder {

  public Extended2ndEditionLunarPartEncoder(IResources resources) {
    super(resources);
  }

  @Override
  public ContentEncoder getGreatCurseEncoder(EncoderRegistry encoderRegistry, ReportSession session) {
    return new GreatCurse2ndEditionEncoder(getResources());
  }

  @Override
  public ContentEncoder getAnimaEncoder(ReportSession reportSession) {
    BasicContent content = reportSession.createContent(BasicContent.class);
    return new AnimaEncoderFactory().create(getResources(), content);
  }

  @Override
  public PageEncoder[] getAdditionalPages(EncoderRegistry encoderRegistry, PageSize pageSize) {
    return new PageEncoder[]{new LunarAdditionalPageEncoder(encoderRegistry, getResources(), pageSize)};
  }
}
