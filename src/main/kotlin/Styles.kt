import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors

class Styles {
    companion object{
        val ANALYST_RANGE_COLOR = "FF0000"
        val DEV_RANGE_COLOR = "00B0F0"
        val TEST_RANGE_COLOR = "92D050"
        val OUTLINE_BORDER = BorderStyle.NONE
        val RANGE_BORDER = BorderStyle.THIN
        val FIRST_RANGE_COLOR = IndexedColors.RED.getIndex()
        val SECOND_RANGE_COLOR = IndexedColors.BLUE.getIndex()
        val THIRD_RANGE_COLOR = IndexedColors.YELLOW.getIndex()
        val FILL_PATTERN_CARDS = FillPatternType.SPARSE_DOTS
        val FILL_PATTERN_RANGES = FillPatternType.ALT_BARS
        val DEFAULT_COLOR = IndexedColors.WHITE.getIndex()
        val FRONTSIDE_USUAL_STORY_COLOR = IndexedColors.LIGHT_GREEN.getIndex()
        val FRONTSIDE_FIXED_STORY_COLOR = IndexedColors.LIGHT_BLUE.getIndex()
        val FRONTSIDE_OPTIMIZATION_STORY_COLOR = IndexedColors.YELLOW.getIndex()
        val FRONTSIDE_EXPEDITE_STORY_COLOR = IndexedColors.RED.getIndex()
        val TROUBLE_COLOR = DEFAULT_COLOR
        val MODIFICATION_COLOR = DEFAULT_COLOR
        val BACKSIDE_TROUBLE_COLOR = IndexedColors.RED.getIndex()
        val BACKSIDE_MODOFICATION_COLOR = IndexedColors.GREY_80_PERCENT.getIndex()
        val BACKSIDE_USUAL_COLOR = FRONTSIDE_USUAL_STORY_COLOR
        val BACKSIDE_FIXED_COLOR = FRONTSIDE_FIXED_STORY_COLOR
        val BACKSIDE_OPTIMIZATION_COLOR = FRONTSIDE_OPTIMIZATION_STORY_COLOR
        val BACKSIDE_EXPEDITE_COLOR = FRONTSIDE_EXPEDITE_STORY_COLOR
        val BACKSIDE_PATTERN = FillPatternType.FINE_DOTS
        val DEFAULT_PATTERN = FillPatternType.NO_FILL
        val FRONTSIDE_CARD_PATTERN = FillPatternType.SPARSE_DOTS
        val COLUMN_COUNT = 40
        val ROWS_COUNT = 6000
    }
}