import java.io.File

class Day01 {
    internal fun setup() {
        val lst = mutableListOf<Int>()
        File("aoc/01.txt").forEachLine { lst.add( it.toInt()  ) }
        println(part1(lst))
    }

    fun part1(lst:List<Int>): Int? {
        val comb = combine2(lst, false)
        val goal = 2000
        val winningPair = comb.firstOrNull{
            val (a, b) = it
            val sum = a + b
            val found = sum == goal
            println("$a + $b => $sum ($found)")
            found
        }
        if (winningPair != null) {
            val (a, b) = winningPair
            val res = a * b
            println("$a * $b => $res")
            return res
        }
        println("pair not found?")
        return null
    }
}

fun main() {
    Day01().setup()
}