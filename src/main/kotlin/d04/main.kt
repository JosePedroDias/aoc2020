package d04

import java.io.File

val MEASURE_REGEX = Regex("""(\d+)(in|cm)""")
val COLOR_REGEX = Regex("""#[0-9a-f]{6}""")
val VALID_EYE_COLORS = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
val PID_REGEX = Regex("""(\d){9}""")

const val MEASURE_UNIT_CM = "cm"
const val MEASURE_UNIT_IN = "in"

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

    override fun toString(): String {
        return "Doc{byr:$byr iyr:$iyr eyr:$eyr hgt:$hgt hcl:$hcl ecl:$ecl pid:$pid cid:$cid valid?${isValid()}}"
    }
}

data class ValidatedDoc(private val fields: List<Pair<String,String>>) {
    var byr:Int? = null
    var iyr:Int? = null
    var eyr:Int? = null
    var hgt:Pair<Int,String>? = null
    var hcl:String? = null
    var ecl:String? = null
    var pid:String? = null
    var cid:String? = null

    init {
        fields.forEach {
            val (key, value) = it
            when (key) {
                "byr" -> {
                    val v = value.toInt()
                    if (v in 1920..2002) {
                        byr = v
                    }
                }
                "iyr" -> {
                    val v = value.toInt()
                    if (v in 2010..2020) {
                        iyr = v
                    }
                }
                "eyr" -> {
                    val v = value.toInt()
                    if (v in 2020..2030) {
                        eyr = v
                    }
                }
                "hgt" -> {
                    val m = MEASURE_REGEX.find(value)
                    if (m != null && m.groupValues.size > 2) {
                        val measure = m.groupValues[1].toInt()
                        when (val unit = m.groupValues[2]) {
                            MEASURE_UNIT_CM -> if (measure in 150..193) {hgt = Pair(measure, unit) }
                            MEASURE_UNIT_IN -> if (measure in 59..76) {hgt = Pair(measure, unit) }
                            else -> throw Error("Found unsupported unit in field height: $unit")
                        }
                    }
                }
                "hcl" -> if (COLOR_REGEX.matchEntire(value) != null) { hcl = value }
                "ecl" -> if (VALID_EYE_COLORS.contains(value)) { ecl = value }
                "pid" -> if (PID_REGEX.matchEntire(value) != null) { pid = value }
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

    override fun toString(): String {
        return "ValidatedDoc{byr:$byr iyr:$iyr eyr:$eyr hgt:$hgt hcl:$hcl ecl:$ecl pid:$pid cid:$cid valid?${isValid()}}"
    }
}

val FIELD_REGEX = Regex("""(\w+):([\w#]+)""")
val WHITESPACE_LINE_REGEX = Regex("""\s*""")

fun parseLines(lines:List<String>): List<List<Pair<String,String>>> {
    val protoDocs = mutableListOf<List<Pair<String,String>>>()
    var fields = mutableListOf<Pair<String,String>>()
    lines.forEach {
        val onlyWS = WHITESPACE_LINE_REGEX.matchEntire(it)
        if (onlyWS != null) {
            protoDocs.add(fields)
            fields = mutableListOf<Pair<String,String>>()
        } else {
            val m = FIELD_REGEX.findAll(it)
            m.forEach { it1 ->
                if (it1.groups.size > 1) {
                    fields.add(Pair(it1.groups[1]!!.value,it1.groups[2]!!.value))
                }
            }
        }
    }
    protoDocs.add(fields)
    return protoDocs
}

internal fun parseInput(): List<List<Pair<String,String>>> {
    val lines = File("aoc/04.txt").readLines()
    return parseLines(lines)
}

fun part1(protoDocs:List<List<Pair<String,String>>>):Int {
    val docs = protoDocs.map {
        Doc(it)
    }
    return docs.count {
        // println(it)
        it.isValid()
    }
}

fun part2(protoDocs:List<List<Pair<String,String>>>):Int {
    val docs = protoDocs.map {
        ValidatedDoc(it)
    }
    return docs.count {
        // println(it)
        it.isValid()
    }
}

fun main() {
    val m = parseInput()
    println("#: ${m.size}\n")

    val answer1 = part1(m)
    println("R1: $answer1\n")

    val answer2 = part2(m)
    println("R2: $answer2\n")
}