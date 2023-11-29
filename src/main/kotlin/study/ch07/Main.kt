package study.ch07

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

fun main() {
    println(parseIntOrThrow("100"))
    println(parseIntOrThrowV2("100A"))
    readFileV1()
    readFileV2("test.txt")
}

fun parseIntOrThrow(str: String): Int {
    try {
        return str.toInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("숫자가 아닙니다. - ${str}")
    }
}

fun parseIntOrThrowV2(str: String): Int? {
    // 하나의 Expression 으로 간주한다.
    return try {
        str.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}

fun readFileV1() {
    // Checked Exception 과 Unchecked Exception 을 구분하지 않고 모두 Unchecked Exception 으로 간주한다.
    val currentFile = File(".")
    val file = File(currentFile.absolutePath + "/test.txt")
    val reader = BufferedReader(FileReader(file))
    println(reader.readLine())
    reader.close()
}

fun readFileV2(path: String) {
    // try with resources 대신 use 라는 inline 확장함수 사용
    BufferedReader(FileReader(path)).use { reader ->
        println(reader.readLine())
    }
}
