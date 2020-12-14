import d13.parseInput
import d13.parseLines
import org.junit.Test
import org.junit.Assert.assertEquals

import d13.part1

val EX_13 = """939
7,13,x,x,59,x,31,19""".split("\n")

class Day13Test {
    @Test
    fun parseTest() {
        val o = parseLines(EX_13)
        assertEquals(939L, o.first)
        assertEquals(listOf(7L,13L,59L,31L,19L), o.second)
    }

    @Test
    fun part1Test() {
        val res = part1(parseLines(EX_13))
        assertEquals(295L, res)
    }
}