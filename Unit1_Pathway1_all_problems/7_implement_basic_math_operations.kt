fun main() {
    val firstNumber: Int = 10
    val secondNumber: Int = 5
    val result = firstNumber + secondNumber

    println("$firstNumber + $secondNumber = $result")
}

fun main() {
    val firstNumber: Int = 10
    val secondNumber: Int = 5
    val thirdNumber: Int = 8

    val result = add(firstNumber, secondNumber)
    val anotherResult = add(firstNumber, thirdNumber)

    println("$firstNumber + $secondNumber = $result")
    println("$firstNumber + $thirdNumber = $anotherResult")
}

fun add(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun main() {
    val firstNumber: Int = 10
    val secondNumber: Int = 5
    val thirdNumber: Int = 8

    val result = subtract(firstNumber, secondNumber)

    println("$firstNumber - $secondNumber = $result")
}

fun subtract(num1: Int, num2: Int): Int {
    return num1 - num2
}