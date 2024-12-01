package day01

import kotlin.math.abs

class TotalDistance {
    fun execute(a: List<Int>, b: List<Int>) : Int {
        tailrec fun distanceHelper(a: List<Int>, b: List<Int>, pos: Int, acc: Int): Int {
            return if (pos == 0) {
                acc
            } else {
                distanceHelper(
                    a.subList(a.size - pos + 1, a.size),
                    b.subList(b.size - pos + 1, b.size),
                    pos - 1,
                    acc + abs(a.first() - b.first()))
            }
        }

        return distanceHelper(a.sorted(), b.sorted(), a.size, 0)
    }
}