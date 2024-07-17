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
    CardCreator.ModificationCreator.Modific.createCard(15)

    var rownum = 0
    var columnNum = 0
    var sheet = wb.createSheet("S")
    Utils.createRows(sheet)
    Utils.setColumnsWidth(sheet)
    for (story in Card.CardHolder.StoriesHolder.UsualStory.stories){
        ExcelCard.ExcelStory.ExcelUsualStory(story).placeCard(sheet, rownum, columnNum)
        rownum += 9
    }
    FileOutputStream("workbook.xlsx").use { fileOut -> wb.write(fileOut)}
    println()


}