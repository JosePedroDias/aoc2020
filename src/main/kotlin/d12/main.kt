package d12

import common.Vec2di
import java.io.File
import java.lang.Exception
import kotlin.math.absoluteValue

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

val ANGLES = listOf(0, 90, 180, 270)

fun anglesTo90Times(deg:Int):Int {
    return ANGLES.indexOf(deg)
}

fun dirToDPos(dir:Char):Vec2di {
    return when(dir) {
        'N' -> Vec2di( 0, 1)
        'S' -> Vec2di( 0, -1)
        'W' -> Vec2di(-1, 0)
        'E' -> Vec2di( 1, 0)
        else -> throw Exception("Unsupported direction: $dir")
    }
}

/*
    https://en.wikipedia.org/wiki/Rotation_matrix

    x' = x * cos(t) - y * sin(t)
    y' = x * sin(t) + y * cos(t)

    for pi/2 gives:
    x' = -y
    y' =  x
 */
fun rotate90DegCcw(pos:Vec2di, invert:Boolean = false):Vec2di {
    val sign = if (invert) -1 else 1
    return Vec2di(
        -pos.y*sign,
         pos.x*sign
    )
}

fun manhattanDist(pos:Vec2di):Int {
    return pos.x.absoluteValue + pos.y.absoluteValue
}

fun simulate(instrs:Sequence<Pair<Char,Int>>, initialVersor:Vec2di, moveVersor:Boolean = false): Int {
    var shipPos = Vec2di(0, 0) // E, N
    var versor = initialVersor

    instrs.forEach {
        (op, num) ->
        //println("pos:$shipPos , dir:$versor , $op $num")
        when(op) {
            'N','S','E','W' -> {
                if (moveVersor)
                    versor += dirToDPos(op) * num
                else
                    shipPos += dirToDPos(op) * num
            }
            'L','R' -> {
                repeat(anglesTo90Times(num)) {
                    versor = rotate90DegCcw(versor, op =='R')
                }
            }
            'F' -> {
                shipPos += versor * num
            }
            else -> { throw Exception("Unsupported operation: $op")}
        }
    }

    //println("pos:$shipPos , dir:$versor")

    return manhattanDist(shipPos)
}

fun part1(instrs:Sequence<Pair<Char,Int>>):Int {
    return simulate(instrs, dirToDPos('E'))
}

// 10E 1N -> ship to waypoint
fun part2(instrs:Sequence<Pair<Char,Int>>):Int {
    return simulate(instrs, Vec2di(10, 1), true) // E, N
}

fun main() {
    val m = parseLines( parseInput() )

    val answer1 = part1(m)
    println("R1: $answer1\n")

    val answer2 = part2(m)
    println("R2: $answer2\n")
}