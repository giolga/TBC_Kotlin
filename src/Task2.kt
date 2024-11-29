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

    fun isPalindrome(string: String): Boolean {
        var arr = Array(string.length) {IntArray(string.length)}

        for(k in string.indices) {
            for(i in string.indices) {
                val j: Int = k + i + 1

                if(j <= string.length - 1) {
                    if(string[i] == string[j]) arr[i][j] = arr[i+1][j-1]
                    else arr[i][j] = Math.min(arr[i][j - 1], arr[i+1][j]) + 1
                }
            }
        }

        return if(arr[0][string.length - 1] == 0) true else false
    }

}

fun main() {
    val solution: Solution = Solution()

    println("USG (12, 18):\t ${solution.gcd(n = 12, m = 18)}")
    println("USJ (12, 18): \t ${solution.lcm(n = 12, m = 18)} \n")

    println("Dollar checker: ")
    println("\t ${solution.hasDollar(string = "Where is another sky," +
                                    "Deeply blue and pure,exactly such, " +
                                    "like yours.Wound of the past, " +
                                    "Ruins of \"Narikala\"" +
                                    "Which remained like gray hair.")}")

    println("\t ${solution.hasDollar(string = "I need a dollar, dollar" +
                                    "Dollar, that's what I need (Hey, hey)" +
                                    "Well, I need a do'$'lar, dollar" +
                                    "Dollar, that's what I need (Hey, hey)" +
                                    "Said I need a dollar, dollar")}\n")

    print("Sum of even numbers between 0 and 100: \t")
    println("${solution.sumOfEvenNumbers()} \n")

    println("Reverse numbers: ")
    println("\t ${solution.reverseNumber(n = 10220)}")
    println("\t ${solution.reverseNumber(n = -10220)}\n")

    println("Palindromes:")
    println("\t ${solution.isPalindrome(string = "abracadabra")}") // false
    println("\t ${solution.isPalindrome(string = "civic")}") // true
    println("\t ${solution.isPalindrome(string = "eye")}") // true
    println("\t ${solution.isPalindrome(string = "poatan")}") //false

}