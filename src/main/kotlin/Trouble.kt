abstract class Trouble(var rate: Int, var dangerScore: Int = 0):TextCard()
{
    class HarmlessTrouble(rate: Int = 0):Trouble(rate)

    class EasyTrouble(rate: Int = 1):Trouble(rate)

    class SeriousTrouble(rate: Int = 2):Trouble(rate)

    class AwfulTrouble(rate: Int = 3):Trouble(rate)

}