import org.junit.Assert
import org.junit.Test
import d06.parseLines
import d06.part1
import d06.part2

val EXAMPLE = """abc

a
b
c

ab
ac

a
a
a
a

b""".split("\n").asSequence()

class Day06Test {
    @Test
    fun parseLines() {
        val feedback = parseLines(EXAMPLE)
        Assert.assertEquals(feedback[0], listOf(setOf('a', 'b', 'c')))
        Assert.assertEquals(feedback[1], listOf(setOf('a'), setOf('b'), setOf('c')))
        Assert.assertEquals(feedback[2], listOf(setOf('a', 'b'), setOf('a', 'c')))
        Assert.assertEquals(feedback[3], listOf(setOf('a'),setOf('a'), setOf('a'), setOf('a')))
        Assert.assertEquals(feedback[4], listOf(setOf('b')))
    }

    @Test
    fun part1() {
        val feedback = parseLines(EXAMPLE)
        Assert.assertEquals(part1(feedback), 11)
    }

    @Test
    fun part2() {
        val feedback = parseLines(EXAMPLE)
        Assert.assertEquals(part2(feedback), 6)
    }
}