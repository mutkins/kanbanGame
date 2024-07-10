import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object Utils {
    val wb = XSSFWorkbook()

    fun setColumnsWidth(sheet: Sheet){
        for (i in 0..Styles.COLUMN_COUNT){
            sheet.setColumnWidth(i, 691) //Эксель как-то по-наркомански воспринимает ширину столбца,
                                        // 691 подобрано опытным путем, на выходе это будет ширина столбца 2
        }
    }

    fun createRows(sheet: Sheet){
        for (i in 0..Styles.ROWS_COUNT){
            val row = sheet.createRow(i)
            createCells(row)
        }
    }

    private fun createCells(row: Row){
        for (i in 0..Styles.COLUMN_COUNT){
            row.createCell(i)
        }
    }

}