import org.apache.poi.xssf.usermodel.XSSFFont
import Utils.wb
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFCellStyle

class MyCellFormat(
    var borderLeft: BorderStyle = BorderStyle.NONE,
    var borderTop: BorderStyle = BorderStyle.NONE,
    var borderRight: BorderStyle = BorderStyle.NONE,
    var borderBottom: BorderStyle = BorderStyle.NONE,
    var fillForegroundColor: Short = Styles.DEFAULT_COLOR,
    var fillPattern: FillPatternType = FillPatternType.NO_FILL,
    var horizontalAlignment: HorizontalAlignment = HorizontalAlignment.GENERAL,
    var font: FontFormat = FontFormat(wb.createFont()),
    var wrapText: Boolean = false,
    var verticalAlignment: VerticalAlignment = VerticalAlignment.BOTTOM
)
{
    constructor(style: XSSFCellStyle): this(
        borderLeft= style.borderLeft,
        borderTop= style.borderTop,
        borderRight= style.borderRight,
        borderBottom= style.borderBottom,
        fillForegroundColor= style.fillForegroundColor,
        fillPattern= style.fillPattern,
        horizontalAlignment= style.alignment,
        font = FontFormat(style.font),
        verticalAlignment = style.verticalAlignment,
        wrapText = style.wrapText
    )

    fun toCacheString(): String{
        println("$borderLeft-$borderTop-$borderRight-$borderBottom-$fillForegroundColor-$fillPattern-$horizontalAlignment-$verticalAlignment-$wrapText-${font.toCacheString()}")
        return "$borderLeft-$borderTop-$borderRight-$borderBottom-$fillForegroundColor-$fillPattern-$horizontalAlignment-$verticalAlignment-$wrapText-${font.toCacheString()}"
    }
}

class FontFormat(
    var bold: Boolean = false
){
    constructor(font: Font): this(){
        bold = font.bold
    }
    fun toCacheString(): String{
        return "$bold"
    }
}

object StyleCache{

    private val styleCache: MutableMap<String, XSSFCellStyle> = mutableMapOf()

    fun getOrCreateStyle(format: MyCellFormat): XSSFCellStyle{
        return styleCache.getOrPut(format.toCacheString()){
            wb.createCellStyle().apply {
                borderLeft = format.borderLeft
                borderTop = format.borderTop
                borderRight = format.borderRight
                borderBottom = format.borderBottom
                fillForegroundColor = format.fillForegroundColor
                fillPattern = format.fillPattern
                alignment = format.horizontalAlignment
                wrapText = format.wrapText
                verticalAlignment = format.verticalAlignment
                setFont(getOrCreateFont(format.font))
            }
        }

    }
    private val fontCache: MutableMap<String, XSSFFont> = mutableMapOf()
    fun getOrCreateFont(fontFormat: FontFormat): XSSFFont{
        return fontCache.getOrPut(fontFormat.toCacheString()){
            wb.createFont().apply {
                bold = fontFormat.bold
            }
        }
    }

}