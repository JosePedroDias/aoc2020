package d13

import java.io.File
import java.lang.Long.min

/*
https://en.wikipedia.org/wiki/Chinese_remainder_theorem
In number theory, two integers a and b are relatively prime, mutually prime,
or coprime if the only positive integer that evenly divides both of them is 1.
One says also a is prime to b or a is coprime with b.
Consequently, any prime number that divides one of a or b does not divide the other.
This is equivalent to their greatest common divisor (gcd) being 1.
*/

internal fun parseInput(): List<String> {
    return File("aoc/13.txt").readLines()
}

fun parseLines(lines:List<String>):Pair<Long, List<Long>> {
    val ti = lines[0].toLong()
    val ids = lines[1]
        .split(',')
        .filter { it != "x" }
        .map { it.toLong() }
    return Pair(ti, ids)
}

fun distFromMod0(num:Long, m:Long):Pair<Long,Long> {
    val i = num / m
    val a = i * m
    val b = (i+1) * m
    if (a == num) { return Pair(0, a) }
    return Pair(b - num, b)
}

fun part1(pair:Pair<Long, List<Long>>): Long {
    val (ti, ids) = pair
    println(" -- $ti -- ")
    var res = 0L
    var betterV =Long.MAX_VALUE
    ids.forEach {
        val (v, n) = distFromMod0(ti, it)
        if (v < betterV) {
            betterV = v
            res = it
        }
        println("$it ~> $v")
    }
    return res
}

fun part2():Int {
    return 0
}

fun main() {
    val m = parseLines( parseInput() )

    val answer1 = part1(m)
    println("R1: $answer1\n")

    // val answer2 = part2(m)
    // println("R2: $answer2\n")
}