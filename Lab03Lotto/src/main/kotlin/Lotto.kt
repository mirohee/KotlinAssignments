class Lotto {
    val lottoRange = 1..40
    val n = 7
    val secretNumbers = pickNDistinct(lottoRange, n) ?: throw IllegalStateException("Failed to generate secret numbers")

    fun pickNDistinct(range: IntRange, n: Int): List<Int>? {
        if (n > range.count()) return null
        return range.shuffled().take(n)
    }

    fun numDistinct(list: List<Int>): Int {
        return list.toSet().size
    }

    fun numCommon(list1: List<Int>, list2: List<Int>): Int {
        val set = list1.toSet()
        return list2.count { it in set }
    }

    fun isLegalLottoGuess(guess: List<Int>, range: IntRange = lottoRange, count: Int = n): Boolean {
        if (guess.size != count) return false
        return guess.all { it in range } && guess.distinct().size == count
    }

    fun checkGuess(guess: List<Int>, secret: List<Int> = secretNumbers): Int {
        return if (isLegalLottoGuess(guess)) numCommon(guess, secret) else 0
    }

    fun readNDistinct(low: Int, high: Int, n: Int): List<Int> {
        while (true) {
            println("Guess $n numbers between 1-40, separated by commas: ")
            val input = readLine()

            val numbers = input?.split(",")?.map { it.trim().toIntOrNull() }?.filterNotNull()

            if (numbers != null && numDistinct(numbers) == n && numbers.all { it in low..high }) {
                return numbers
            } else {
                println("Invalid input. Please enter $n numbers between $low and $high, separated by commas: ")
            }
        }

    }

    fun playLotto() {
        do {
            val guess = readNDistinct(1, 40, n)
            val correct = checkGuess(guess)
            println("You got $correct numbers correct!")
            println("Lotto numbers: $guess, you got $correct correct")
            println("Continue? Y/N: ")
        } while (readLine()?.uppercase() == "Y")
    }

    fun generateNextGuess(currentGuess: List<Int>, range: IntRange, n: Int): List<Int> {
        val newGuess = currentGuess.toMutableList()
        val randomIndex = (0 until n).random()
        val newNumber = (range - newGuess).random()
        newGuess[randomIndex] = newNumber
        return newGuess
    }

    fun findLotto(): Pair<Int, List<Int>> {
        var attempts = 0
        val correctCount = n
        var bestGuess = pickNDistinct(lottoRange, n) ?: throw IllegalStateException("Failed to generate initial guess")
        var bestCorrectCount = checkGuess(bestGuess)

        do {
            val guess = generateNextGuess(bestGuess, lottoRange, n)
            val correct = checkGuess(guess)
            attempts++

            if (correct > bestCorrectCount) {
                bestGuess = guess
                bestCorrectCount = correct
            }
        } while (bestCorrectCount < correctCount)

        return Pair(attempts, bestGuess)
    }

}

fun main() {
    val lotto = Lotto()
    lotto.playLotto()

    val (steps, correctNumbers) = lotto.findLotto()
    println("Steps: $steps")
    println("Correct numbers: $correctNumbers")
}




