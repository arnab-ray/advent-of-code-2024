package day01

class SimilarityScore {
    fun execute(a: List<Int>, b: List<Int>) : Int {
        val occurrenceCounter = b.groupBy { it }.map { (k, v) -> k to v.size }.toMap()
        return a.fold(0) { total, next -> total + next * (occurrenceCounter[next] ?: 0) }
    }
}