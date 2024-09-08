package zadam

class Lotto(val lottoRange: IntRange = 1..40, val n: Int = 7) {
    private val secretNumbers = pickNDistinct(lottoRange, n) ?: listOf()

    fun pickNumber(low: Int = 1, high: Int = 40) = (low..high).random()
    fun pickNDistinct(range: IntRange, n: Int): List<Int>? =
        if(range.count() < n) null else range.shuffled().take(n).sorted()
    fun numDistinct(list: List<Int>) = list.toSet().size
    fun numCommon(list1: List<Int>, list2: List<Int>) = list1.intersect(list2).size
    fun checkGuess(guess: List<Int>, secret: List<Int> = secretNumbers): Int =
        if (isLegalLottoGuess(guess)) numCommon(guess, secretNumbers) else 0
    fun isLegalLottoGuess(guess: List<Int>, range: IntRange = lottoRange, count: Int = n) : Boolean =
        numDistinct(guess) == count  && guess.all { it in range }
}

fun readNumberList(): List<Int>? {
    val input = readlnOrNull()?.split(",")?.map { it.toIntOrNull() }?.filterNotNull()
    return input
}

fun readLottoGuess(game: Lotto): List<Int> {
    do {
        print("Give ${game.n} numbers from ${game.lottoRange.first} to ${game.lottoRange.last}, separated by commas: ")
        val guess = readNumberList()
        if (guess != null) {
            if (game.isLegalLottoGuess(guess)) {
                return guess
            }
        }
    } while (true)
}

fun playLotto() {
    do {
        val lotto = Lotto()
        val numbers = readLottoGuess(lotto)
        println("lotto numbers: ${numbers.sorted()}, you got ${lotto.checkGuess(numbers)} correct")

        val (steps, computerGuess) = findLotto(lotto)
        println("computer guess in $steps steps is $computerGuess")

        print("More? (Y/N): ")
        val more = readLine() == "Y"
    } while(more)
}

// brute-force method
fun findLotto1(lotto: Lotto): Pair<Int, List<Int>> {
    var tries = 0
    do {
        val guess = lotto.pickNDistinct(lotto.lottoRange, lotto.n) ?: listOf()
        tries++
        if (lotto.checkGuess(guess) == 7) {
            return Pair(tries, guess)
        }
    } while(true)
}

// probably faster way to find lotto numbers
fun findLotto(lotto: Lotto): Pair<Int, List<Int>> {
    var tries = 0
    var guess: List<Int>
    // find 0 correct numbers
    do {
        guess = lotto.pickNDistinct(lotto.lottoRange, lotto.n) ?: listOf()
        tries++
    } while(lotto.checkGuess(guess) != 0)
    // change one number at a time to guess a new number
    val correct = mutableListOf<Int>()
    for(i in (1..40).minus(guess)) {
        val testNumbers = guess.take(6) + listOf(i)
        tries++
        if(lotto.checkGuess(testNumbers) == 1) {
            correct.add(i)
            if (correct.size == 7)
                break
        }
    }
    return Pair(tries, correct.sorted())
}

fun main() {
    playLotto()
}