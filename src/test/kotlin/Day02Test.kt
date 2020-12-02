import org.junit.Test
import org.junit.Assert.assertEquals

import d02.part1
import d02.PwReq

class Day02Test {
    @Test
    fun sample01() {
        val lst = listOf(
            PwReq(1, 3, 'a', "abcde"),
            PwReq(1, 3, 'b', "cdefg"),
            PwReq(2, 9, 'c', "ccccccccc"),
        )
        assertEquals(
            part1(lst), 2
        )
    }
}
