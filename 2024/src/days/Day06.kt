package days

import utils.Point2D
import java.io.File

fun main() {
    val input = File("2024/src/days/input-day-06.txt").readLines()
    val day06 = Day06(input)
    println("Day 6 part 1: ${day06.solvePart1()}")
    println("Day 6 part 2: ${day06.solvePart2()}")
}


class Day06(input: List<String>) {
    private val grid = input.flatMapIndexed { y, row ->
        row.mapIndexed { x, char -> Point2D(x, y) to char }
    }.toMap().withDefault { ' ' }

    private val start = grid.entries.first { it.value == '^' }.key

    fun solvePart1() =
        traverse().path.size

    fun solvePart2() =
        traverse().path
            .filterNot { it == start }
            .count { obstacle -> traverse(newObstacle = obstacle).hasLoop }

    private fun traverse(newObstacle: Point2D? = null): TraverseResult {
        var current = start
        var dir = Point2D.UP

        val seen = mutableSetOf<Pair<Point2D, Point2D>>()

        while (current in grid && (current to dir) !in seen) {
            seen.add(current to dir)

            val next = current + dir
            if (grid.getValue(next) == '#' || next == newObstacle) {
                dir = dir.turnedRight()
            } else {
                current = next
            }
        }

        return TraverseResult(
            path = seen.map { it.first }.toSet(),
            hasLoop = current to dir in seen
        )
    }

    private data class TraverseResult(val path: Set<Point2D>, val hasLoop: Boolean)
}