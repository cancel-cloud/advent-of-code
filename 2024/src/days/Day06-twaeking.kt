package days

import java.io.File

fun main() {
    val input = File("2024/src/days/input-day-06.txt").readLines()
    //println(input[0])
    //println("Length: ${input.size}")
    var currentline = 0
    val guard = findGuard(input)
    println("days.Guard is at line $guard")
    println(input[guard])
    val rest = input[guard].substring(guard)
    println("-----------------------------")
    println(rest)
    println("Find first hash:")
    val firstHashIndex = rest.indexOfFirst { it == '#' }

    println("rest length: ${rest.length}")
    println("found hash at $firstHashIndex")

    /*
    println("days.Guard is at line $guard")
    val rest = moveGuard(fetchSpecificLine(input, guard), guard)
    println(rest.toString())
     */

}

fun fetchSpecificLine(input: List<String>, line: Int): String {
    return input[line]
}

fun findGuard(input: List<String>): Int {
    for ((current, line) in input.withIndex()) {
        if (line.contains("^")) {
            return current
        }
    }
    return -1
}

fun moveGuard(input: String, guard: Int): String {
    val newInput = input.toMutableList()
    val rest = newInput.subList(guard, newInput.size)
    val possibleMoves = findFirstHash(rest.toCharArray(), guard)
    if (possibleMoves != -1) {
        println("Possible moves: $possibleMoves")
        newInput[guard] = '.'
    }
    return rest.toString()
}

fun findFirstHash(array: CharArray, startingPoint: Int = 0): Int {
    for (i in startingPoint..<array.size) {
        if (array[i] == '#') {
            return i
        }
    }
    return -1
}
