abstract class Story(
    var num: Int = 0,
    var price: Int = 0,
    var analystEstimate: Int = 0,
    var devEstimate: Int = 0,
    var testEstimate: Int = 0,
    var baseComplexity: Int = getBaseComplexity(),
    var title: String = ""
):Card(){

    fun setPrice(){
        val mapping = Data.BaseComplexityRange.RANGE.zip(Data.PriceRange.RANGE).toMap()
        price = mapping[baseComplexity]?: 0
        }

    fun setEstimate(){
        val estimate = Estimate(baseComplexity)
        devEstimate = estimate.devEstimate
        analystEstimate = estimate.analystEstimate
        testEstimate = estimate.testEstimate

    }
    abstract fun setTitle()


    abstract class StoryWithDueDate(num: Int, var dueDay: Int = 0):Story(num){
        abstract fun setDueDay()
    }

    abstract class StoryWithStabilityScore(num: Int, var stabilityScore: Int = 0):Story(num){
        fun setStabilityScore(){
            val mapping = Data.BaseComplexityRange.RANGE.zip(Data.StabilityScoreRange.RANGE).toMap()
            stabilityScore = mapping[baseComplexity]?: 0
        }
    }


    class UsualStory(num: Int): Story(num){
        override fun setLetterIndex() {
            letterIndex = Data.LetterIndexMap.MAP["UsualStory"] ?: ""
        }

        override fun setTitle() {
            title = "Стандартная история"
        }

    }

    class FixedDateStory(num: Int): StoryWithDueDate(num){
        override fun setLetterIndex() {
            letterIndex = Data.LetterIndexMap.MAP["FixedDateStory"] ?: ""
        }
        override fun setDueDay(){
            dueDay = Data.DueDayRange.getNext()
        }
        override fun setTitle() {
            title = "Крайний срок: $dueDay день"
        }
    }

    class OptimizationStory(num: Int): StoryWithStabilityScore(num){
        override fun setLetterIndex() {
            letterIndex = Data.LetterIndexMap.MAP["OptimizationStory"] ?: ""
        }
        override fun setTitle() {
            title = Data.OptimizationList.getNext()
        }
    }

    class ExpediteStory(num: Int): StoryWithDueDate(num){
        override fun setLetterIndex() {
            letterIndex = Data.LetterIndexMap.MAP["ExpediteStory"] ?: ""
        }

        override fun setDueDay() {
            dueDay = (3..5).random()
        }
        override fun setTitle() {
            title = "Срок: $dueDay дня"
        }
    }

    companion object{
        fun getBaseComplexity(): Int{
            return (Data.BaseComplexityRange.RANGE).random()
        }
    }

    class Estimate(
        var devEstimate: Int = 0,
        var analystEstimate: Int = 0,
        var testEstimate: Int = 0,
    )
    {
        constructor(baseComplexity: Int): this(){
            devEstimate = 4 + baseComplexity
            analystEstimate = (devEstimate/2.5 + (0..4).random()).toInt()
            testEstimate = (devEstimate/2.5 + (0..4).random()).toInt()
        }
    }

    abstract class StoryBuilder<T : StoryBuilder<T, S>, S:Story>{
        abstract val story:S
        fun setPrice():T{
            val mapping = Data.BaseComplexityRange.RANGE.zip(Data.PriceRange.RANGE).toMap()
            story.price = mapping[story.baseComplexity]?: 0
            return self()
        }

        fun setEstimate():T{
            val estimate = Estimate(story.baseComplexity)
            story.devEstimate = estimate.devEstimate
            story.analystEstimate = estimate.analystEstimate
            story.testEstimate = estimate.testEstimate
            return self()
        }
        abstract fun self(): T

        class UsualStoryBuilder(i: Int): StoryBuilder<UsualStoryBuilder, UsualStory>(){
            override val story: UsualStory = Story.UsualStory(i)
            override fun self(): UsualStoryBuilder {
                return this
            }

            fun setLetterIndex(): UsualStoryBuilder {
                story.letterIndex = Data.LetterIndexMap.MAP["UsualStory"] ?: ""
                return this
            }

            fun setTitle(): UsualStoryBuilder {
                story.title = "Стандартная история"
                return this
            }

            fun build(): UsualStory{
                return story
            }


        }
        class FixedDateStoryBuilder(i: Int): StoryBuilder<FixedDateStoryBuilder, FixedDateStory>(){
            override val story: FixedDateStory = FixedDateStory(i)
            override fun self(): FixedDateStoryBuilder {
                return this
            }

            fun setLetterIndex(): FixedDateStoryBuilder {
                story.letterIndex = Data.LetterIndexMap.MAP["UsualStory"] ?: ""
                return this
            }

            fun setTitle() :FixedDateStoryBuilder {
                story.title = "Крайний срок: $story.dueDay день"
                return this
            }

            fun setDueDay(): FixedDateStoryBuilder{
                story.dueDay = Data.DueDayRange.getNext()
                return this
            }

            fun build(): FixedDateStory{
                return story
            }


        }
    }
}

