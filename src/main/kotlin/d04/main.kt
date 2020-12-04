package d04

import java.io.File

data class Doc(private val fields: List<Pair<String,String>>) {
    var byr:String? = null
    var iyr:String? = null
    var eyr:String? = null
    var hgt:String? = null
    var hcl:String? = null
    var ecl:String? = null
    var pid:String? = null
    var cid:String? = null

    init {
        fields.forEach {
            val (key, value) = it
            when (key) {
                "byr" -> byr = value
                "iyr" -> iyr = value
                "eyr" -> eyr = value
                "hgt" -> hgt = value
                "hcl" -> hcl = value
                "ecl" -> ecl = value
                "pid" -> pid = value
                "cid" -> cid = value
                else -> throw Error("Found unsupported field: $key")
            }
        }
    }

    fun isValid():Boolean {
        return (byr != null && iyr != null &&
                eyr != null && hgt != null &&
                hcl != null && ecl != null &&
                pid != null)
    }
}

val FIELD_REGEX = Regex("""(\w+):([\w#]+)""")
val WHITESPACE_LINE_REGEX = Regex("""\s*""")

internal fun parseInput(): List<Doc> {
    var fields = mutableListOf<Pair<String,String>>()
    val docs = mutableListOf<Doc>()
    File("aoc/04.txt").forEachLine {
        val onlyWS = WHITESPACE_LINE_REGEX.matchEntire(it)
        if (onlyWS != null) {
            docs.add(Doc(fields))
            fields = mutableListOf()
        } else {
            val m = FIELD_REGEX.findAll(it)
            m.forEach { it1 ->
                if (it1.groups.size > 1) {
                    fields.add(Pair(it1.groups[1]!!.value,it1.groups[2]!!.value))
                }
            }
        }
    }
    docs.add(Doc(fields))
    return docs
}

fun part1(docs:List<Doc>):Int {
    return docs.count {
        it.isValid()
    }
}

fun part2(docs:List<Doc>):Int {
    return docs.size
}

fun main() {
    val m = parseInput()

    val answer1 = part1(m)
    println("R1: $answer1\n")

    val answer2 = part2(m)
    println("R2: $answer2\n")
}