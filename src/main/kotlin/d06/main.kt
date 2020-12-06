package d06

import java.io.File

val WHITESPACE_LINE_REGEX = Regex("""\s*""")

internal fun parseInput(): Sequence<String> {
    return File("aoc/06.txt").readLines().asSequence()
}

fun parseLines(lines:Sequence<String>):List<List<Set<Char>>> {
    val allAnswers = mutableListOf<List<Set<Char>>>()
    var answersOfGroup = mutableListOf<Set<Char>>()
    lines.forEach {
        val onlyWS = WHITESPACE_LINE_REGEX.matchEntire(it)
        if (onlyWS != null) {
            allAnswers.add(answersOfGroup)
            answersOfGroup = mutableListOf<Set<Char>>()
        } else {
            val answersOfOne = mutableSetOf<Char>()
            it.forEach { ch ->
                answersOfOne.add(ch)
            }
            answersOfGroup.add(answersOfOne)
        }
    }
    allAnswers.add(answersOfGroup)
    return allAnswers
}

fun part1(feedback:List<List<Set<Char>>>): Int {
    val processGroups = feedback.map {
        it.fold(setOf<Char>()) {
            acc, set -> acc.union(set)
        }
    }
    return processGroups.sumBy { it.size }
}

fun part2(feedback:List<List<Set<Char>>>):Int {
    val processGroups = feedback.map {
        it.fold(setOf<Char>('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')) {
                acc, set -> acc.intersect(set)
        }
    }
    return processGroups.sumBy { it.size }
}

fun main() {
    val m = parseLines( parseInput() )

    val answer1 = part1(m)
    println("R1: $answer1\n")

    val answer2 = part2(m)
    println("R2: $answer2\n")
}