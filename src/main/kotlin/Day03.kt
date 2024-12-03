fun main() {
    println(calculateMul(readInput("day03")))
    println(calculateMulConditional(readInput("day03")))
}

fun calculateMul(input: List<String>): Long {
    val mulRegex = "mul\\([0-9]{1,3},[0-9]{1,3}\\)".toRegex()
    return input.flatMap { str ->
        mulRegex.findAll(str)
            .map {
                val (a, b) = it.value
                    .removeSurrounding("mul(", ")")
                    .split(",")
                    .map { i -> i.toInt() }
                a * b
            }
    }.fold(0L) { acc, i -> acc + i }
}

sealed interface Instruction

data object Do : Instruction

data object Dont : Instruction

data class Mul(val a: Int, val b: Int) : Instruction

fun getInstruction(instruction: String): Instruction {
    return when {
        instruction == "do()" -> Do
        instruction == "don't()" -> Dont
        instruction.startsWith("mul(") -> {
            val (a, b) = instruction.removeSurrounding("mul(", ")").split(",").map { it.toInt() }
            Mul(a, b)
        }
        else -> error("unknown instruction")
    }
}

fun calculateMulConditional(input: List<String>): Long {
    val mulRegex = "mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don't\\(\\)".toRegex()

    val instructions = input.flatMap { str -> mulRegex.findAll(str).map { getInstruction(it.value) } }
    var enabled = true
    var acc = 0L

    instructions.forEach { instruction ->
        when(instruction) {
            is Do -> enabled = true
            is Dont -> enabled = false
            is Mul -> if (enabled) acc += (instruction.a * instruction.b)
        }
    }

    return acc
}