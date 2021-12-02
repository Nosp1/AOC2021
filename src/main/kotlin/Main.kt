import java.io.File

fun main(args: Array<String>) {
    val list = readLines("./src/main/kotlin/input.txt").map { it.toInt() }
    val task1a = countTask1A(list)
    println(task1a)

    val summedLists = listSorter(list)
    val task1b = countTask1B(summedLists)
    println(task1b)
}

fun listSorter(list: List<Int>): List<Int> {
    val lists: MutableList<MutableList<Int>> = mutableListOf()
    for (i in 0 until list.size - 3) {
        lists.add(mutableListOf(list[i], list[i + 1], list[i + 2]))
    }
    return lists.map { li -> li.sum() }
}

fun countTask1B(list: List<Int>): Int {
    var count = 0
    var last = 0
    list.forEach { i ->
        if (i > last) {
            count++
        }
        last = i
    }
    return count
}


fun countTask1A(list: List<Int>): Int {
    var count = 0
    for (i in 1 until list.size) {
        if (list[i] > list[i - 1]) {
            count++
        }
    }
    return count
}

fun readLines(fileName: String): List<String> {
    val file = File(fileName)
    return file.readLines()
}