fun main() {
    val operatingSystem: String = "Chrome OS"
    val emailId: String = "sample@gmail.com"

    println(displayAlertMessage(operatingSystem, emailId))
}

fun displayAlertMessage(operatingSystem: String, emailId: String): String {
    return "There's a new sign-in request on $operatingSystem for your Google Account $emailId."
}

fun main() {
    val firstUserEmailId: String = "user_one@gmail.com"

    println(displayAlertMessage(emailId = firstUserEmailId))
    println()

    val secondUserOperatingSystem: String = "Windows"
    val secondUserEmailId: String = "user_two@gmail.com"

    println(displayAlertMessage(secondUserOperatingSystem, secondUserEmailId))
    println()

    val thirdUserOperatingSystem: String = "Mac OS"
    val thirdUserEmailId: String = "user_three@gmail.com"

    println(displayAlertMessage(thirdUserOperatingSystem, thirdUserEmailId))
    println()
}

fun displayAlertMessage(operatingSystem: String = "Unknown OS", emailId: String): String {
    return "There's a new sign-in request on $operatingSystem for your Google Account $emailId."
}