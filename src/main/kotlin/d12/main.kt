package d12

import java.io.File
import java.lang.Exception

internal fun parseInput(): Sequence<String> {
    return File("aoc/12.txt").readLines().asSequence()
}

fun parseLines(lines:Sequence<String>):Sequence<Pair<Char,Int>> {
    return lines.map {
        val op = it[0]
        val num = it.substring(1).toInt()
        Pair(op, num)
    }
}

val DIRS = listOf('N', 'W', 'S', 'E')
val ANGLES = listOf(0, 90, 180, 270)

fun turnLeft(dir:Char, angleDeg:Int):Char {
    val dIdx = DIRS.indexOf(dir)
    val aIdx = ANGLES.indexOf(angleDeg)
    val dIdx2 = (dIdx + aIdx) % 4
    return DIRS[dIdx2]
}

val DIRS2 = listOf('N', 'E', 'S', 'W')
fun turnRight(dir:Char, angleDeg:Int):Char {
    val dIdx = DIRS2.indexOf(dir)
    val aIdx = ANGLES.indexOf(angleDeg)
    val dIdx2 = (dIdx + aIdx) % 4
    return DIRS2[dIdx2]
}

fun forward(dir:Char, units:Int):Pair<Int,Int> {
    return when (dir) {
        'N' -> Pair( 0,         -1 * units)
        'S' -> Pair( 0,          1 * units)
        'E' -> Pair( 1 * units,  0)
        'W' -> Pair(-1 * units,  0)
        else -> throw Exception("Unsupported direction: $dir")
    }
}

fun part1(instrs:Sequence<Pair<Char,Int>>): Int {
    var x = 0
    var y = 0
    var dir = 'E'

    instrs.forEach {
        (op, num) ->
        when(op) {
            'N' -> { y -= num }
            'S' -> { y += num }
            'E' -> { x += num }
            'W' -> { x -= num }
            'L' -> { dir = turnLeft(dir, num) }
            'R' -> { dir = turnRight(dir, num) }
            'F' -> { val d = forward(dir, num); x += d.first ; y += d.second }
            else -> { throw Exception("Unsupported operation: $op")}
        }
    }

    return x + y
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