import Utils.wb
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress


abstract class ExcelCard(
    private var firstColumn: Int = 0,
    private var lastColumn: Int = 0,
    private var firstRow: Int = 0,
    private var lastRow: Int = 0,
    var leftBar: String = "",
    var title: String = "",
    var rightBar: String = ""
) {
    fun writeOutlineBorder(sheet: Sheet){

        for (rowNum in firstRow..lastRow){
            for (columnNum in firstColumn..lastColumn){
                val style: CellStyle = wb.createCellStyle()
                style.borderTop = if (rowNum == firstRow) Styles.OUTLINE_BORDER else BorderStyle.NONE
                style.borderRight = if  (columnNum == lastColumn) Styles.OUTLINE_BORDER else BorderStyle.NONE
                style.borderBottom = if  (rowNum == lastRow) Styles.OUTLINE_BORDER else BorderStyle.NONE
                style.borderLeft = if  (columnNum == firstColumn) Styles.OUTLINE_BORDER else BorderStyle.NONE
                val row = sheet.getRow(rowNum)
                val cell = row.getCell(columnNum)
                cell.cellStyle = style
            }
        }
    }

    fun writeLeftBarValue(sheet: Sheet){
        val row = sheet.getRow(firstRow)
        val cell = row.getCell(firstColumn)
        cell.setCellValue(leftBar)
        mergeCells(sheet, firstRow,firstRow, firstColumn, firstColumn+1)
        doHorizontalAlignment(cell)

    }

    fun writeRightBarValue(sheet: Sheet){
        val row = sheet.getRow(firstRow)
        val cell = row.getCell(lastColumn-4)
        cell.setCellValue(rightBar)
        mergeCells(sheet, firstRow,firstRow, lastColumn-4, lastColumn)
        doHorizontalAlignment(cell)

    }

    fun writeTitle(sheet: Sheet){
        val row = sheet.getRow(firstRow+1)
        val cell = row.getCell(firstColumn+1)
        cell.setCellValue(title)
        mergeCells(sheet, firstRow + 1,firstRow + 1, firstColumn+1, lastColumn-1)
        doHorizontalAlignment(cell)
    }


    fun setCoordinates(firstRow: Int, firstColumn: Int){
        this.firstRow = firstRow
        this.firstColumn = firstColumn
        this.lastRow = this.firstRow + 8
        this.lastColumn = this.firstColumn + 16
    }

    private fun mergeCells(sheet: Sheet, firstRow: Int, lastRow: Int, firstCol: Int, lastCol: Int){
        val cellRangeAddress = CellRangeAddress(firstRow, lastRow, firstCol, lastCol)
        sheet.addMergedRegion(cellRangeAddress)
    }
    private fun doHorizontalAlignment(cell: Cell){
        val cellStyle: CellStyle = wb.createCellStyle()
        cellStyle.alignment = HorizontalAlignment.CENTER
        cell.cellStyle = cellStyle
    }

    class ExcelStory(
        var firstRangeCount: Int = 0,
        var firstRangeColor: String = "",
        var secondRangeCount: Int = 0,
        var secondRangeColor: String = "",
        var thirdRangeCount: Int = 0,
        var thirdRangeColor: String = ""
    ): ExcelCard(){

        constructor(story: Story): this(){
            firstRangeCount = story.analystEstimate
            secondRangeCount = story.devEstimate
            thirdRangeCount = story.testEstimate
            firstRangeColor = Styles.ANALYST_RANGE_COLOR
            secondRangeColor = Styles.DEV_RANGE_COLOR
            thirdRangeColor = Styles.TEST_RANGE_COLOR
            leftBar = "${story.letterIndex}${story.num}"
            rightBar = "${story.price} $"
            title = story.title
        }
    }

    abstract class ExcelTextCard(
        var textBar: String = ""
    ): ExcelCard(){
        constructor(card: TextCard): this(){
            textBar = card.text
        }
    }

    class ExcelTrouble(
    ): ExcelTextCard(){
        constructor(card: Trouble): this(){
            textBar = card.text
            leftBar = "${card.letterIndex}${card.rate}"
            rightBar = "${card.dangerScore} угр."
        }
    }

    class ExcelModification(
    ): ExcelTextCard(){}


companion object{

    fun getCellStyle(sheet: Sheet, rowNum: Int, colNum: Int) : CellStyle?{
        val row = sheet.getRow(rowNum)
        val cell = row.getCell(colNum)
        return cell.cellStyle
    }
}


    }