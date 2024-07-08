import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream

abstract class Card(protected var letterIndex: String = ""){
    abstract fun setLetterIndex()

    companion object{
        var cardList: MutableList<MutableList<Card>> = mutableListOf()

        fun placeCardOnList(){
            val wb = XSSFWorkbook()
            var i = 1

            for (list in cardList){
                val sheet = try {
                    wb.createSheet(list[0].letterIndex)
                } catch (e: IllegalArgumentException){
                    wb.createSheet("${list[0].letterIndex}_${i++}")
                }
                val row: Row = sheet.createRow(1)

// Create a cell and put a value in it.
                val cell: Cell = row.createCell(1)
// Style the cell with borders all around.
                val style: CellStyle = wb.createCellStyle()
                style.borderBottom = BorderStyle.THIN
                style.bottomBorderColor = IndexedColors.BLACK.getIndex()
                style.borderLeft = BorderStyle.THIN
                style.leftBorderColor = IndexedColors.GREEN.getIndex()
                style.borderRight = BorderStyle.THIN
                style.rightBorderColor = IndexedColors.BLUE.getIndex()
                style.borderTop = BorderStyle.MEDIUM_DASHED
                style.topBorderColor = IndexedColors.BLACK.getIndex()
                cell.cellStyle = style
            }
            FileOutputStream("workbook.xlsx").use { fileOut -> wb.write(fileOut)}
        }
        }

    abstract class CardHolder{
        var stories: MutableList<Card> = mutableListOf()
        fun addCardList(list: MutableList<Card>) {
            stories = list
        }
        data object StoriesHolder{
            data object UsualStory: CardHolder()
            data object FixedDateStory: CardHolder()
            data object OptimizationStory: CardHolder()
            data object ExpediteStory: CardHolder()
            }
        data object Trouble: CardHolder()
        data object Modification: CardHolder()
        }
    }