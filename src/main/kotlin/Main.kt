import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.HorizontalAlignment
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream
import Utils.wb
import org.apache.poi.ss.usermodel.BorderStyle

fun main() {
    CardCreator.StoryCreator.Usual.createCard(500)
    CardCreator.StoryCreator.FixedDate.createCard(10)
    CardCreator.StoryCreator.Optimization.createCard(16)
    CardCreator.StoryCreator.Expedite.createCard(14)

    CardCreator.TroubleCreator.HarmlessTrouble.createCard(40)
    CardCreator.TroubleCreator.EasyTrouble.createCard(40)
    CardCreator.TroubleCreator.SeriousTrouble.createCard(40)
    CardCreator.TroubleCreator.AwfulTrouble.createCard(40)
    CardCreator.ModificationCreator.Modific.createCard(15)

    var sheet = wb.createSheet("S")
    Utils.createRows(sheet)
    Utils.setColumnsWidth(sheet)
    var rownum = -10
    var columnNum = 0
    Card.CardHolder.StoriesHolder.UsualStory.stories.forEachIndexed{index, story ->

        if (index % 2 == 0){
            columnNum = 0
            rownum += 10
        }
        else columnNum = 18
        ExcelCard.ExcelStory.ExcelUsualStory(story).placeCard(sheet, rownum, columnNum)
    }
    sheet = wb.createSheet("F")
    Utils.createRows(sheet)
    Utils.setColumnsWidth(sheet)
    rownum = -10
    columnNum = 0
    Card.CardHolder.StoriesHolder.FixedDateStory.stories.forEachIndexed{index, story ->

        if (index % 2 == 0){
            columnNum = 0
            rownum += 10
        }
        else columnNum = 18
        ExcelCard.ExcelStory.ExcelFixedStory(story).placeCard(sheet, rownum, columnNum)
    }
    sheet = wb.createSheet("O")
    Utils.createRows(sheet)
    Utils.setColumnsWidth(sheet)
    rownum = -10
    columnNum = 0
    Card.CardHolder.StoriesHolder.OptimizationStory.stories.forEachIndexed{index, story ->

        if (index % 2 == 0){
            columnNum = 0
            rownum += 10
        }
        else columnNum = 18
        ExcelCard.ExcelStory.ExcelOptimizationStory(story).placeCard(sheet, rownum, columnNum)
    }
    sheet = wb.createSheet("E")
    Utils.createRows(sheet)
    Utils.setColumnsWidth(sheet)
    rownum = -10
    columnNum = 0
    Card.CardHolder.StoriesHolder.ExpediteStory.stories.forEachIndexed{index, story ->

        if (index % 2 == 0){
            columnNum = 0
            rownum += 10
        }
        else columnNum = 18
        ExcelCard.ExcelStory.ExcelExpediteStory(story).placeCard(sheet, rownum, columnNum)
    }



    FileOutputStream("workbook.xlsx").use { fileOut -> wb.write(fileOut)}
    println()


}

