import Utils.wb
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFFont


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
    class BackSide(backgroundColor: Short): ExcelCard(backgroundColor=backgroundColor){
        override fun placeCard(sheet: Sheet, firstRow: Int, firstColumn: Int) {
            setCoordinates(firstRow,firstColumn)
            fillCard(sheet)
            writeOutlineBorder(sheet)
        }
    }
    abstract fun placeCard(sheet: Sheet, firstRow: Int, firstColumn: Int)


    protected fun fillCard(sheet: Sheet) {
        for (rowNum in firstRow..lastRow){
            val row = sheet.getRow(rowNum)
            for (columnNum in firstColumn..lastColumn){
                println("Fill $rowNum:$columnNum cell")
                val cell = row.getCell(columnNum)
                val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
                val format = MyCellFormat(style)
//                fillCell(cell, backgroundColor)
                format.fillForegroundColor = backgroundColor
                format.fillPattern = Styles.FILL_PATTERN_CARDS
                val newStyle = getCellStyle(format)
                cell.cellStyle = newStyle

            }
        }
    }

    protected fun writeOutlineBorder(sheet: Sheet){
        for (rowNum in firstRow..lastRow){
            for (columnNum in firstColumn..lastColumn){
                println("write outline $rowNum:$columnNum cell")
                val row = sheet.getRow(rowNum)
                val cell = row.getCell(columnNum)
                val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
                val format = MyCellFormat(style)
                format.borderTop = if (rowNum == firstRow) Styles.OUTLINE_BORDER else BorderStyle.NONE
                format.borderRight = if  (columnNum == lastColumn) Styles.OUTLINE_BORDER else BorderStyle.NONE
                format.borderBottom = if  (rowNum == lastRow) Styles.OUTLINE_BORDER else BorderStyle.NONE
                format.borderLeft = if  (columnNum == firstColumn) Styles.OUTLINE_BORDER else BorderStyle.NONE
                val newStyle = getCellStyle(format)
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
        val cell = row.getCell(lastColumn-3)
        cell.setCellValue(rightBar)
        doBoldFont(cell)
        mergeCells(sheet, firstRow,firstRow, lastColumn-3, lastColumn)
        doHorizontalAlignment(cell)

    }

    protected fun writeTitle(sheet: Sheet){

        val row = sheet.getRow(firstRow+1)
        val cell = row.getCell(firstColumn+1)
        println("Write title on ${firstRow+1}: ${firstColumn+1}")
        cell.setCellValue(title)
        println("Do bold font ${firstRow+1}: ${firstColumn+1}")
        doBoldFont(cell)
        mergeCells(sheet, firstRow + 1,firstRow + 1, firstColumn+1, lastColumn-1)
        println("Do Horizontal Alignment ${firstRow+1}: ${firstColumn+1}")
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

    protected fun doWrap(cell: XSSFCell){
        val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
        val format = MyCellFormat(style)
        format.wrapText = true
        val newStyle = getCellStyle(format)
        cell.cellStyle = newStyle
    }
    protected fun doHorizontalAlignment(cell: Cell){
        val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
        val format = MyCellFormat(style)
        format.horizontalAlignment = HorizontalAlignment.CENTER
        val newStyle = getCellStyle(format)
        cell.cellStyle = newStyle
    }
    protected fun doVerticalAlignment(cell: Cell){
        val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
        val format = MyCellFormat(style)
        format.verticalAlignment = VerticalAlignment.CENTER
        val newStyle = getCellStyle(format)
        cell.cellStyle = newStyle
    }

    protected fun doBoldFont(cell: Cell){
        val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
        println("Cell style before bolding ${style.font.bold}")
        val format = MyCellFormat(style)
        format.font.bold = true
         val newStyle = getCellStyle(format)
        cell.cellStyle = newStyle
        println("Cell style after bolding ${style.font.bold}")
    }

    protected fun fillCell(cell: Cell, color: Short){
        val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
        val format = MyCellFormat(style)
        format.fillForegroundColor = color
        format.fillPattern = Styles.FILL_PATTERN_CARDS
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
                val cell = row.getCell(columnNum)
                val style: XSSFCellStyle = getCellStyle(cell as XSSFCell)
                val format = MyCellFormat(style)
                format.borderTop = Styles.RANGE_BORDER
                format.borderRight = Styles.RANGE_BORDER
                format.borderBottom = Styles.RANGE_BORDER
                format.borderLeft = Styles.RANGE_BORDER
                format.fillForegroundColor = Styles.FIRST_RANGE_COLOR
                format.fillPattern = Styles.FILL_PATTERN_RANGES
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
                format.fillPattern = Styles.FILL_PATTERN_RANGES
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
                format.fillPattern = Styles.FILL_PATTERN_RANGES
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

        fun writeTextBar(sheet: Sheet){
            val row = sheet.getRow(firstRow+1)
            val cell = row.getCell(firstColumn+1)
            cell.setCellValue(textBar)
            doVerticalAlignment(cell)
            doHorizontalAlignment(cell)
            doWrap(cell as XSSFCell)
            mergeCells(sheet, firstRow + 1,lastRow - 1, firstColumn+1, lastColumn-1)

        }
    }

    class ExcelTrouble(
    ): ExcelTextCard(){
        constructor(card: Trouble): this(){
            textBar = card.text
            leftBar = "${card.letterIndex}${card.rate}"
            rightBar = "угроза: ${card.dangerScore}"
        }
        init {
            backgroundColor = Styles.TROUBLE_COLOR
        }
        override fun placeCard(sheet: Sheet, firstRow: Int, firstColumn: Int) {
            setCoordinates(firstRow,firstColumn)
            fillCard(sheet)
            writeOutlineBorder(sheet)
            writeLeftBarValue(sheet)
            writeRightBarValue(sheet)
            writeTextBar(sheet)
        }


    }

    class ExcelModification(story: Modification): ExcelTextCard(story){
        init {
            backgroundColor = Styles.MODIFICATION_COLOR
        }
        override fun placeCard(sheet: Sheet, firstRow: Int, firstColumn: Int) {
            setCoordinates(firstRow,firstColumn)
            fillCard(sheet)
            writeOutlineBorder(sheet)
            writeTextBar(sheet)
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