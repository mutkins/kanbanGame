interface CardCreator {

    fun createCard(count: Int)
    object StoryCreator {
        object Usual : CardCreator {
            override fun createCard(count: Int) {
                val stories: MutableList<Card> = mutableListOf()
                for (i in 1..count) {
                    val foo = Story.UsualStory(i)
                    foo.setLetterIndex()
                    foo.setEstimate()
                    foo.setPrice()
                    foo.setTitle()
                    stories.add(foo)
                }
                Card.cardList.add(stories)
                Card.CardHolder.StoriesHolder.UsualStory.addCardList(stories)
            }
        }

        object FixedDate : CardCreator {
            override fun createCard(count: Int) {
                val stories: MutableList<Card> = mutableListOf()
                for (i in 1..count) {
                    try{
                    val foo = Story.FixedDateStory(i)
                    foo.setLetterIndex()
                    foo.setEstimate()
                    foo.setPrice()
                    foo.setDueDay()
                    foo.setTitle()
                    stories.add(foo)
                    }
                    catch (e: IteratorException){
                        break
                    }
                }
                Card.cardList.add(stories)
                Card.CardHolder.StoriesHolder.FixedDateStory.addCardList(stories)
            }
        }

        object Optimization : CardCreator {
            override fun createCard(count: Int) {
                val stories: MutableList<Card> = mutableListOf()
                for (i in 1..count) {
                    try{
                    val foo = Story.OptimizationStory(i)
                    foo.setLetterIndex()
                    foo.setEstimate()
                    foo.setPrice()
                    foo.setStabilityScore()
                    foo.setTitle()
                    stories.add(foo)
                    }
                    catch (e: IteratorException){
                        break
                    }
                }
                Card.cardList.add(stories)
                Card.CardHolder.StoriesHolder.OptimizationStory.addCardList(stories)
            }
        }

        object Expedite : CardCreator {
            override fun createCard(count: Int) {
                val stories: MutableList<Card> = mutableListOf()
                for (i in 1..count) {
                    try{
                    val foo = Story.ExpediteStory(i)
                    foo.setLetterIndex()
                    foo.setEstimate()
                    foo.setPrice()
                    foo.setDueDay()
                    foo.setTitle()
                    stories.add(foo)
                    }
                    catch (e: IteratorException){
                        break
                    }
                }
                Card.cardList.add(stories)
                Card.CardHolder.StoriesHolder.ExpediteStory.addCardList(stories)
            }
        }
    }

    object TroubleCreator{
        object HarmlessTrouble : CardCreator {
            override fun createCard(count: Int) {
                val troubles: MutableList<Card> = mutableListOf()
                for (i in 1..count) {
                    try {
                        val foo = Trouble.HarmlessTrouble()
                        foo.setLetterIndex()
                        foo.setText()
                        foo.setDangerScore()
                        troubles.add(foo)
                    }
                    catch (e: IteratorException){
                        break
                    }
                }
                Card.cardList.add(troubles)
                Card.CardHolder.Trouble.addCardList(troubles)
            }
        }
        object EasyTrouble : CardCreator {
            override fun createCard(count: Int) {
                val troubles: MutableList<Card> = mutableListOf()
                for (i in 1..count) {
                    try{
                    val foo = Trouble.EasyTrouble()
                    foo.setLetterIndex()
                    foo.setText()
                    foo.setDangerScore()
                    troubles.add(foo)
                }
                catch (e: IteratorException){
                    break
                }
                }
                Card.cardList.add(troubles)
                Card.CardHolder.Trouble.addCardList(troubles)
            }
        }
        object SeriousTrouble : CardCreator {
            override fun createCard(count: Int) {
                val troubles: MutableList<Card> = mutableListOf()
                for (i in 1..count) {
                    try{
                    val foo = Trouble.SeriousTrouble()
                    foo.setLetterIndex()
                    foo.setText()
                    foo.setDangerScore()
                    troubles.add(foo)
                }
                catch (e: IteratorException){
                    break
                }
                }
                Card.cardList.add(troubles)
                Card.CardHolder.Trouble.addCardList(troubles)
            }
        }
        object AwfulTrouble : CardCreator {
            override fun createCard(count: Int) {
                val troubles: MutableList<Card> = mutableListOf()
                for (i in 1..count) {
                    try{
                    val foo = Trouble.AwfulTrouble()
                    foo.setLetterIndex()
                    foo.setText()
                    foo.setDangerScore()
                    troubles.add(foo)
                }
                catch (e: IteratorException){
                    break
                }
                }
                Card.cardList.add(troubles)
                Card.CardHolder.Trouble.addCardList(troubles)
            }
        }
    }
    object ModificationCreator{
        object Modification : CardCreator {
            override fun createCard(count: Int) {
                val modifications: MutableList<Card> = mutableListOf()
                for (i in 1..count) {
                    try{
                        val foo = Modification()
                        foo.setLetterIndex()
                        foo.setText()
                        modifications.add(foo)
                    }
                    catch (e: IteratorException){
                        break
                    }
                }
                Card.cardList.add(modifications)
                Card.CardHolder.Modification.addCardList(modifications)
            }
        }
    }
}