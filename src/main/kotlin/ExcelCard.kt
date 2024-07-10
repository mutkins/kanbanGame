import Utils.wb
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFFont


abstract class ExcelCard(
    protected var firstColumn: Int = 0,
    protected var lastColumn: Int = 0,
    protected var firstRow: Int = 0,
    protected var lastRow: Int = 0,
    var leftBar: String = "",
    var title: String = "",
    var rightBar: String = "",
    var letterIndex: String = ""
) {

    fun fillCard(sheet: Sheet){
        for (rowNum in firstRow..lastRow){
            val row = sheet.getRow(rowNum)
            for (columnNum in firstColumn..lastColumn){
                val cell = row.getCell(columnNum)

                val color = when (letterIndex){
                    "S" -> Styles.USUAL_STORY_COLOR
                    "F" -> Styles.FIXED_STORY_COLOR
                    "O" -> Styles.OPTIMIZATION_STORY_COLOR
                    "E" -> Styles.EXPEDITE_STORY_COLOR
                    else -> Styles.DEFAULT_COLOR
                }
                fillCell(cell, color)
            }
        }
    }


    fun writeOutlineBorder(sheet: Sheet){
        for (rowNum in firstRow..lastRow){
            for (columnNum in firstColumn..lastColumn){
                val style: CellStyle = getCellStyle(sheet, rowNum,columnNum)
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
        doBoldFont(cell)
        mergeCells(sheet, firstRow,firstRow, firstColumn, firstColumn+1)
        doHorizontalAlignment(cell)

    }

    fun writeRightBarValue(sheet: Sheet){
        val row = sheet.getRow(firstRow)
        val cell = row.getCell(lastColumn-4)
        cell.setCellValue(rightBar)
        doBoldFont(cell)
        mergeCells(sheet, firstRow,firstRow, lastColumn-4, lastColumn)
        doHorizontalAlignment(cell)

    }

    fun writeTitle(sheet: Sheet){
        val row = sheet.getRow(firstRow+1)
        val cell = row.getCell(firstColumn+1)
        cell.setCellValue(title)
        doBoldFont(cell)
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
        val style: CellStyle = getCellStyle(cell)
        style.alignment = HorizontalAlignment.CENTER
        cell.cellStyle = style
    }

    private fun doBoldFont(cell: Cell){
        val style: CellStyle = getCellStyle(cell)
        val font: Font = wb.createFont()
        font.bold = true
        style.setFont(font)
        cell.cellStyle = style
    }

    protected fun fillCell(cell: Cell, color: Short){
        val style: CellStyle = getCellStyle(cell)
        style.fillForegroundColor = color
        style.fillPattern = Styles.FILL_PATTERN_GLOBAL
    }

    abstract class ExcelStory(
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


        fun writeFirstRange(sheet: Sheet){
            val row = sheet.getRow(firstRow+3)
            for (columnNum in firstColumn+1..firstRangeCount){
                val cell = row.getCell(columnNum)
                val style = getCellStyle(cell)
                style.borderTop = Styles.RANGE_BORDER
                style.borderRight = Styles.RANGE_BORDER
                style.borderBottom = Styles.RANGE_BORDER
                style.borderLeft = Styles.RANGE_BORDER
                fillCell(cell, Styles.FIRST_RANGE_COLOR)
                cell.cellStyle = style
            }
        }
        fun writeSecondRange(sheet: Sheet){
            val row = sheet.getRow(firstRow+5)
            for (columnNum in firstColumn+1..secondRangeCount){
                val cell = row.getCell(columnNum)
                val style = getCellStyle(cell)
                style.borderTop = Styles.RANGE_BORDER
                style.borderRight = Styles.RANGE_BORDER
                style.borderBottom = Styles.RANGE_BORDER
                style.borderLeft = Styles.RANGE_BORDER
                fillCell(cell, Styles.SECOND_RANGE_COLOR)
                cell.cellStyle = style
            }

        }
        fun writeThirdRange(sheet: Sheet){
            val row = sheet.getRow(firstRow+7)
            for (columnNum in firstColumn+1..thirdRangeCount){
                val cell = row.getCell(columnNum)
                val style = getCellStyle(cell)
                style.borderTop = Styles.RANGE_BORDER
                style.borderRight = Styles.RANGE_BORDER
                style.borderBottom = Styles.RANGE_BORDER
                style.borderLeft = Styles.RANGE_BORDER
                fillCell(cell, Styles.THIRD_RANGE_COLOR)
                cell.cellStyle = style
            }
        }
        class ExcelUsualStory(story: Story):ExcelStory(story){

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

    fun getCellStyle(sheet: Sheet, rowNum: Int, colNum: Int) : CellStyle{
        val row = sheet.getRow(rowNum)
        val cell = row.getCell(colNum)
        val a = cell.cellStyle
        return cell.cellStyle
    }
    fun getCellStyle(cell: Cell): CellStyle{
        val a = cell.cellStyle
        return cell.cellStyle
    }
}


    }