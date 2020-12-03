import org.junit.Test
import org.junit.Assert.assertEquals

import d03.part1
import d03.WrappingXMatrix
import kotlin.test.assertFails

class Day03Test {
    @Test
    fun basicMatrix() {
val input = """..##.......
#X..#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#""".split("\n")
        val m = WrappingXMatrix(input)

        assertEquals(11, input.size)
        assertEquals(11, input[0].length)

        assertEquals(11, m.width)
        assertEquals(11, m.height)

        assertEquals('#', m.getCell(0, 1))
        assertEquals('X', m.getCell(1, 1))
        assertEquals('.', m.getCell(0, 2))

        assertEquals('#', m.getCell(0 + m.width, 1))
        assertFails { m.getCell(0, 1+m.height) }
    }

    @Test
    fun sample01() {
    }
}
