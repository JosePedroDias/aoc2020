package d10

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
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

// count differences of 1, differences of 3
// return d1 * d3

fun isValidNext(from:Int, to:Int, goal:Int):Boolean {
    return to in from+1..from+3 && to <= goal
}

fun step(visited:PersistentList<Int>, yetToVisit:PersistentList<Int>, goal:Int): PersistentList<Int>? {
    val from = visited.last()
    //println("$visited $yetToVisit")
    if (yetToVisit.isEmpty()) {
        return visited
    }
    yetToVisit.forEach {
        to ->
        if (isValidNext(from, to, goal)) {
            val res = step(visited.add(to), yetToVisit.remove(to), goal)
            if (res != null) {
                return res
            }
        }
    }
    return null
}


fun countTransitions(lst:List<Int>):Pair<Int, Int> {
    val deltas = lst.zipWithNext().map {
        (a, b) ->
        b - a
    }
    val ones = deltas.filter { it == 1 }.count()
    val threes = deltas.filter { it == 3 }.count()

    return Pair(ones, threes + 1) // TODO WHY?
}

fun part1(startAdapters:List<Int>): Int {
    val goal = startAdapters.maxOrNull() ?: return 0

    val visited = persistentListOf(0)
    val yetToVisit = startAdapters.sorted().toPersistentList()

    val steps = step(visited, yetToVisit, goal) ?: return 0
    val transitions = countTransitions(steps)
    return transitions.first * transitions.second
}

fun part2(startAdapters:List<Int>):Int {
    val sorted =startAdapters.sorted()
    val set = sorted.toSet()
    //val goal = sorted.last()

    val tos = hashMapOf<Int,List<Int>>()
    tos[0] = mutableListOf(startAdapters.first())

    sorted.subList(0, sorted.size-1).forEach {
        val l = mutableListOf<Int>()
        for (t in it+1..it+3) {
            if (set.contains(t)) {
                l.add(t)
            }
        }
        tos[it] = l
    }

    println(tos)
    // TODO contiue

    return 0
}

fun main() {
    val m = parseLines( parseInput() )

    val answer1 = part1(m)
    println("R1: $answer1\n")

    val answer2 = part2(m)
    println("R2: $answer2\n")
}