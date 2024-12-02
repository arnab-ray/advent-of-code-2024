package day02

import java.io.File

fun main() {
    val input: List<List<Int>> = listOf(
        listOf(7,  6,  4,  2,  1),
        listOf(1,  2,  7,  8,  9),
        listOf(9,  7,  6,  2,  1),
        listOf(1,  3,  2,  4,  5),
        listOf(8,  6,  4,  4,  1),
        listOf(1,  3,  6,  7,  9),
        listOf(72, 75, 78, 79, 79),
        listOf(21, 21, 25, 26, 29, 30, 32, 36)
    )

    val reports = mutableListOf<List<Int>>()
    File("/Users/arnabray/Personal_Workspace/advent-of-code-2024/src/main/resources/day02.txt").forEachLine { line ->
        val report = line.split("\\s+".toRegex()).map { it.toInt() }
        reports.add(report)
    }

    val count = SafetyCount().count(reports)
    println("safety count: $count")
    check(count == 341)

    val countWithDampen = SafetyCount().countWithDampener(reports)
    println("safety count with dampener: $countWithDampen")
    check(countWithDampen == 404)
}