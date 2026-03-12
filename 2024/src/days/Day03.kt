package days

import java.io.File
import java.util.regex.Matcher
import java.util.regex.Pattern

fun main() {
    val inputFilePath = "2024/src/days/input-day-03.txt"
    val file = File(inputFilePath).readLines()
    //println(file.joinToString("\n"))

    partOne(file)
    println(multiplyAndSum(file.toString()))

}

fun partOne(file: List<String>): Int {
    val regex = Regex("""mul\((\d+),(\d+)\)""")
    // Find all matches
    val matches = regex.findAll(file.toString())
    for (match in matches) {
        val intX = match.groupValues[1] // Extract the first number
        val intY = match.groupValues[2] // Extract the second number
        println("Found mul with intX=$intX and intY=$intY")
    }
    //summ up all the numbers
    var sum = 0
    for (match in matches) {
        val intX = match.groupValues[1].toInt() // Extract the first number
        val intY = match.groupValues[2].toInt() // Extract the second number
        sum += intX * intY
    }
    println("Sum of all numbers: $sum")
    return sum
}

private fun multiplyAndSum(value: String): Int {
    val pattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)")
    val matcher = pattern.matcher(value)

    var sum = 0
    var enabled = true
    while (matcher.find()) {
        val foundMatch: String = matcher.group()

        if (foundMatch == "do()") {
            enabled = true
        } else if (foundMatch == "don't()") {
            enabled = false
        }

        if (enabled && foundMatch.startsWith("mul")) {
            // Replaces everything that is not a digit or a comma
            val numbers =
                foundMatch.replace("[^\\d+,]".toRegex(), "").split(",".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            val num1 = numbers[0].toInt()
            val num2 = numbers[1].toInt()
            sum += num1 * num2
        }
    }
    return sum
}