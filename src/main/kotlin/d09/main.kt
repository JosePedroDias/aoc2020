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

fun part1(nums:List<Long>): Long {
    val pLen = 25
    val hLen = 25

    val pi = 0
    val pf = pLen

    val hi = 0//pf + 1
    val hf = pi + hLen

    val ni = hf + 1
    val nf = hf + 10

    val preamble = nums.subList(pi, pf)
    println("\npreamble (${preamble.size})")
    println(preamble)

    val recentHistory = nums.subList(hi, hf)
    println("\nrecentHistory (${recentHistory.size})")
    println(recentHistory)

    val possibleNexts = perm2(recentHistory).map {
        it.first + it.second
    }.toList()
    println("\npossibleNexts (${possibleNexts.size})")
    println(possibleNexts)

    val nexts = nums.subList(ni, nf)
    println("\nnexts (${nexts.size})")
    println(nexts)

    nexts.forEach {
        val found = possibleNexts.contains(it)
        if (!found) {
            println("NOT FOUND: $it")
        }
    }

    return 0
}

fun part2():Int {
    return 0
}

fun main() {
    val m = parseLines( parseInput() )

    val answer1 = part1(m)
    println("R1: $answer1\n")

    //val answer2 = part2(m)
    //println("R2: $answer2\n")
}