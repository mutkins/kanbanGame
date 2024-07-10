import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream

abstract class Card(var letterIndex: String = ""){
    abstract fun setLetterIndex()

    companion object {
        var cardList: MutableList<MutableList<Card>> = mutableListOf()
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