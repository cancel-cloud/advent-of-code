package days

import java.io.File

fun main() {
    // Path to the input file
    val inputFilePath = "2024/src/days/input-day-02.txt"

    // Read the reports from the input file
    val reports = File(inputFilePath).readLines()

    // Part One: Count safe reports under the original rules
    val safeReportsCountPartOne = reports.count { isReportSafe(it) }
    println("Part One - Number of safe reports: $safeReportsCountPartOne")

    // Part Two: Count safe reports under the Problem Dampener rules
    val safeReportsCountPartTwo = reports.count { isReportSafeWithDampener(it) }
    println("Part Two - Number of safe reports: $safeReportsCountPartTwo")
}

// Part One: Check if a report is safe under the original rules
fun isReportSafe(report: String): Boolean {
    val levels = report.split(" ").map { it.toInt() }
    if (levels.size < 2) return false

    val differences = levels.zipWithNext { a, b -> b - a }

    val isIncreasing = differences.all { it in 1..3 }
    val isDecreasing = differences.all { it in -3..-1 }

    return isIncreasing || isDecreasing
}

// Part Two: Check if a report is safe with the Problem Dampener
fun isReportSafeWithDampener(report: String): Boolean {
    val levels = report.split(" ").map { it.toInt() }
    if (levels.size < 2) return false

    // Check if the report is already safe
    if (isReportSafe(report)) return true

    // Try removing each level and check if the modified report is safe
    for (i in levels.indices) {
        val modifiedLevels = levels.filterIndexed { index, _ -> index != i }
        if (isReportSafe(modifiedLevels.joinToString(" "))) {
            return true
        }
    }
    return false
}