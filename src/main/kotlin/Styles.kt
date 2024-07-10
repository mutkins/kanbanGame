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
        val FILL_PATTERN_GLOBAL = FillPatternType.FINE_DOTS
        val USUAL_STORY_COLOR = IndexedColors.LIGHT_GREEN.getIndex()
        val FIXED_STORY_COLOR = IndexedColors.LIGHT_BLUE.getIndex()
        val OPTIMIZATION_STORY_COLOR = IndexedColors.YELLOW.getIndex()
        val EXPEDITE_STORY_COLOR = IndexedColors.RED.getIndex()
        val DEFAULT_COLOR = IndexedColors.WHITE.getIndex()
        val COLUMN_COUNT = 40
        val ROWS_COUNT = 400
    }
}