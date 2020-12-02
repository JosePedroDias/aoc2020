import kotlinx.coroutines.*

// https://github.com/Kotlin/kotlinx.coroutines/blob/master/docs/basics.md#structured-concurrency

fun main() {
    doStuff()
}

fun doStuff() {
    println("before parallel stuff")
    val shared = mutableListOf<String>()
    runBlocking(Dispatchers.Default) {
        //val shared = ThreadLocal<MutableList<String>>()
        launch {
            delay(1000L)
            println("c1 1000!")
            shared.add("c1")
        }
        launch {
            delay(1500L)
            println("c2! 1500")
            shared.add("c2")
        }
        launch {
            delay(500L)
            println("c3 500!")
            shared.add("c3")
        }
        println("execution continues...")
    } // this block ends only when its coroutines end
    println("all is done")
    println(shared)
}