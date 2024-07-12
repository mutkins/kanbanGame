import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream

abstract class Card(var letterIndex: String = ""){
    abstract fun setLetterIndex()

    companion object {
        var cardList: MutableList<MutableList<Card>> = mutableListOf()
    }


    abstract class CardHolder{

        data object StoriesHolder{
            data object UsualStory: CardHolder() {
                var stories: MutableList<Story.UsualStory> = mutableListOf()
                fun addCardList(list: MutableList<Story.UsualStory>) {
                    stories = list
                }

                fun placeCardOnSheet(){
                    for (story in stories){
                        ExcelCard.ExcelStory.ExcelUsualStory(story)
                    }

                }
            }
            data object FixedDateStory: CardHolder() {
                var stories: MutableList<Story.FixedDateStory> = mutableListOf()
                fun addCardList(list: MutableList<Story.FixedDateStory>) {
                    stories = list
                }
            }
            data object OptimizationStory: CardHolder() {
                var stories: MutableList<Story.OptimizationStory> = mutableListOf()
                fun addCardList(list: MutableList<Story.OptimizationStory>) {
                    stories = list
                }
            }
            data object ExpediteStory: CardHolder() {
                var stories: MutableList<Story.ExpediteStory> = mutableListOf()
                fun addCardList(list: MutableList<Story.ExpediteStory>) {
                    stories = list
                }
            }
            }
        data object TroubleO: CardHolder(){
             var stories: MutableList<Trouble> = mutableListOf()
            fun addCardList(list: MutableList<Trouble>) {
                stories = list
            }}
        data object ModificationO: CardHolder(){
            var stories: MutableList<Modification> = mutableListOf()
            fun addCardList(list: MutableList<Modification>) {
                stories = list
            }
        }
        }
    }