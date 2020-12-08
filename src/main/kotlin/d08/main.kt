package d08

import java.io.File
import java.lang.Exception

const val ACC = "acc"
const val JMP = "jmp"
const val NOP = "nop"

internal fun parseInput(): Sequence<String> {
    return File("aoc/08.txt").readLines().asSequence()
}

fun parseLines(lines:Sequence<String>): List<Pair<String, Int>> {
    val commands = mutableListOf<Pair<String, Int>>()
    lines.forEach {
        val parts = it.split(" ")
        commands.add(Pair(parts[0], parts[1].toInt()))
    }
    return commands
}

fun part1(commands:List<Pair<String, Int>>): Pair<Int,Boolean> {
    var acc = 0
    var lNo = 0
    val visitedLines = mutableSetOf<Int>()

    while (true) {
        if (visitedLines.contains(lNo)) {
            break
        }
        visitedLines.add(lNo)

        try {
            val (cmd, arg) = commands[lNo]
            //print("$cmd $arg | ")
            when (cmd) {
                ACC -> {
                    acc += arg
                }
                JMP -> {
                    lNo += arg -1
                }
                NOP -> {

                }
                else -> {
                    throw Error("Unsupported command: $cmd")
                }
            }
            lNo += 1
            //println("acc:$acc lNo:$lNo")
        } catch (ex:Exception) {
            return Pair(acc, false)
        }
    }

    return Pair(acc, true)
}

fun rewriteCommand(commands: List<Pair<String, Int>>, lineNo:Int): List<Pair<String, Int>> {
    return commands.mapIndexed{
        index, pair ->
            val (cmd, arg) = pair
            if (index == lineNo) {
                Pair(if (cmd == JMP) NOP else JMP, arg)
            } else {
                Pair(cmd, arg)
            }
    }
}

fun part2(commands: List<Pair<String, Int>>):Int? {
    for (i in commands.indices) {
        if (commands[i].first == ACC) {
            continue
        }
        val commands2 = rewriteCommand(commands, i)
        val res = part1(commands2)
        if (!res.second) {
            return res.first
        }
    }
    return null
}

fun main() {
    val m = parseLines( parseInput() )

    val answer1 = part1(m).first
    println("R1: $answer1\n")

    val answer2 = part2(m)
    println("R2: $answer2\n")
}