package d01

import common.combine2seq
import common.combine3seq
import java.io.File

internal fun parseInput(): List<Int> {
    val lst = mutableListOf<Int>()
    File("aoc/01.txt").forEachLine { lst.add(it.toInt()) }
    return lst
}

fun part1(lst: List<Int>): Int? {
    val comb = combine2seq(lst, false)
    val goal = 2020
    val pair = comb.firstOrNull {
        val (a, b) = it
        val sum = a + b
        val found = sum == goal
        //println("$a + $b => $sum ($found)")
        found
    }
    if (pair != null) {
        val (a, b) = pair
        val res = a * b
        println("$a * $b => $res")
        return res
    }
    println("pair not found?")
    return null
}

fun part2(lst: List<Int>): Int? {
    val comb = combine3seq(lst, false)
    val goal = 2020
    val trio = comb.firstOrNull {
        val (a, b, c) = it
        val sum = a + b + c
        val found = sum == goal
        //println("$a + $b + $c => $sum ($found)")
        found
    }
    if (trio != null) {
        val (a, b, c) = trio
        val res = a * b * c
        println("$a * $b * $c => $res")
        return res
    }
    println("trio not found?")
    return null
}

fun main() {
    val l = parseInput()

    val answer1 = part1(l)
    println("R1: $answer1\n")

    val answer2 = part2(l)
    println("\nR2: $answer2\n")
}
