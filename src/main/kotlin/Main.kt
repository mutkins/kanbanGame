import java.io.FileOutputStream
import Utils.wb

fun main() {
    CardCreator.StoryCreator.Usual.createCard(50)
    CardCreator.StoryCreator.FixedDate.createCard(10)
    CardCreator.StoryCreator.Optimization.createCard(16)
    CardCreator.StoryCreator.Expedite.createCard(14)

    CardCreator.TroubleCreator.HarmlessTrouble.createCard(29)
    CardCreator.TroubleCreator.EasyTrouble.createCard(15)
    CardCreator.TroubleCreator.SeriousTrouble.createCard(15)
    CardCreator.TroubleCreator.AwfulTrouble.createCard(15)
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

    sheet = wb.createSheet("M")
    Utils.createRows(sheet)
    Utils.setColumnsWidth(sheet)
    rownum = -10
    columnNum = 0
    Card.CardHolder.ModificationHolder.stories.forEachIndexed{ index, story ->

        if (index % 2 == 0){
            columnNum = 0
            rownum += 10
        }
        else columnNum = 18
        ExcelCard.ExcelModification(story).placeCard(sheet, rownum, columnNum)
    }

    sheet = wb.createSheet("T")
    Utils.createRows(sheet)
    Utils.setColumnsWidth(sheet)
    val backsideSheet = wb.createSheet("T_BS")
    Utils.createRows(backsideSheet)
    Utils.setColumnsWidth(backsideSheet)
    rownum = -10
    columnNum = 0
    Card.CardHolder.TroubleHolder.stories.forEachIndexed{ index, story ->

        if (index % 2 == 0){
            columnNum = 0
            rownum += 10
        }
        else columnNum = 18
        ExcelCard.ExcelTrouble(story).placeCard(sheet, rownum, columnNum)
        ExcelCard.BackSide(Styles.BACKSIDE_TROUBLE_COLOR).placeCard(backsideSheet, rownum, columnNum)
    }
    sheet = wb.createSheet("B1")
    Utils.createRows(sheet)
    Utils.setColumnsWidth(sheet)
    rownum = -10
    columnNum = 0
    for (index in 0..30){
        if (index % 2 == 0){
            columnNum = 0
            rownum += 10
        }
        else columnNum = 18

    }



    FileOutputStream("workbook.xlsx").use { fileOut -> wb.write(fileOut)}
    println()


}

