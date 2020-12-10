import d10.parseInput
import org.junit.Test
import org.junit.Assert.assertEquals

import d10.parseLines
import d10.part1

val ex10_1 = """16
10
15
5
1
11
7
19
6
12
4""".split("\n").asSequence()

val ex10_2 = """28
33
18
42
31
14
46
20
48
47
24
23
49
45
19
38
39
11
1
32
25
35
8
17
7
9
4
2
34
10
3""".split("\n").asSequence()

class Day10Test {
    @Test
    fun checkEx1() {
        val adapters = parseLines(ex10_1)
        assertEquals(7 * 5, part1(adapters))
    }

    @Test
    fun checkEx2() {
        val adapters = parseLines(ex10_2)
        assertEquals(22 * 10, part1(adapters))
    }
}