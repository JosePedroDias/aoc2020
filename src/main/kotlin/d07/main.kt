package d07

import java.io.File

val COMMA_REGEX = Regex(""",""")
val SPACE_REGEX = Regex(""" """)

internal fun parseInput(): Sequence<String> {
    return File("aoc/07.txt").readLines().asSequence()
}

data class Rule(private val line:String) {
    val from:String
    val tos = mutableListOf<Pair<Int, String>>()

    init {
        val m = SPACE_REGEX.split(line)
        val l = m.size
        from = "${m[0]} ${m[1]}"
        // println("${fromName}")
        if (l > 7) {
            var i = 4
            do {
                val toNum = m[i].toInt()
                val toName = "${m[i + 1]} ${m[i + 2]}"
                //println("-> #$toNum $toName")
                tos.add(Pair(toNum, toName))
                i += 4
            } while (i < l)
        }
    }

    override fun toString(): String {
        //return "Rule{from:$from tos:#${tos.size}}"
        return "Rule{from:$from tos:${tos.map { "${it.first} ${it.second}" }}"
    }
}

fun parseRule(line:String):Rule {
    return Rule(line)
}

fun numCommas(line:String):Int {
    val m = COMMA_REGEX.findAll(line)
    return m.count()
}

fun parseLines(lines:Sequence<String>): HashMap<String, MutableList<Rule>> {
    val map = hashMapOf<String, MutableList<Rule>>()
    lines.forEach {
        /*val nc = numCommas(it)
        if (nc > 3) {
            println(nc)
        }*/
        // 0-3 commas => 1-4 kinds in the to

        val rule = parseRule(it)
        rule.tos.forEach {
            val toName = it.second
            val bag = map.get(toName)
            if (bag == null) {
                map.set(toName, mutableListOf(rule))
            } else {
                bag.add(rule)
            }
        }
    }
    return map
}

fun getABagOf(m:HashMap<String, MutableList<Rule>>, visitedKinds: MutableSet<String>, kind:String):Int {
    val rulesToKind = m.get(kind)
    if (rulesToKind != null) {
        rulesToKind.forEach {
            val kind2 = it.from
            println("-> $kind2")
            if (visitedKinds.contains(kind2)) {
                println("Ignore")
            } else {
                visitedKinds.add(kind2)
                getABagOf(m, visitedKinds, kind2)
            }
        }
    }
    println(visitedKinds)
    return visitedKinds.size
}

fun part1(m:HashMap<String, MutableList<Rule>>): Int {
    val visitedKinds = mutableSetOf<String>()
    return getABagOf(m, visitedKinds, "shiny gold")
}

fun part2(m:HashMap<String, MutableList<Rule>>):Int {
    val visitedKinds = mutableSetOf<String>()
    return getABagOf(m, visitedKinds, "shiny gold")
}

fun main() {
    val m = parseLines( parseInput() )

    val answer1 = part1(m)
    println("R1: $answer1\n")

    val answer2 = part2(m)
    println("R2: $answer2\n")
}