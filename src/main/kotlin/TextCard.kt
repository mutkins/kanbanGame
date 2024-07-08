abstract class TextCard(
    protected var text: String = "",
): Card() {
    abstract fun setText()
}