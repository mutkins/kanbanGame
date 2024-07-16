interface CardCreator {

    fun createCard(count: Int)
    object StoryCreator {
        object Usual : CardCreator {
            override fun createCard(count: Int) {
                val stories: MutableList<Story.UsualStory> = mutableListOf()
                for (i in 1..count) {
                    val foo = Card.Builder.StoryBuilder.UsualStoryBuilder(i)
                        .setPrice()
                        .setTitle()
                        .setEstimate()
                        .setLetterIndex()
                        .build()
                    stories.add(foo)
                }
//                Card.cardList.add(stories)
                Card.CardHolder.StoriesHolder.UsualStory.addCardList(stories)
            }
        }

        object FixedDate : CardCreator {
            override fun createCard(count: Int) {
                val stories: MutableList<Story.FixedDateStory> = mutableListOf()
                for (i in 1..count) {
                    try{
                    val foo = Card.Builder.StoryBuilder.StoryWithDueDateBuilder.FixedDateStoryBuilder(i)
                    .setLetterIndex()
                    .setEstimate()
                    .setPrice()
                    .setDueDay()
                    .setTitle()
                    .build()
                    stories.add(foo)
                    }
                    catch (e: IteratorException){
                        break
                    }
                }
//                Card.cardList.add(stories)
                Card.CardHolder.StoriesHolder.FixedDateStory.addCardList(stories)
            }
        }

        object Optimization : CardCreator {
            override fun createCard(count: Int) {
                val stories: MutableList<Story.OptimizationStory> = mutableListOf()
                for (i in 1..count) {
                    try{
                    val foo = Card.Builder.StoryBuilder.StoryWithStabilityScoreBuilder.OptimizationStoryBuilder(i)
                    .setLetterIndex()
                    .setEstimate()
                    .setPrice()
                    .setStabilityScore()
                    .setTitle()
                    .build()
                    stories.add(foo)
                    }
                    catch (e: IteratorException){
                        break
                    }
                }
//                Card.cardList.add(stories)
                Card.CardHolder.StoriesHolder.OptimizationStory.addCardList(stories)
            }
        }

        object Expedite : CardCreator {
            override fun createCard(count: Int) {
                val stories: MutableList<Story.ExpediteStory> = mutableListOf()
                for (i in 1..count) {
                    try{
                    val foo = Card.Builder.StoryBuilder.StoryWithDueDateBuilder.ExpediteStoryBuilder(i)
                    .setLetterIndex()
                    .setEstimate()
                    .setPrice()
                    .setDueDay()
                    .setTitle()
                    .build()
                    stories.add(foo)
                    }
                    catch (e: IteratorException){
                        break
                    }
                }
//                Card.cardList.add(stories)
                Card.CardHolder.StoriesHolder.ExpediteStory.addCardList(stories)
            }
        }
    }

    object TroubleCreator{
        object HarmlessTrouble : CardCreator {
            override fun createCard(count: Int) {
                val troubles: MutableList<Trouble> = mutableListOf()
                for (i in 1..count) {
                    try {
                        val foo = Card.Builder.TextCardBuilder.TroubleBuilder.HarmlessTroubleBuilder()
                        .setLetterIndex()
                        .setText()
                        .setDangerScore()
                        .build()
                        troubles.add(foo)
                    }
                    catch (e: IteratorException){
                        break
                    }
                }
//                Card.cardList.add(troubles)
                Card.CardHolder.TroubleO.addCardList(troubles)
            }
        }
        object EasyTrouble : CardCreator {
            override fun createCard(count: Int) {
                val troubles: MutableList<Trouble> = mutableListOf()
                for (i in 1..count) {
                    try{
                    val foo = Card.Builder.TextCardBuilder.TroubleBuilder.EasyTroubleBuilder()
                        .setLetterIndex()
                        .setText()
                        .setDangerScore()
                        .build()
                        troubles.add(foo)
                }
                catch (e: IteratorException){
                    break
                }
                }
//                Card.cardList.add(troubles)
                Card.CardHolder.TroubleO.addCardList(troubles)
            }
        }
        object SeriousTrouble : CardCreator {
            override fun createCard(count: Int) {
                val troubles: MutableList<Trouble> = mutableListOf()
                for (i in 1..count) {
                    try{
                    val foo = Card.Builder.TextCardBuilder.TroubleBuilder.SeriousTroubleBuilder()
                        .setLetterIndex()
                        .setText()
                        .setDangerScore()
                        .build()
                        troubles.add(foo)
                }
                catch (e: IteratorException){
                    break
                }
                }
//                Card.cardList.add(troubles)
                Card.CardHolder.TroubleO.addCardList(troubles)
            }
        }
        object AwfulTrouble : CardCreator {
            override fun createCard(count: Int) {
                val troubles: MutableList<Trouble> = mutableListOf()
                for (i in 1..count) {
                    try{
                    val foo = Card.Builder.TextCardBuilder.TroubleBuilder.AwfulTroubleBuilder()
                        .setLetterIndex()
                        .setText()
                        .setDangerScore()
                        .build()
                        troubles.add(foo)
                }
                catch (e: IteratorException){
                    break
                }
                }
//                Card.cardList.add(troubles)
                Card.CardHolder.TroubleO.addCardList(troubles)
            }
        }
    }
    object ModificationCreator{
        object Modific : CardCreator {
            override fun createCard(count: Int) {
                val modifications: MutableList<Modification> = mutableListOf()
                for (i in 1..count) {
                    try{
                        val foo = Card.Builder.TextCardBuilder.ModificationBuilder()
                        .setLetterIndex()
                        .setText()
                        .build()
                        modifications.add(foo)
                    }
                    catch (e: IteratorException){
                        break
                    }
                }
//                Card.cardList.add(modifications)
                Card.CardHolder.ModificationO.addCardList(modifications)
            }
        }
    }
}