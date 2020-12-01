import org.junit.Test
import org.junit.Assert.assertEquals

class Day01Test {
    private var d: Day01 = Day01()

    @Test
    fun sample1() {
        assertEquals(
            514579, d.part1(
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
