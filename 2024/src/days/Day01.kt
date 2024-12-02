package days

import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val path = "2024/src/days/input-day-01.txt"
    val file = Files.readAllLines(Paths.get(path))
    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()

    // Populate the lists
    for (line in file) {
        val split = line.split("   ")
        list1.add(split[0].toInt())
        list2.add(split[1].toInt())
    }

    // Sort the lists
    list1.sort()
    list2.sort()

    // Part 1
    val result1 = list1.indices.sumOf { kotlin.math.abs(list1[it] - list2[it]) }
    println("Result for part 1: $result1")

    // Part 2
    val occurrences = list2.groupingBy { it }.eachCount()
    val result2 = list1.sumOf { occurrences[it]?.times(it) ?: 0 }
    println("Result for part 2: $result2")
}