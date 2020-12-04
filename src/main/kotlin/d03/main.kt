package d03

import java.io.File

const val TREE = '#'

data class WrappingXMatrix(private val lines: List<String>) {
    val width: Int = lines[0].length

    val height: Int = lines.size

    fun getCell(x:Int, y:Int):Char {
        return lines[y][x % width]
    }
}

internal fun parseInput(): WrappingXMatrix {
    val lines = mutableListOf<String>()
    File("aoc/03.txt").forEachLine {
        lines.add(it)
    }
    return WrappingXMatrix(lines)
}

fun part1(m:WrappingXMatrix, slope:Pair<Int,Int>):Long {
    val dx = slope.first
    var x = 0
    var treesFound = 0L

    for (y in 0 until m.height step slope.second) {
        if (m.getCell(x, y) == TREE) {
            ++treesFound
        }
        x += dx
    }

    return treesFound
}

fun part2(m:WrappingXMatrix, slopes:List<Pair<Int,Int>>):Long {
    // println(slopes.map{ part1(m, it) })
    return slopes.map{ part1(m, it) }.reduce { acc, item -> item * acc }
}

fun main() {
    val m = parseInput()
    val answer1 = part1(m, Pair(3,1))
    println("R1: $answer1\n")

    val slopes = listOf(
        Pair(1,1),
        Pair(3,1),
        Pair(5,1),
        Pair(7,1),
        Pair(1,2)
    )

    val answer2 = part2(m, slopes)
    println("R2: $answer2\n")
}