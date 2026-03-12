package days

import java.io.File

fun main() {
    // Read input from the file
    val input = File("2024/src/days/input-day-05.txt").readText()
    val sections = input.split("\n\n")
    val ruleSection = sections[0].lines()
    val pageSection = sections[1].lines()

    // Parse rules into a map
    val map = mutableMapOf<Int, MutableSet<Int>>()
    for (rule in ruleSection) {
        val (from, to) = rule.split("|").map { it.toInt() }
        map.computeIfAbsent(from) { mutableSetOf() }.add(to)
    }

    // Calculate part one and part two results
    var partOneSum = 0
    var partTwoSum = 0
    for (pageString in pageSection) {
        val pages = pageString.split(",").map { it.toInt() }.toMutableList()
        partOneSum += partOneMiddle(map, pages)
        partTwoSum += partTwoMiddle(map, pages)
    }

    // Print the results
    println("Day 5 part 1: $partOneSum")
    println("Day 5 part 2: $partTwoSum")
}

// Function to check if the pages sequence is valid
fun valid(map: Map<Int, Set<Int>>, pages: List<Int>): Boolean {
    for (i in 0 until pages.size - 1) {
        if (map[pages[i]]?.contains(pages[i + 1]) != true) {
            return false
        }
    }
    return true
}

// Part one logic
fun partOneMiddle(map: Map<Int, Set<Int>>, pages: List<Int>): Int {
    return if (valid(map, pages)) {
        pages[pages.size / 2]
    } else {
        0
    }
}

// Part two logic
fun partTwoMiddle(map: Map<Int, Set<Int>>, pages: MutableList<Int>): Int {
    if (valid(map, pages)) {
        return 0
    }
    pages.sortWith(PageComparator(map))
    return pages[pages.size / 2]
}

// Custom comparator for sorting pages
class PageComparator(private val map: Map<Int, Set<Int>>) : Comparator<Int> {
    override fun compare(i1: Int, i2: Int): Int {
        return if (map[i1]?.contains(i2) == true) 1 else -1
    }
}