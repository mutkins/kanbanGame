abstract class Story(
    var num: Int = 0,
    var price: Int = 0,
    var analystEstimate: Int = 0,
    var devEstimate: Int = 0,
    var testEstimate: Int = 0,
    var baseComplexity: Int = getBaseComplexity(),
    var title: String = ""
):Card(){

    abstract class StoryWithDueDate(num: Int, var dueDay: Int = 0):Story(num)
    abstract class StoryWithStabilityScore(num: Int, var stabilityScore: Int = 0):Story(num)
    class UsualStory(num: Int): Story(num)

    class FixedDateStory(num: Int): StoryWithDueDate(num)

    class OptimizationStory(num: Int): StoryWithStabilityScore(num)

    class ExpediteStory(num: Int): StoryWithDueDate(num)

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

