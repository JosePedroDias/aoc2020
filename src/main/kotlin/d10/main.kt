package d10

import java.io.File

internal fun parseInput(): Sequence<String> {
    return File("aoc/10.txt").readLines().asSequence()
}

fun parseLines(lines:Sequence<String>):List<Int> {
    val out = mutableListOf<Int>()
    lines.forEach {
        out.add(it.toInt())
    }
    return out.sorted()
}

// choose the highest in the list, add 3 (goal)
// must connect them all such that
// we end with highest + 3 jolts
// every connection -3 or +1, +2 or +3

fun getAllJolts(lst:List<Int>): List<Int> {
    return listOf(0) + lst + listOf(lst.last()+3)
}

fun toDeltas(lst:List<Int>): List<Int> {
    return lst.zipWithNext().map { (a, b) -> b - a }
}

fun part1(lst:List<Int>): Int {
    val deltas = toDeltas(getAllJolts(lst))
    val counts = deltas.groupingBy { it }.eachCount()
    return counts.getValue(1) * counts.getValue(3)
}

fun part2(lst:List<Int>): Long {
    val deltas = getAllJolts(lst)
    val paths = mutableMapOf<Int,Long>() // cache
    paths[0] = 1L
    deltas.drop(1).forEach {
        paths[it] = (1..3)
            .map { last -> paths.getOrDefault(it - last, 0) }
            .sum()
    }
    return paths[deltas.last()] ?: 0
}

fun main() {
    val m = parseLines( parseInput() )

    val answer1 = part1(m)
    println("R1: $answer1\n")

    val answer2 = part2(m)
    println("R2: $answer2\n")
}