package common

fun <A>perm2(lst:List<A>):Sequence<Pair<A, A>> {
    val n = lst.size
    return sequence {
        for (i in 0 until n) {
            for (j in 0 until i) {
                yield(Pair(lst[i], lst[j]))
            }
        }
    }
}

fun <A>perm3(lst:List<A>):Sequence<Triple<A, A, A>> {
    val n = lst.size
    return sequence {
        for (i in 0 until n) {
            for (j in 0 until i) {
                for (k in 0 until j) {
                    yield(Triple(lst[i], lst[j], lst[k]))
                }
            }
        }
    }
}
