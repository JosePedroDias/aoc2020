import org.junit.Test
import org.junit.Assert.assertEquals

class Day01Test {
    @Test
    fun sample01() {
        assertEquals(
            514579, part1(
                listOf(
                    1721,
                    979,
                    366,
                    299,
                    675,
                    1456
                )
            )
        )
    }
}
