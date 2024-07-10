//import org.apache.poi.ss.usermodel.*
//import org.apache.poi.xssf.usermodel.XSSFWorkbook
//import java.io.FileOutputStream
//
//object CardPlacer {
//
//    fun placeCardOnList(wb: XSSFWorkbook, cardList: List<List<Card>>){
//        var i = 1
//
//        for (list in cardList){
//            val sheet: S = try {
//                wb.createSheet(list[0].letterIndex)
//            } catch (e: IllegalArgumentException){
//                wb.createSheet("${list[0].letterIndex}_${i++}")
//            }
//
//
//            for (card in list){
//
//                excelCard = ExcelCard.ExcelStory(card)
//            }
//
//
//            val row: Row = sheet.createRow(1)
//// Create a cell and put a value in it.
//            val cell: Cell = row.createCell(1)
//
//// Style the cell with borders all around.
//            val style: CellStyle = wb.createCellStyle()
//            style.borderBottom = BorderStyle.THIN
//            style.bottomBorderColor = IndexedColors.BLACK.getIndex()
//            style.borderLeft = BorderStyle.THIN
//            style.leftBorderColor = IndexedColors.GREEN.getIndex()
//            style.borderRight = BorderStyle.THIN
//            style.rightBorderColor = IndexedColors.BLUE.getIndex()
//            style.borderTop = BorderStyle.MEDIUM_DASHED
//            style.topBorderColor = IndexedColors.BLACK.getIndex()
//            cell.cellStyle = style
//        }
//
//    }
//}
