class Fraction(numerator: Int, denominator: Int, sign: Int = 1) : Comparable<Fraction> {
    private val numerator: Int
    private val denominator: Int
    private val sign: Int

    init {
        require(denominator != 0) { "Denominator cannot be zero." }

        val gcd = gcd(kotlin.math.abs(numerator), kotlin.math.abs(denominator))

        this.numerator = kotlin.math.abs(numerator) / gcd
        this.denominator = kotlin.math.abs(denominator) / gcd
        this.sign = if (numerator * denominator < 0) -1 * sign else sign
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }

    fun add(f: Fraction): Fraction {
        val num = numerator * f.denominator * sign + f.numerator * denominator * f.sign
        val den = denominator * f.denominator
        return Fraction(num, den)
    }

    fun negate(): Fraction {
        return Fraction(numerator, denominator, -sign)
    }

    fun mult(f: Fraction): Fraction {
        val num = numerator * f.numerator
        val den = denominator * f.denominator
        return Fraction(num, den, sign * f.sign)
    }

    operator fun plus(other: Fraction): Fraction {
        return this.add(other)
    }

    operator fun minus(other: Fraction): Fraction {
        return this.add(other.negate())
    }

    operator fun times(other: Fraction): Fraction {
        return this.mult(other)
    }

    operator fun div(other: Fraction): Fraction {
        return this * Fraction(other.denominator, other.numerator, other.sign)
    }

    operator fun unaryMinus(): Fraction {
        return this.negate()
    }

    override fun compareTo(other: Fraction): Int {
        val a = this.sign * this.numerator * other.denominator
        val b = other.sign * other.numerator * this.denominator
        return a.compareTo(b)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Fraction) return false
        return numerator == other.numerator &&
                denominator == other.denominator &&
                sign == other.sign
    }

    override fun hashCode(): Int {
        var result = numerator
        result = 31 * result + denominator
        result = 31 * result + sign
        return result
    }

    override fun toString(): String {
        val result = if (sign < 0) "-" else ""
        return "$result$numerator/$denominator"
    }
}

fun main() {
    val a = Fraction(1,2,-1)
    println(a)
    println(a.add(Fraction(1,3)))
    println(a.mult(Fraction(5,2, -1)))
    println(a.div(Fraction(2,1)))
    println(-Fraction(1,6) + Fraction(1,2))
    println(Fraction(2,3) * Fraction(3,2))
    println(Fraction(1,2) > Fraction(2,3))
    // Comparable interface function compareTo()
}
