package advanced.reflection

import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KType
import kotlin.reflect.cast
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.createType
import kotlin.reflect.full.hasAnnotation


annotation class Executable

@Executable
class Reflection {
    fun a() {
        println("A 입니다.")
    }

    fun b(number: Int) {
        println("B 입니다.")
    }

    fun c() {
        println("C 입니다.")
    }
}

fun executeAll(obj: Any) {
    val kClass = obj::class
    if (!kClass.hasAnnotation<Executable>()) {
        return
    }

    val callableFunctions = kClass.members.filterIsInstance<KFunction<*>>()
        .filter { it.returnType == Unit::class.createType() }
        .filter { it.parameters.size == 1 && it.parameters[0].type == kClass.createType() }

    callableFunctions.forEach { it.call(kClass.createInstance()) }
}


fun main() {
    val kClass: KClass<Reflection> = Reflection::class
    val ref = Reflection()
    val kClass2: KClass<out Reflection> = ref::class
    val kClass3: KClass<out Any> = Class.forName("advanced.reflect.Reflection").kotlin
    kClass.java // Class<Reflection>
    kClass.java.kotlin // KClass<Reflection>

    val kType: KType = GoldFish::class.createType() // KType, 타입을 표현. ex) Int, Int? ...

    val goldFish = GoldFish("금붕이")
    goldFish::class.members.first { it.name == "print" }.call(goldFish)

    executeAll(Reflection())
}

class GoldFish(val name: String) {
    fun print() {
        println("금붕어 이름은 ${name}입니다.")
    }
}

fun castToGoldFish(obj: Any): GoldFish {
    return GoldFish::class.cast(obj)
}
