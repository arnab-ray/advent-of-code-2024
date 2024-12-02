package day02

import kotlin.math.abs
import kotlin.math.sign

class SafetyCount {
    fun count(reports: List<List<Int>>): Int {
        return reports.filter { it.isSafe() }.size
    }

    fun countWithDampener(reports: List<List<Int>>): Int {
        return reports.filter { it.partialSafe() }.size
    }

    private fun Int.isSafe(sign: Int) = abs(this) in 1..3 && sign == this.sign

    private fun List<Int>.isSafe() : Boolean {
        val sign = (this[1] - this[0]).sign
        return this
            .zipWithNext { a, b ->  b - a }
            .mapIndexedNotNull { index, element ->
                if (!element.isSafe(sign)) index else null
            }.isEmpty()
    }

    private fun List<Int>.partialSafe() : Boolean {
        val diffs = this.zipWithNext { a, b ->  b - a }
        val signFrequency = diffs.groupingBy { it.sign }.eachCount()
        val sign = if (signFrequency.getOrDefault(1, 0) > signFrequency.getOrDefault(-1, 0)) 1 else -1
        val candidates = diffs.mapIndexedNotNull { index, element ->
            if (!element.isSafe(sign)) index else null
        }

        return when (candidates.count()) {
            0 -> true
            1 -> {
                val i = candidates.single()
                i == 0 || i == diffs.lastIndex ||
                        (diffs[i] + diffs[i - 1]).isSafe(sign) ||
                        (diffs[i] + diffs[i + 1]).isSafe(sign)
            }
            2 -> {
                val i = candidates.first()
                val j = candidates.last()
                j == i + 1 && (diffs[i] + diffs[j]).isSafe(sign)
            }
            else -> false
        }
    }

}