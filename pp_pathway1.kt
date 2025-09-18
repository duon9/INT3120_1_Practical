// pp2:
fun pp2() {
    println("Use the val keyword when the value doesn't change.")
	println("Use the var keyword when the value can change.")
	println("When you define a function, you define the parameters that can be passed to it.")
	println("When you call a function, you pass arguments for the parameters.")
}

// pp3: change '} into ")
fun pp3() {
    println("New chat message from a friend")
}

// pp4: change val into var
fun pp4() {
    var discountPercentage: Int = 0
    var offer: String = ""
    var item = "Google Chromecast"
    discountPercentage = 20
    offer = "Sale - Up to $discountPercentage% discount on $item! Hurry up!"
    println(offer)
}

// pp5: numberOfAdults & numberOfKids should be Int
fun pp5() {
    val numberOfAdults = 20
    val numberOfKids = 30
    val total = numberOfAdults + numberOfKids
    println("The total party size is: $total")
}

// pp6: totalSalary should be Int
fun pp6() {
    val baseSalary = 5000
    val bonusAmount = 1000
    val totalSalary = baseSalary + bonusAmount
    println("Congratulations for your bonus! You will receive a total of $totalSalary (additional bonus).")
}

// pp7: missing val result
fun pp7() {
    val firstNumber = 10
    val secondNumber = 5
    val thirdNumber = 8
    
    val result = add(firstNumber, secondNumber)
    val anotherResult = subtract(firstNumber, thirdNumber)

    println("$firstNumber + $secondNumber = $result")
    println("$firstNumber - $thirdNumber = $anotherResult")
}

// Define add() & subtract() function:
fun add(firstNumber: Int, secondNumber: Int): Int {
    return firstNumber + secondNumber
}

fun subtract(firstNumber: Int, secondNumber: Int): Int {
	return firstNumber - secondNumber
}

// pp8
fun pp8() {
    val firstUserEmailId = "user_one@gmail.com"

    // The following line of code assumes that you named your parameter as emailId. 
    // If you named it differently, feel free to update the name.
    println(displayAlertMessage(emailId = firstUserEmailId))

    val secondUserOperatingSystem = "Windows"
    val secondUserEmailId = "user_two@gmail.com"
    println(displayAlertMessage(secondUserOperatingSystem, secondUserEmailId))

    val thirdUserOperatingSystem = "Mac OS"
    val thirdUserEmailId = "user_three@gmail.com"
    println(displayAlertMessage(thirdUserOperatingSystem, thirdUserEmailId))
}

// Define your displayAlertMessage():
fun displayAlertMessage(operatingSystem: String = "Unknown OS", emailId: String): String {
    return "There's a new sign-in request on $operatingSystem for your Google Account $emailId."
}

// pp9: rename function, variables
fun pp9() {
    val step = 4000
    val caloBurned = stepTOcalo(step);
    println("Walking $step steps burns $caloBurned calories") 
}

fun stepTOcalo(step: Int): Double {
    val caloForStep = 0.04
    val total = step * caloForStep
    return total
}

// pp10:
fun pp10() {
    println(isMore(300, 250))
    println(isMore(300, 300))
    println(isMore(200, 220))
}

fun isMore(timeSpentToday : Int, timeSpentYesterday: Int): Boolean {
    return timeSpentToday > timeSpentYesterday
}

// pp11:
fun pp11() {
    println(wInfo("Ankara", 27, 31, 82))
    println(wInfo("Tokyp", 32, 36, 10))
    println(wInfo("Cape Town", 59, 64, 2))
    println(wInfo("Guatemala City", 50, 55, 7))
}

fun wInfo(city: String, lTemp: Int, hTemp: Int, rain: Int): String {
    return "City: $city\n" +
    		"Low temperature: $lTemp, High temperature: $hTemp\n" +
    		"Chance of rain: $rain\n"
}

fun main() {
	pp11()
}