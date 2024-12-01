package day01

import java.io.File

fun main() {
    process()
}

fun process() {
    val a = mutableListOf<Int>()
    val b = mutableListOf<Int>()

    File("/Users/arnabray/Personal_Workspace/advent-of-code-2024/src/main/resources/day01.txt").forEachLine { line ->
        val (first, second) = line.split("\\s+".toRegex()).map { it.toInt() }
        a.addLast(first)
        b.addLast(second)
    }

    println("total distance: " + TotalDistance().execute(a, b))
    println("similarity score: " + SimilarityScore().execute(a, b))
}