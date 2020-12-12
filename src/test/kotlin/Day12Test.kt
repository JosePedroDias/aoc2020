import d12.parseLines
import org.junit.Test
import org.junit.Assert.assertEquals

import d12.part1
import d12.part2

val EX_12A = """F10
N3
F7
R90
F11""".split("\n").asSequence()

class Day12Test {
    @Test
    fun sample01() {
        val v = part1( parseLines(EX_12A) )
        assertEquals(25, v)
    }

    @Test
    fun sample02() {
        val v = part2( parseLines(EX_12A) )
        assertEquals(286, v)
    }
}