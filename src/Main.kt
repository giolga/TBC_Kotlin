fun integerParser(myString: String): Int {
    var z: Int = 0
    for(i in myString) {
//        if(i.toInt() >= 48 && i.toInt() <= 57) {
//            z = z * 10 + (i - '0').toInt()
//        }
        if(i in '0' .. '9') { //let's get used to Kotlin syntax ;)
            z = z * 10 + (i - '0').toInt()
        }
    }

    return z
}

fun division(x: Int, y: Int) {
    try {
        println("Division of X and Y is: ${x / y}")
    }catch (e: Exception) {
        println("Division By Zero! Y = $y")
    }
}

fun main() {
    var x: String = readln()
    var y: String = readln()

    var first = integerParser(x)
    var second = integerParser(y)

    println("X Value is: $first")
    println("Y Value is: $second")

    division(first, second)

    var stopProgram: Boolean = false

    while(!stopProgram) {
        print("Would you like to continue running this program? (Y/N): ")
        var answer: String = readln()

        if (answer.isEmpty()) {
            println("Invalid input. Empty String!")
            continue
        }
        if (answer[0].lowercaseChar() != 'y' && answer[0].lowercaseChar() != 'n') {
            println("Invalid Input! please enter Y or N! Your input is: $answer")
            continue
        }

        answer = if(answer[0].lowercaseChar() == 'y') "დიახ" else "არა"

        if(answer == "არა") {
            stopProgram = true
            println("Bye!")
            break
        }

        x = readln()
        y = readln()

        first = integerParser(x)
        second = integerParser(y)

        println("X Value is: $first")
        println("Y Value is: $second")

        division(first, second)
    }
}
