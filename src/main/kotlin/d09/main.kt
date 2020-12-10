package d09

import common.perm2
import java.io.File

internal fun parseInput(): Sequence<String> {
    return File("aoc/09.txt").readLines().asSequence()
}

fun parseLines(lines:Sequence<String>):List<Long> {
    val nums = mutableListOf<Long>()
    lines.forEach {
        nums.add(it.toLong())
    }
    return nums
}

fun part1(nums:List<Long>, preambleSize:Int): Long? {
    val past = ArrayDeque<Long>()

    nums.forEachIndexed {
        i, n ->

        if (i >= preambleSize) {
            val possibleNexts = perm2(past).map {
                it.first + it.second
            }.toList()

            if (!possibleNexts.contains(n)) {
                return n
            }
        }

        past.addFirst(n)
        if (past.size > preambleSize) {
            past.removeLast()
        }
    }

    return null
}

fun part2(nums:List<Long>, target:Long):Long? {
    val past = mutableListOf<Long>()

    nums.forEachIndexed {
        l, n ->
        past.add(n)

        for (i in 1..l) {
            val subPast = past.subList(l-i, l)
            if (subPast.sum() == target) {
                val a = subPast.minOrNull()
                val b = subPast.maxOrNull()
                if (a != null && b != null) {
                    return a + b
                }
            }
        }
    }
    return null
}

fun main() {
    val m = parseLines( parseInput() )

    val answer1 = part1(m, 25)
    println("R1: $answer1\n")

    if (answer1 != null) {
        val answer2 = part2(m, answer1)
        println("R2: $answer2\n")
    }
}