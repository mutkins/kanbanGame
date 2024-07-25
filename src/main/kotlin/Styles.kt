import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors

class Styles {
    companion object{
        val ANALYST_RANGE_COLOR = "FF0000"
        val DEV_RANGE_COLOR = "00B0F0"
        val TEST_RANGE_COLOR = "92D050"
        val OUTLINE_BORDER = BorderStyle.THICK
        val RANGE_BORDER = BorderStyle.THIN
        val FIRST_RANGE_COLOR = IndexedColors.RED.getIndex()
        val SECOND_RANGE_COLOR = IndexedColors.BLUE.getIndex()
        val THIRD_RANGE_COLOR = IndexedColors.YELLOW.getIndex()
        val FILL_PATTERN_CARDS = FillPatternType.SPARSE_DOTS
        val FILL_PATTERN_RANGES = FillPatternType.ALT_BARS
        val DEFAULT_COLOR = IndexedColors.WHITE.getIndex()
        val USUAL_STORY_COLOR = IndexedColors.LIGHT_GREEN.getIndex()
        val FIXED_STORY_COLOR = IndexedColors.LIGHT_BLUE.getIndex()
        val OPTIMIZATION_STORY_COLOR = IndexedColors.YELLOW.getIndex()
        val EXPEDITE_STORY_COLOR = IndexedColors.RED.getIndex()
        val TROUBLE_COLOR = DEFAULT_COLOR
        val MODIFICATION_COLOR = DEFAULT_COLOR
        val BACKSIDE_TROUBLE_COLOR = IndexedColors.RED.getIndex()
        val BACKSIDE_PATTERN = FillPatternType.ALT_BARS
        val COLUMN_COUNT = 40
        val ROWS_COUNT = 6000
    }
}