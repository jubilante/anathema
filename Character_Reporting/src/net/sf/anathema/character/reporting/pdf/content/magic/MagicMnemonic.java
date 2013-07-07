package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.main.magic.IMagicStats;

import java.util.List;

public interface MagicMnemonic {
  void removePrintMagic(IMagicStats printMagic);

  List<IMagicStats> getRemainingPrintMagic();
}
