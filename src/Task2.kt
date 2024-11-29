class Solution {
   private fun euclide( n: Int, m: Int): Int { // usg AKA GCD. Main algorithm which helps us to calculate USG/USJ
        var r: Int
        var a: Int = n
        var b: Int= m

        while(true) {
            r = a % b
            if(r == 0) return  b
            a = b
            b = r
        }
    }

    fun gcd(n: Int, m: Int): Int {
        return euclide(n, m)
    }

    fun lcm(n: Int, m: Int): Int {
        return (n * m) / euclide(n, m)
    }

    fun hasDollar(string: String): Boolean {
        for(i in string) {
            if (i == '$')
                return  true
        }
        return  false
    }

    fun sumOfEvenNumbers(n: Int = 0, sum: Int = 0): Int {
        if(n > 100)
            return sum
        return  sumOfEvenNumbers(n + 2, sum + n)
    }

    fun reverseNumber(n: Int): Int {
        var answer: Int = 0;
        var n = n

        var isNegative: Boolean = if(n < 0) true else false

        if(isNegative) n *= -1

        while(n > 0) {
            answer = answer * 10 + n % 10
            n /= 10
        }

        return  if(isNegative) -answer else answer
    }


}

fun main() {
    val solution: Solution = Solution()
    println(solution.gcd(n = 12, m = 18))
    println(solution.lcm(n = 12, m = 18))
    println(solution.hasDollar(string = "Where is another sky," +
                                    "Deeply blue and pure,exactly such, " +
                                    "like yours.Wound of the past, " +
                                    "Ruins of \"Narikala\"" +
                                    "Which remained like gray hair."))

    println(solution.hasDollar(string = "I need a dollar, dollar" +
                                    "Dollar, that's what I need (Hey, hey)" +
                                    "Well, I need a do'$'lar, dollar" +
                                    "Dollar, that's what I need (Hey, hey)" +
                                    "Said I need a dollar, dollar"))

    println(solution.sumOfEvenNumbers())
    println(solution.reverseNumber(n = 10220))
    println(solution.reverseNumber(n = -10220))

}