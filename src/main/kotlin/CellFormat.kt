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
    var alignment: HorizontalAlignment = HorizontalAlignment.GENERAL,
    var font: XSSFFont = wb.createFont()
)
{
    constructor(style: XSSFCellStyle): this(
        borderLeft= style.borderLeft,
        borderTop= style.borderTop,
        borderRight= style.borderRight,
        borderBottom= style.borderBottom,
        fillForegroundColor= style.fillForegroundColor,
        fillPattern= style.fillPattern,
        alignment= style.alignment,
        font = style.font
    ){

    }

    fun toCacheString(): String{
        return "$borderLeft-$borderTop-$borderRight-$borderBottom-$fillForegroundColor-$fillPattern-$alignment-$font"
    }
}

object StyleCache{

    private val styleCache: MutableMap<String, XSSFCellStyle> = mutableMapOf()
    fun getOrCreateStyle(format: MyCellFormat): XSSFCellStyle{
        var a = format.toCacheString()
        val _styleCache = styleCache
        if (styleCache[format.toCacheString()] == null){
            val style = wb.createCellStyle()
            style.borderLeft = format.borderLeft
            style.borderTop = format.borderTop
            style.borderRight = format.borderRight
            style.borderBottom = format.borderBottom
            style.fillForegroundColor = format.fillForegroundColor
            style.fillPattern = format.fillPattern
            style.alignment = format.alignment
            style.setFont(format.font)
            styleCache[format.toCacheString()] = style
        }
        return styleCache[format.toCacheString()]!!

    }

}