import org.junit.Test
import org.junit.Assert.assertEquals

import d11.step
import d11.Matrix
import d11.OCCUPIED

val ex11_1 = """L.LL.LL.LL
LLLLLLL.LL
L.L.L..L..
LLLL.LL.LL
L.LL.LL.LL
L.LLLLL.LL
..L.L.....
LLLLLLLLLL
L.LLLLLL.L
L.LLLLL.LL""".split("\n")

class Day11Test {
    @Test
    fun testStep() {
        val m1 = Matrix(ex11_1)

        val m2 = step(m1)
        assertEquals("""#.##.##.##
#######.##
#.#.#..#..
####.##.##
#.##.##.##
#.#####.##
..#.#.....
##########
#.######.#
#.#####.##""", m2.toString())

        val m3 = step(m2)
        assertEquals("""#.LL.L#.##
#LLLLLL.L#
L.L.L..L..
#LLL.LL.L#
#.LL.LL.LL
#.LLLL#.##
..L.L.....
#LLLLLLLL#
#.LLLLLL.L
#.#LLLL.##""", m3.toString())

        val m4 = step(m3)
        assertEquals("""#.##.L#.##
#L###LL.L#
L.#.#..#..
#L##.##.L#
#.##.LL.LL
#.###L#.##
..#.#.....
#L######L#
#.LL###L.L
#.#L###.##""", m4.toString())

        val m5 = step(m4)
        assertEquals("""#.#L.L#.##
#LLL#LL.L#
L.L.L..#..
#LLL.##.L#
#.LL.LL.LL
#.LL#L#.##
..L.L.....
#L#LLLL#L#
#.LLLLLL.L
#.#L#L#.##""", m5.toString())

        assertEquals(false, m5 == m4)

        val m6 = step(m5)
        assertEquals("""#.#L.L#.##
#LLL#LL.L#
L.#.L..#..
#L##.##.L#
#.#L.LL.LL
#.#L#L#.##
..L.L.....
#L#L##L#L#
#.LLLLLL.L
#.#L#L#.##""", m6.toString())

        val m7 = step(m6)
        assertEquals("""#.#L.L#.##
#LLL#LL.L#
L.#.L..#..
#L##.##.L#
#.#L.LL.LL
#.#L#L#.##
..L.L.....
#L#L##L#L#
#.LLLLLL.L
#.#L#L#.##""", m7.toString())
        assertEquals(true, m6 == m7)

        assertEquals(37, m7.ofKind(OCCUPIED))
    }
}