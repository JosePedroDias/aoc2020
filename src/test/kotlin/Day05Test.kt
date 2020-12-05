import org.junit.Test
import org.junit.Assert.assertEquals

import d05.calcSeatId

class Day05Test {
    @Test
    fun seatId() {
        assertEquals(calcSeatId("FBFBBFFRLR"), 357)
        assertEquals(calcSeatId("BFFFBBFRRR"), 567)
        assertEquals(calcSeatId("FFFBBBFRRR"), 119)
        assertEquals(calcSeatId("BBFFBBFRLL"), 820)
    }
}
