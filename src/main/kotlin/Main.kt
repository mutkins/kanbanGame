import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.HorizontalAlignment
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream
import Utils.wb
import org.apache.poi.ss.usermodel.BorderStyle

fun main() {
    CardCreator.StoryCreator.Usual.createCard(50)
    CardCreator.StoryCreator.FixedDate.createCard(10)
    CardCreator.StoryCreator.Optimization.createCard(16)
    CardCreator.StoryCreator.Expedite.createCard(14)

    CardCreator.TroubleCreator.HarmlessTrouble.createCard(40)
    CardCreator.TroubleCreator.EasyTrouble.createCard(40)
    CardCreator.TroubleCreator.SeriousTrouble.createCard(40)
    CardCreator.TroubleCreator.AwfulTrouble.createCard(40)
    CardCreator.ModificationCreator.Modification.createCard(15)

//    val b = Card.CardHolder.StoriesHolder.ExpediteStory.stories

//    CardPlacer.placeCardOnList(XSSFWorkbook(), Card.cardList)

//    val row = sheet.getRow(0)
//    val cell = row.getCell(0)
//
//    val borderStyle: CellStyle = wb.createCellStyle()
//    borderStyle.borderBottom = Styles.OUTLINE_BORDER
//    cell.setCellStyle(borderStyle)
//
//    val alignmentStyle = cell.cellStyle
//    alignmentStyle.alignment = HorizontalAlignment.CENTER
//    cell.setCellStyle(alignmentStyle)
//    cell.setCellValue("T")



    var sheet = wb.createSheet("TT")
    Utils.createRows(sheet)
    Utils.setColumnsWidth(sheet)
    val r = ExcelCard.ExcelStory(Card.CardHolder.StoriesHolder.UsualStory.stories[0] as Story)
    r.setCoordinates(0,0)
    r.writeLeftBarValue(sheet)
    r.writeRightBarValue(sheet)
    r.writeTitle(sheet)
    r.writeOutlineBorder(sheet)
    r.fillCard(sheet)
    r.writeFirstRange(sheet)
    r.writeSecondRange(sheet)
    r.writeThirdRange(sheet)

    FileOutputStream("workbook.xlsx").use { fileOut -> wb.write(fileOut)}
    println()


}