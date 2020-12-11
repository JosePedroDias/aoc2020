package d11

import java.io.File

// const val FLOOR = '.'
const val EMPTY = 'L'
const val OCCUPIED = '#'

val DIRS = listOf(
    Pair(-1, -1),
    Pair(-1, 0),
    Pair(-1, 1),
    Pair(0, -1),
    Pair(0, 1),
    Pair(1, -1),
    Pair(1, 0),
    Pair(1, 1),
)

data class Matrix(private val lines_: List<String>) {
    private val lines = mutableListOf<String>()

    init {
        lines.addAll(lines_)
    }

    val width: Int = lines[0].length

    val height: Int = lines.size

    fun getCell(x:Int, y:Int):Char {
        if (x !in 0 until width || y !in 0 until height) {
            return '.'
        }
        return lines[y][x]
    }

    fun setCell(x:Int, y:Int, c:Char) {
        val l = lines[y]
        val l2 = "${l.substring(0 until x)}$c${l.substring(x+1 until width)}"
        lines[y] = l2
    }

    fun neighborsOfKind(x:Int, y:Int, c:Char):Int {
        return listOf(
            getCell(x-1, y-1),
            getCell(x-1, y),
            getCell(x-1, y+1),
            getCell(x, y-1),
            getCell(x, y+1),
            getCell(x+1, y-1),
            getCell(x+1, y),
            getCell(x+1, y+1),
        ).count { it == c }
    }

    fun neighborsOfKind2(x:Int, y:Int, c:Char):Int {
        var found = 0
        for (d in DIRS) {
            var p = Pair(x + d.first, y + d.second)
            var dirFound = false
            do {
                val v = getCell(p.first, p.second)
                if (v == c) {
                    ++found
                    dirFound = true
                }
                p = Pair(p.first + d.first, p.second + d.second)
            } while (!dirFound && p.first in 0 until width && p.second in 0 until height)
        }
        return found
    }

    fun getCells():Sequence<Char> {
        return sequence {
            for (x in 0 until width) {
                for (y in 0 until height) {
                    yield(getCell(x,y))
                }
            }
        }
    }

    fun ofKind(c:Char):Int {
        return getCells().filter { it == c }.count()
    }

    fun getPositions():Sequence<Pair<Int, Int>> {
        return sequence {
            for (x in 0 until width) {
                for (y in 0 until height) {
                    yield(Pair(x, y))
                }
            }
        }
    }

    fun getCopy():Matrix {
        val lines2 = mutableListOf<String>()
        lines2.addAll(lines)
        return Matrix(lines2)
    }

    override fun toString():String {
       return lines.joinToString("\n")
    }

    override fun equals(other: Any?):Boolean {
        if (other is Matrix) {
            if (width != other.width || height != other.height) { return false }
            return this.toString() == other.toString()
        }
        return false
    }

    override fun hashCode(): Int {
        return lines.hashCode()
    }
}

internal fun parseInput(): List<String> {
    return File("aoc/11.txt").readLines()
}

fun parseLines(lines:List<String>):Matrix {
    return Matrix(lines)
}

fun step(m:Matrix):Matrix {
    val m2 = m.getCopy()

    m.getPositions().forEach {
        (x, y) ->
        when (m.getCell(x, y)) {
            EMPTY -> {
                if (m.neighborsOfKind(x, y, OCCUPIED) == 0) {
                    m2.setCell(x, y, OCCUPIED)
                }
            }
            OCCUPIED -> {
                if (m.neighborsOfKind(x, y, OCCUPIED) > 3) {
                    m2.setCell(x, y, EMPTY)
                }
            }
            else -> Unit
        }
    }

    return m2
}

fun step2(m:Matrix):Matrix {
    val m2 = m.getCopy()

    m.getPositions().forEach {
            (x, y) ->
        when (m.getCell(x, y)) {
            EMPTY -> {
                if (m.neighborsOfKind2(x, y, OCCUPIED) == 0) {
                    m2.setCell(x, y, OCCUPIED)
                }
            }
            OCCUPIED -> {
                if (m.neighborsOfKind2(x, y, OCCUPIED) > 4) {
                    m2.setCell(x, y, EMPTY)
                }
            }
            else -> Unit
        }
    }

    return m2
}

fun part1(m_:Matrix): Int {
    var mPrev = Matrix("  \n  ".split("\n"))
    var m = m_

    while (m != mPrev) {
        mPrev = m
        m = step(m)
    }

    return m.ofKind(OCCUPIED)
}

fun part2(m_:Matrix):Int {
    var mPrev = Matrix("  \n  ".split("\n"))
    var m = m_

    while (m != mPrev) {
        mPrev = m
        m = step2(m)
    }

    return m.ofKind(OCCUPIED)
}

fun main() {
    val m = parseLines( parseInput() )

    val answer1 = part1(m)
    println("R1: $answer1\n")

    val answer2 = part2(m)
    println("R2: $answer2\n")
}