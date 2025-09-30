fun main() {
    println("${compareT(200, 150)}")
    println("${compareT(100, 150)}")
}

fun compareT(timeSpentToday: Int, timeSpentYesterday: Int): Boolean {
    return timeSpentToday > timeSpentYesterday
}