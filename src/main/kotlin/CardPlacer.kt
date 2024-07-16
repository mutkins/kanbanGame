import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream

object CardPlacer {

    fun placeCardOnSheet(sheet: Sheet, stories: List<Card>){
        for (story in stories){

            val r = ExcelCard.ExcelStory.ExcelExpediteStory(story)

        }

    }
}
