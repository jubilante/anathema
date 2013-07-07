package net.sf.anathema.character.reporting.pdf.rendering.general.stats;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.main.util.IStats;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.AbstractTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.TableCell;
import org.apache.commons.lang3.ArrayUtils;

public abstract class AbstractStatsTableEncoder<T extends IStats, C> extends AbstractTableEncoder<C> {

  private final boolean suppressHeaderLine;

  public AbstractStatsTableEncoder() {
    this(false);
  }

  public AbstractStatsTableEncoder(boolean suppressHeaderLine) {
    this.suppressHeaderLine = suppressHeaderLine;
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, C content, Bounds bounds) {
    IStatsGroup<T>[] groups = createStatsGroups(content);
    float[] columnWidths = calculateColumnWidths(groups);
    PdfPTable table = new PdfPTable(columnWidths);
    table.setTotalWidth(bounds.width);
    if (!suppressHeaderLine) {
      encodeHeaderLine(graphics, table, groups);
    }
    encodeContent(graphics, table, content, bounds);
    return table;
  }

  protected abstract void encodeContent(SheetGraphics graphics, PdfPTable table, C content, Bounds bounds);

  protected abstract IStatsGroup<T>[] createStatsGroups(C content);

  protected final void encodeHeaderLine(SheetGraphics graphics, PdfPTable table, IStatsGroup<T>[] groups) {
    for (int index = 0; index < groups.length; index++) {
      Font usedFont = index == 0 ? createFont(graphics) : createHeaderFont(graphics);
      table.addCell(createHeaderCell(groups[index].getTitle(), groups[index].getColumnCount(), index != groups.length - 1, usedFont));
    }
  }

  protected final void encodeContentLine(SheetGraphics graphics, PdfPTable table, IStatsGroup<T>[] groups, T stats) {
    for (int index = 0; index < groups.length; index++) {
      if (index != 0) {
        table.addCell(createSpaceCell(graphics));
      }
      groups[index].addContent(table, createFont(graphics), stats);
    }
  }

  protected final void encodeSectionLine(SheetGraphics graphics, PdfPTable table, String sectionName) {
    int columnCount = table.getAbsoluteWidths().length;
    Phrase phrase = new Phrase(sectionName, createSectionFont(graphics));
    PdfPCell cell = new TableCell(phrase, Rectangle.NO_BORDER);
    cell.setPaddingTop(3);
    cell.setPaddingLeft(0.75f);
    cell.setColspan(columnCount);
    table.addCell(cell);
  }

  protected final float[] calculateColumnWidths(IStatsGroup<T>[] groups) {
    Float[] columnWidths = new Float[0];
    for (IStatsGroup<T> group : groups) {
      if (columnWidths.length != 0) {
        columnWidths = net.sf.anathema.lib.lang.ArrayUtilities.concat(Float.class, columnWidths, new Float(0.2));
      }
      columnWidths = net.sf.anathema.lib.lang.ArrayUtilities.concat(Float.class, columnWidths, group.getColumnWeights());
    }
    return ArrayUtils.toPrimitive(columnWidths);
  }

  protected PdfPCell createSpaceCell(SheetGraphics graphics) {
    PdfPCell cell = new PdfPCell(new Phrase("", createFont(graphics)));
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
  }

  private PdfPCell createHeaderCell(String text, int columnSpan, boolean useSpaceCell, Font textFont) {
    PdfPCell cell = new PdfPCell(new Phrase(text, textFont));
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setColspan(useSpaceCell ? columnSpan + 1 : columnSpan);
    cell.setPaddingLeft(0);
    cell.setPaddingRight(0);
    cell.setVerticalAlignment( PdfPCell.ALIGN_BOTTOM );
    return cell;
  }

  private Font createSectionFont(SheetGraphics graphics) {
    Font sectionFont = createFont(graphics);
    sectionFont.setStyle(Font.BOLD);
    return sectionFont;
  }

  protected final Font createFont(SheetGraphics graphics) {
    return graphics.createTableFont();
  }

  private Font createHeaderFont(SheetGraphics graphics) {
    return TableEncodingUtilities.createHeaderFont(graphics.getBaseFont());
  }
}
