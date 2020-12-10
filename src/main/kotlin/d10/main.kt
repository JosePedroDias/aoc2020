package d10

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import java.io.File

internal fun parseInput(): Sequence<String> {
    return File("aoc/10.txt").readLines().asSequence()
}

fun parseLines(lines:Sequence<String>):PersistentList<Int> {
    val out = mutableListOf<Int>()
    lines.forEach {
        out.add(it.toInt())
    }
    return out.toPersistentList()
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

fun step(pair:Pair<PersistentList<Int>, PersistentList<Int>>, goal:Int): List<Pair<PersistentList<Int>, PersistentList<Int>>> {
    val (visited, yetToVisit) = pair
    val from = visited.last()
    //println("from:$from #ad:${adapters.size} goal:$goal")
    return yetToVisit.fold(mutableListOf()) {
        acc, to ->
        if (isValidNext(from, to, goal)) {
            //println("$from -> $to")
            acc.add(Pair(visited.add(to), yetToVisit.remove(to)))
        }
        acc
    }
}

fun countTransitions(lst:List<Int>):Pair<Int, Int> {
    val deltas = lst.zipWithNext().map {
        (a, b) -> b - a
    }
    val ones = deltas.filter { it == 1 }.count()
    val threes = deltas.filter { it == 3 }.count()

    return Pair(ones, threes + 1) // TODO WHY?
}

fun part1(startAdapters:PersistentList<Int>): Int {
    val goal = startAdapters.maxOrNull() ?: return 0
    // println("goal: $goal")

    var alternatives = listOf(
        Pair(persistentListOf(0), startAdapters.sorted().toPersistentList())
    )

    do {
        //println(alternatives)
        alternatives = alternatives.fold(emptyList()) {
            acc, pair -> acc + step(pair, goal)
        }
    } while (alternatives.first().second.isNotEmpty())

    val steps = alternatives.first().first
    val transitions = countTransitions(steps)

    return transitions.first * transitions.second
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