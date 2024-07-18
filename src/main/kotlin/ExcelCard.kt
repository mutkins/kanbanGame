import Utils.wb
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFFont
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid


abstract class ExcelCard(
    protected var firstColumn: Int = 0,
    protected var lastColumn: Int = 0,
    protected var firstRow: Int = 0,
    protected var lastRow: Int = 0,
    var leftBar: String = "",
    var title: String = "",
    var rightBar: String = "",
    var letterIndex: String = "",
    protected var backgroundColor: Short = Styles.DEFAULT_COLOR
) {

    abstract fun placeCard(sheet: Sheet, firstRow: Int, firstColumn: Int)


    protected fun fillCard(sheet: Sheet) {
        for (rowNum in firstRow..lastRow){
            val row = sheet.getRow(rowNum)
            for (columnNum in firstColumn..lastColumn){
                val cell = row.getCell(columnNum)
                val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
                val format = MyCellFormat(style)
//                fillCell(cell, backgroundColor)
                format.fillForegroundColor = backgroundColor
                format.fillPattern = Styles.FILL_PATTERN_GLOBAL
                val newStyle = getCellStyle(format)
                cell.cellStyle = newStyle
            }
        }
    }

    protected fun writeOutlineBorder(sheet: Sheet){
        for (rowNum in firstRow..lastRow){
            for (columnNum in firstColumn..lastColumn){
                val row = sheet.getRow(rowNum)
                val cell = row.getCell(columnNum)
                val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
                val format = MyCellFormat(style)

                format.borderTop = if (rowNum == firstRow) Styles.OUTLINE_BORDER else BorderStyle.NONE
                format.borderRight = if  (columnNum == lastColumn) Styles.OUTLINE_BORDER else BorderStyle.NONE
                format.borderBottom = if  (rowNum == lastRow) Styles.OUTLINE_BORDER else BorderStyle.NONE
                format.borderLeft = if  (columnNum == firstColumn) Styles.OUTLINE_BORDER else BorderStyle.NONE

                val newStyle = getCellStyle(format)
//                style.borderTop = if (rowNum == firstRow) Styles.OUTLINE_BORDER else BorderStyle.NONE
//                style.borderRight = if  (columnNum == lastColumn) Styles.OUTLINE_BORDER else BorderStyle.NONE
//                style.borderBottom = if  (rowNum == lastRow) Styles.OUTLINE_BORDER else BorderStyle.NONE
//                style.borderLeft = if  (columnNum == firstColumn) Styles.OUTLINE_BORDER else BorderStyle.NONE
                cell.cellStyle = newStyle
            }
        }
    }

    protected fun writeLeftBarValue(sheet: Sheet){
        val row = sheet.getRow(firstRow)
        val cell = row.getCell(firstColumn)
        cell.setCellValue(leftBar)
        doBoldFont(cell)
        mergeCells(sheet, firstRow,firstRow, firstColumn, firstColumn+1)
        doHorizontalAlignment(cell)

    }

    protected fun writeRightBarValue(sheet: Sheet){
        val row = sheet.getRow(firstRow)
        val cell = row.getCell(lastColumn-4)
        cell.setCellValue(rightBar)
        doBoldFont(cell)
        mergeCells(sheet, firstRow,firstRow, lastColumn-4, lastColumn)
        doHorizontalAlignment(cell)

    }

    protected fun writeTitle(sheet: Sheet){
        val row = sheet.getRow(firstRow+1)
        val cell = row.getCell(firstColumn+1)
        cell.setCellValue(title)
        doBoldFont(cell)
        mergeCells(sheet, firstRow + 1,firstRow + 1, firstColumn+1, lastColumn-1)
        doHorizontalAlignment(cell)
    }


    protected fun setCoordinates(firstRow: Int, firstColumn: Int){
        this.firstRow = firstRow
        this.firstColumn = firstColumn
        this.lastRow = this.firstRow + 8
        this.lastColumn = this.firstColumn + 16
    }

    protected fun mergeCells(sheet: Sheet, firstRow: Int, lastRow: Int, firstCol: Int, lastCol: Int){
        val cellRangeAddress = CellRangeAddress(firstRow, lastRow, firstCol, lastCol)
        sheet.addMergedRegion(cellRangeAddress)
    }
    protected fun doHorizontalAlignment(cell: Cell){
        val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
        val format = MyCellFormat(style)
        format.alignment = HorizontalAlignment.CENTER
        val newStyle = getCellStyle(format)
        cell.cellStyle = newStyle
    }

    protected fun doBoldFont(cell: Cell){
//        val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
//        val font: Font = wb.createFont()
//        font.bold = true
//        style.setFont(font)
        val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
        val format = MyCellFormat(style)
        format.alignment = HorizontalAlignment.CENTER
        val newStyle = getCellStyle(format)
        cell.cellStyle = newStyle
    }

    protected fun fillCell(cell: Cell, color: Short){
        val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
        val format = MyCellFormat(style)
        format.fillForegroundColor = color
        format.fillPattern = Styles.FILL_PATTERN_GLOBAL
        val newStyle = getCellStyle(format)
        cell.cellStyle = newStyle
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

        override fun placeCard(sheet: Sheet, firstRow: Int, firstColumn: Int) {
            setCoordinates(firstRow,firstColumn)
            fillCard(sheet)
            writeOutlineBorder(sheet)
            writeFirstRange(sheet)
            writeSecondRange(sheet)
            writeThirdRange(sheet)
            writeTitle(sheet)
            writeLeftBarValue(sheet)
            writeRightBarValue(sheet)

        }

        private fun writeFirstRange(sheet: Sheet){
            val row = sheet.getRow(firstRow+3)
            for (columnNum in firstColumn+1..firstColumn+1+firstRangeCount){
                println(columnNum)
                val cell = row.getCell(columnNum)
                val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
                val format = MyCellFormat(style)
                format.borderTop = Styles.RANGE_BORDER
                format.borderRight = Styles.RANGE_BORDER
                format.borderBottom = Styles.RANGE_BORDER
                format.borderLeft = Styles.RANGE_BORDER
                format.fillForegroundColor = Styles.FIRST_RANGE_COLOR
                format.fillPattern = Styles.FILL_PATTERN_GLOBAL
                fillCell(cell, Styles.FIRST_RANGE_COLOR)
                val newStyle = getCellStyle(format)
                cell.cellStyle = newStyle
            }
        }
        private fun writeSecondRange(sheet: Sheet){
            val row = sheet.getRow(firstRow+5)
            for (columnNum in firstColumn+1..firstColumn+1+secondRangeCount){
                val cell = row.getCell(columnNum)
//                val style = getCellStyle(cell as XSSFCell)
                val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
                val format = MyCellFormat(style)
                format.borderTop = Styles.RANGE_BORDER
                format.borderRight = Styles.RANGE_BORDER
                format.borderBottom = Styles.RANGE_BORDER
                format.borderLeft = Styles.RANGE_BORDER
                format.fillForegroundColor = Styles.SECOND_RANGE_COLOR
                format.fillPattern = Styles.FILL_PATTERN_GLOBAL
//                fillCell(cell, Styles.SECOND_RANGE_COLOR)
                val newStyle = getCellStyle(format)
                cell.cellStyle = newStyle
            }

        }
        private fun writeThirdRange(sheet: Sheet){
            val row = sheet.getRow(firstRow+7)
            for (columnNum in firstColumn+1..firstColumn+1+thirdRangeCount){
                val cell = row.getCell(columnNum)
                val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
                val format = MyCellFormat(style)
                format.borderTop = Styles.RANGE_BORDER
                format.borderRight = Styles.RANGE_BORDER
                format.borderBottom = Styles.RANGE_BORDER
                format.borderLeft = Styles.RANGE_BORDER
                format.fillForegroundColor = Styles.THIRD_RANGE_COLOR
                format.fillPattern = Styles.FILL_PATTERN_GLOBAL
//                fillCell(cell, Styles.THIRD_RANGE_COLOR)
                val newStyle = getCellStyle(format)
                cell.cellStyle = newStyle
            }
        }
        class ExcelUsualStory(story: Story.UsualStory):ExcelStory(story as Story){
            init {
                backgroundColor = Styles.USUAL_STORY_COLOR
            }
        }
        class ExcelFixedStory(story: Story.FixedDateStory):ExcelStory(story as Story){
            init {
                backgroundColor = Styles.FIXED_STORY_COLOR
            }
        }

        class ExcelOptimizationStory(story: Story.OptimizationStory):ExcelStory(story as Story){
            init {
                backgroundColor = Styles.OPTIMIZATION_STORY_COLOR
            }
        }

        class ExcelExpediteStory(story: Story.ExpediteStory):ExcelStory(story as Story){
            init {
                backgroundColor = Styles.EXPEDITE_STORY_COLOR
            }
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
        init {
            backgroundColor = Styles.TROUBLE_COLOR
        }
        override fun placeCard(sheet: Sheet, firstRow: Int, firstColumn: Int) {
            fillCard(sheet)
            writeOutlineBorder(sheet)
            writeTitle(sheet)
            writeLeftBarValue(sheet)
            writeRightBarValue(sheet)

        }
    }

    class ExcelModification(

    ): ExcelTextCard(){
        init {
            backgroundColor = Styles.MODIFICATION_COLOR
        }
        override fun placeCard(sheet: Sheet, firstRow: Int, firstColumn: Int) {
            fillCard(sheet)
            writeOutlineBorder(sheet)
            writeTitle(sheet)
            writeLeftBarValue(sheet)
            writeRightBarValue(sheet)

        }
    }


companion object{

    fun getCellStyle(cell: XSSFCell): XSSFCellStyle{

        return cell.cellStyle
    }

    fun getCellStyle(format: MyCellFormat): XSSFCellStyle{

        return StyleCache.getOrCreateStyle(format)
    }
}


    }