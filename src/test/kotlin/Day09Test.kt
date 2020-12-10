import d09.parseLines
import org.junit.Test
import org.junit.Assert.assertEquals

import d09.part1
import d09.part2

val ex9_1 = """35
20
15
25
47
40
62
55
65
95
102
117
150
182
127
219
299
277
309
576""".split("\n").asSequence()

val ex9_2 = """35
20
15
25
47
40
62
55
65
95
102
117
150
182
127
219
299
277
309
576""".split("\n").asSequence()

class Day09Test {
    @Test
    fun checkEx1() {
        assertEquals(127L, part1(parseLines(ex9_1), 5))
    }

    @Test
    fun checkEx2() {
        assertEquals(62L, part2(parseLines(ex9_2), 127))
    }
}