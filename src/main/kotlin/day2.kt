fun main() {
val list = readLines("./src/main/kotlin/day2.txt")
    calculateDepth(list)
    calculateWithAim(list)
}



fun calculateDepth(input : List<String>) {
    val list1 = input.map { it.split(" ") }
    println(list1)
   val listOfCoordinateValuePair = list1.map{CoordinateValuePair(it[0], it[1].toInt())}
   val sumForward = listOfCoordinateValuePair.filter { it.direction == "forward"}.sumOf { it.value }
   val sumDownward = listOfCoordinateValuePair.filter { it.direction == "down"}.sumOf { it.value }
   val sumUp = listOfCoordinateValuePair.filter { it.direction == "up"}.sumOf { it.value }
   val resultOfUpAndDown =  sumDownward - sumUp
   val multiplied = sumForward * resultOfUpAndDown
   println(multiplied)
}

fun calculateWithAim(list: List<String>) {
    val list1 = list.map { it.split(" ")}
    var aim = 0;
    var horizontalPos = 0;
    var depth = 0
    list1.forEach() {
        when {
            it[0] == "down" -> aim += it[1].toInt()
            it[0] == "up" -> aim -= it[1].toInt()
            it[0] == "forward" -> {
                horizontalPos += it[1].toInt()
                depth += aim * it[1].toInt()
            }
        }
    }
    println(horizontalPos * depth)

}

class CoordinateValuePair(var direction: String, var value: Int) { }