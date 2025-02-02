package d05

import java.io.File

val WHITESPACE_LINE_REGEX = Regex("""\s*""")

val FIELD_REGEX = Regex("""\s*""")

internal fun parseInput(): Sequence<String> {
    return File("aoc/05.txt").readLines().asSequence()
}

// rows: 7x FB -> 0 - 127
// cols: 3x LR -> 0 -> 7
// seatId = rowNr * 8 + colNr

const val F = 'F' // lower
const val B = 'B'
const val L = 'L' // lower
const val R = 'R'

const val ROWS = 128
const val COLS = 8

fun bspToSeat(bsp:String):Pair<Int,Int> {
    var rowStep = ROWS
    var row = 0
    val bspRows = bsp.subSequence(0, 7)
    bspRows.forEach {
        rowStep /= 2
        if (it == B) { row += rowStep }
        else if (it != F) { throw Error("Unsupported char $it!") }
    }

    var colStep = COLS
    var col = 0
    val bspCols = bsp.subSequence(7, 10)
    bspCols.forEach {
        colStep /= 2
        if (it == R) { col += colStep }
        else if (it != L) { throw Error("Unsupported char $it!") }
    }

    return Pair(row, col)
}

fun pairToSeatId(seat:Pair<Int,Int>):Int {
    return 8*seat.first + seat.second
}

fun calcSeatId(bsp:String):Int {
    return pairToSeatId( bspToSeat(bsp) )
}

fun part1(bsps:Sequence<String>): Int? {
    return bsps
        .map { calcSeatId(it) }.maxOrNull()
}

fun part2(bsps:Sequence<String>):Int? {
    val rowSeatIds = bsps
        .map { bspToSeat(it) }
        .filter { it.first in 1..126 }
        .map { pairToSeatId(it)  }.sorted()

    val sortedIdPairs = rowSeatIds.zipWithNext()

    sortedIdPairs.forEach {
        val (a, b) = it
        if (b - a == 2) {
            return a + 1
        }
    }

    return null
}

fun main() {
    val bsps = parseInput()

    val answer1 = part1(bsps)
    println("R1: $answer1\n")

    val answer2 = part2(bsps)
    println("R2: $answer2\n")
}