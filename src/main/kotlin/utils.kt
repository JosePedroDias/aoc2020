fun <A>combine2(lst:List<A>, withReps:Boolean):List<Pair<A, A>> {
    val res = mutableListOf<Pair<A,A>>()
    val n = lst.size
    for (i in 0 until n) {
        for (j in 0 until n) {
            val addPair = (!withReps && i < j) || (withReps && i !== j);
            if (addPair) {
                res.add(Pair(lst[i], lst[j]))
            }
        }
    }
    return res
}

fun <A>combine3(lst:List<A>, withReps:Boolean):List<Triple<A, A, A>> {
    val res = mutableListOf<Triple<A,A,A>>()
    val n = lst.size
    for (i in 0 until n) {
        for (j in 0 until n) {
            for (k in 0 until n) {
                val addTriple = (!withReps && i < j && j < k) || (withReps && i !== j && j !== k);
                if (addTriple) {
                    res.add(Triple(lst[i], lst[j], lst[k]))
                }
            }
        }
    }
    return res
}