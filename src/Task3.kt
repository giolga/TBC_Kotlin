fun main() {
    var georgian: MutableMap<Int, String> = mutableMapOf()
    var english: MutableMap<Int, String> = mutableMapOf()

    val georgianUnits = listOf("", "ერთი", "ორი", "სამი", "ოთხი", "ხუთი", "ექვსი", "შვიდი", "რვა", "ცხრა")
    val georgianTens = listOf("", "ათი", "ოცდა", "ოცდა","ორმოცდა", "ორმოცდა", "სამოცდა", "სამოცდა", "ოთხმოცდა", "ოთხმოცდა")
    val georgianAdditionalNumbers = listOf("", "თერთმეტი", "თორმეტი", "ცამეტი", "თოთხმეტი", "თხუთმეტი", "თექვსმეტი", "ჩვიდმეტი", "თვრამეტი", "ცხრამეტი")
    val georgianHundreds = listOf("", "ას", "ორას", "სამას", "ოთხას", "ხუთას", "ექვსას", "შვიდას", "რვაას", "ცხრაას")
    val georgianThousand = "ათასი"

    val englishUnits = listOf("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val englishTens = listOf("", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")
    val englishAdditionalNumbers = listOf("", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    val thousand = "thousand"

    for (i in 1..1000) {
        var ans: String = ""
        when  {
            i == 1000 -> ans = georgianThousand
            i < 10 -> ans = georgianUnits[i]
            i == 10 -> ans = georgianTens[1]
            i in 11 .. 19 -> ans = georgianAdditionalNumbers[i % 10]
            i in 20 .. 99 -> {
                val tens = i / 10
                val units = i % 10

                if(tens % 2 == 0 && units == 0) {
                    ans = georgianTens[tens].substring(0, georgianTens[tens].length - 2) + 'ი' // + "ათი"
                }

                if(tens % 2 == 0 && units > 0) {
                    ans = georgianTens[tens] + georgianUnits[units]
                }

                if(tens % 2 == 1 && units == 0) {
                    ans = georgianTens[tens - 1] + georgianTens[1] // + "ათი"
                }

                if(tens % 2 == 1 && units > 0) {
                    ans = georgianTens[tens] + georgianAdditionalNumbers[units]
                }
            }

            else -> {
                val hundreds = (i / 100) % 10
                val tens = (i / 10) % 10
                val units = i % 10

//                ans = georgianHundreds[hundreds] + georgianTens[tens] + georgianUnits[units]
                if(tens % 2 == 0 && units == 0) {
                    if(tens > 1) { //if else extra? -\./-
                        ans = georgianHundreds[hundreds] + georgianTens[tens].substring(0, georgianTens[tens].length - 2) + 'ი'
                    }else {
                        ans = georgianHundreds[hundreds] + georgianTens[tens] + 'ი' // + "ათი" base code
                    }

                }

                if(tens % 2 == 0 && units > 0) {
                    ans = georgianHundreds[hundreds] + georgianTens[tens] + georgianUnits[units]
                }

                if(tens % 2 == 1 && units == 0) {
                    ans = georgianHundreds[hundreds] + georgianTens[tens - 1] + georgianTens[1] // + "ათი"
                }

                if(tens % 2 == 1 && units > 0) {
                    ans = georgianHundreds[hundreds] + georgianTens[tens] + georgianAdditionalNumbers[units]
                }

                if(tens == 1 && units > 0) {
                    ans = georgianHundreds[hundreds] + georgianTens[0] + georgianAdditionalNumbers[units]
                }
            }
        }

        georgian[i] = ans
    }

    for(i in georgian) {
        println("${i.key} : ${i.value}")
    }

    for(i in 1 .. 1000) {
        var ans = ""
        when {
            i == 1000 -> ans = thousand
            i < 10 -> ans = englishUnits[i]
            i in 11 .. 19 -> ans = englishAdditionalNumbers[i % 10]
            i in 20 .. 99 -> {
                val tens = i / 10
                val units = i % 10

                if(units == 0) {
                    ans = englishTens[tens]
                }
                else {
                    ans = englishTens[tens] + " " + englishUnits[units]
                }
            }
            else -> {
                val hundred = i / 100
                val tens = (i / 10) % 10
                val units = i % 10

                ans = if(units == 0) englishUnits[hundred] + " hundred " + englishTens[tens]  else englishUnits[hundred] + " hundred " + englishTens[tens] + "-" + englishUnits[units]
            }
        }

        println("$i : $ans")
        english[i] = ans
    }

}