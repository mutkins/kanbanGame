abstract class Story(
    private var num: Int = 0,
    letterIndex: String = "",
    private var price: Int = 0,
    private var analystEstimate: Int = 0,
    private var devEstimate: Int = 0,
    private var testEstimate: Int = 0,
    protected var baseComplexity: Int = getBaseComplexity(),
    protected var title: String = ""
):Card(letterIndex){

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


    abstract class StoryWithDueDate(num: Int, protected var dueDay: Int = 0):Story(num){
        abstract fun setDueDay()
    }

    abstract class StoryWithStabilityScore(num: Int, private var stabilityScore: Int = 0):Story(num){
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
}

