import kotlinx.coroutines.*
import kotlin.system.*

fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        //val one = async { doSomethingUseful(15, 1500L) }
        //val two = async { doSomethingUseful(130, 3500L) }
        // println("The answer is ${one.await() + two.await()}")

        val tasks = mutableListOf<Deferred<Int>>()
        for(i in 1..100) {
            val task = async { doSomethingUseful(i, 500L) }
            tasks.add(task)
        }

        val results = tasks.awaitAll()
        println(results)
    }
    println("Completed in $time ms")
}

suspend fun doSomethingUseful(v:Int, ms:Long): Int {
    delay(ms)
    return v
}
