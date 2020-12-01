# advent of code 2020


## resources

### kotlin

- [collections](https://kotlinlang.org/docs/reference/collection-operations.html)
- [parallel execution w/ coroutines](https://jivimberg.io/blog/2018/05/04/parallel-map-in-kotlin/)
- [test time taken](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.system/measure-time-millis.html)
- [JVM memory params](https://www.baeldung.com/jvm-parameters)
- [memory usage](https://www.baeldung.com/java-heap-memory-api)
- [basic coroutines](https://kotlinlang.org/docs/tutorials/coroutines/coroutines-basic-jvm.html)

#### notes

```kotlin
    // memory used
    val memoryMXBean = ManagementFactory.getMemoryMXBean()
    println("mem: ${memoryMXBean.heapMemoryUsage.used}")

    // time taken
    val ms = measureTimeMillis {
        // do stuff here
    }
    println("took ${ms} ms")
```

### gradle

- [groovy to kotlin](https://docs.gradle.org/nightly/userguide/migrating_from_groovy_to_kotlin_dsl.html)
- [test logging opts](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.testing.logging.TestLogging.html)