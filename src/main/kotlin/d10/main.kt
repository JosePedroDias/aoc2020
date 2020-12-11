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

val resultOf = hashMapOf<String, Boolean>()

fun serializeArgs(from:Int, yetToVisit:PersistentList<Int>):String {
    return "$from/$yetToVisit" //yetToVisit.add(0, from).joinToString(",")
}

fun step2(visited:PersistentList<Int>, yetToVisit:PersistentList<Int>, goal:Int, onFound:()->Unit) {
    val from = visited.last()
    println("$from -> $yetToVisit")

    if (yetToVisit.isEmpty()) {
        onFound()
        println("!!!! $visited")
        //resultOf[serializeArgs(from, yetToVisit)] = true
        //return
    }

    /*val cachedRes = resultOf.getOrDefault(serializeArgs(from, yetToVisit), null)
    if (cachedRes != null) {
        if (cachedRes) {
            onFound()
        }
        return cachedRes
    }*/

    yetToVisit.forEach {
        to ->
        if (isValidNext(from, to, goal)) {
            val nextVisited = visited.add(to)
            val nextYetToVisit = yetToVisit.remove(to)
            step2(nextVisited, nextYetToVisit, goal, onFound)
        }
    }
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

fun part1(startAdapters:PersistentList<Int>): Int {
    val goal = startAdapters.maxOrNull() ?: return 0

    val visited = persistentListOf(0)
    val yetToVisit = startAdapters.sorted().toPersistentList()

    val steps = step(visited, yetToVisit, goal) ?: return 0
    val transitions = countTransitions(steps)
    return transitions.first * transitions.second
}

fun part2(startAdapters:PersistentList<Int>):Int {
    val goal = startAdapters.maxOrNull() ?: return 0

    val visited = persistentListOf(0)
    val yetToVisit = startAdapters.sorted().toPersistentList()

    var found = 0
    fun onFound() { ++found }

    step2(visited, yetToVisit, goal, ::onFound)

    return found
}

fun main() {
    val m = parseLines( parseInput() )

    val answer1 = part1(m)
    println("R1: $answer1\n")

    //val answer2 = part2(m)
    //println("R2: $answer2\n")
}