import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream


fun main() {
    CardCreator.StoryCreator.Usual.createCard(50)
    CardCreator.StoryCreator.FixedDate.createCard(10)
    CardCreator.StoryCreator.Optimization.createCard(16)
    CardCreator.StoryCreator.Expedite.createCard(14)

    CardCreator.TroubleCreator.HarmlessTrouble.createCard(40)
    CardCreator.TroubleCreator.EasyTrouble.createCard(40)
    CardCreator.TroubleCreator.SeriousTrouble.createCard(40)
    CardCreator.TroubleCreator.AwfulTrouble.createCard(40)
    CardCreator.ModificationCreator.Modification.createCard(15)

    val a = Card.cardList
    val b = Card.CardHolder.StoriesHolder.ExpediteStory.stories
    Card.placeCardOnList()
    println()


}