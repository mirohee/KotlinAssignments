class FractionMutable(var numerator: Int, var denominator: Int, var sign: Int = 1) {
    fun commonDenominator(a: Int, b: Int): Int {
        return if (b == 0) a else commonDenominator(b, a % b)
    }

    init {
        normalize()
    }

    fun normalize() {
        if (numerator < 0) {
            numerator = -numerator
            sign = -sign
        }
        val commonDen = commonDenominator(numerator, denominator)
        numerator /= commonDen
        denominator /= commonDen
    }

    fun add(f: FractionMutable) {
        val num = numerator * f.denominator * sign + f.numerator * denominator * f.sign
        val den = denominator * f.denominator
        val commonDen = commonDenominator(num, den)
        numerator = num / commonDen
        denominator = den / commonDen
        normalize()
    }

    fun negate() {
        sign = -sign
    }

    fun mult(f: FractionMutable) {
        numerator *= f.numerator
        denominator *= f.denominator
        sign *= f.sign
        normalize()
    }

    fun div(f: FractionMutable) {
        numerator *= f.denominator
        denominator *= f.numerator
        sign *= f.sign
        normalize()
    }

    fun intPart(): Int {
        return numerator / denominator
    }


    override fun toString(): String {
        val result = if (sign < 0) "-" else ""
        return "$result$numerator/$denominator"
    }



}
fun main() {
    val a = FractionMutable(1,2,-1)
    a.add(FractionMutable(1,3))
    println(a)
    a.mult(FractionMutable(5,2, -1))
    println(a)
    a.div(FractionMutable(2,1))
    println(a)
    val b = FractionMutable(1, 2)
    b.mult(FractionMutable(1, 3, -1))
    println(b)
}


