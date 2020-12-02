package d02

/*
1-3 b: cdefg
*/

import java.io.File

data class PwReq(
    val minTimes:Int,
    val maxTimes:Int,
    val letter:Char,
    val password:String
)

val EXPR_REGEX = Regex("""(\d+)-(\d+) (\S): (\S+)""")

internal fun parseInput(): List<PwReq> {
    val lst = mutableListOf<PwReq>()
    File("aoc/02.txt").forEachLine {
        val m = EXPR_REGEX.find(it)
        if (m != null) {
            // println(m.groupValues)
            lst.add(PwReq(
                m.groupValues[1].toInt(),
                m.groupValues[2].toInt(),
                m.groupValues[3][0],
                m.groupValues[4],
            ))
            // println(lst.last())
        }
    }
    return lst
}

fun isPwValid(input:PwReq):Boolean {
    val (m, M, letter, pw) = input
    var count = 0
    for (i in pw.indices) {
        val c = pw[i]
        if (c == letter) {
            ++count
        }
    }
    return count in m..M
}

fun isPwValid2(input:PwReq):Boolean {
    val (m, M, letter, pw) = input
    var count = 0

    if (pw[m-1] == letter) ++count
    if (pw[M-1] == letter) ++count

    return count == 1
}

fun part1(lst:List<PwReq>):Int {
    return lst.count {
        isPwValid(it)
    }
}

fun part2(lst:List<PwReq>):Int {
    return lst.count {
        isPwValid2(it)
    }
}

fun main() {
    val lst = parseInput()

    val answer1 = part1(lst)
    println("R1: $answer1\n")

    val answer2 = part2(lst)
    println("R2: $answer2\n")
}