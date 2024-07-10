abstract class TextCard(
    var text: String = "",
): Card() {
    abstract fun setText()
}