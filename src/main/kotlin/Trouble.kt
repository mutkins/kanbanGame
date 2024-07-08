abstract class Trouble(
    private var rate: Int,
    protected var dangerScore: Int = 0
):TextCard()
{
    override fun setLetterIndex() {
        letterIndex = Data.LetterIndexMap.MAP["Trouble"] ?: ""
    }
    abstract fun setDangerScore()

    class HarmlessTrouble(rate: Int = 0):Trouble(rate){
        override fun setText() {
            text = Data.HarmlessTroubleList.getNext()
        }

        override fun setDangerScore() {
            dangerScore = Data.TroubleRange.Harmless.RANGE.random()
        }
    }

    class EasyTrouble(rate: Int = 1):Trouble(rate){
        override fun setText() {
            text = Data.EasyTroubleList.getNext()
        }
        override fun setDangerScore() {
            dangerScore = Data.TroubleRange.Easy.RANGE.random()
        }
    }

    class SeriousTrouble(rate: Int = 2):Trouble(rate){
        override fun setText() {
            text = Data.SeriousTroubleList.getNext()
        }
        override fun setDangerScore() {
            dangerScore = Data.TroubleRange.Serious.RANGE.random()
        }
    }

    class AwfulTrouble(rate: Int = 3):Trouble(rate){
        override fun setText() {
            text = Data.AwfulTroubleList.getNext()
        }
        override fun setDangerScore() {
            dangerScore = Data.TroubleRange.Awful.RANGE.random()
        }
    }

}