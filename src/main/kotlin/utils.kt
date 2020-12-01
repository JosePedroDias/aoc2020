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