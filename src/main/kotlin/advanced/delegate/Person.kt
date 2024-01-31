package advanced.delegate

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun main() {
    val person = Person()
    person.name = "test"
    println(person.maskingName)

    val person2 = Person2()
    println(person2.name)

    val person3 = Person3()
    println(person3.name)
    println(person3.name)

    val person4 = Person4()
    println(person4.name)

    val person5 = Person5()
    println(person5.num)

    val person6 = Person6(mapOf(
        "name" to "noose",
        "age" to 10,
    ))
    println(person6.name)
    println(person6.age)
}

class Person {

    lateinit var name: String

    val maskingName: String
        get() = name[0] + (1 until name.length).joinToString("") { "*" }
}

class Person2 {
    private var _name: String? = null
    val name: String
        get() {
            if (_name == null) {
                Thread.sleep(2_000L)
                this._name = "noose"
            }
            return this._name!!
        }
}

class Person3 {
    val name: String by lazy { // name 의 getter 를 Lazy 객체의 getter 로 이어준다. (위임)
        Thread.sleep(2_000L)
        "noose"
    }
}

class Person4 {
    private val delegateProperty = LazyInitProperty {
        Thread.sleep(2_000L)
        "noose"
    }
    val name: String
        get() = delegateProperty.value
}

class LazyInitProperty<T>(val init: () -> T) {
    private var _value: T? = null
    val value: T
        get() {
            if (_value == null) {
                this._value = init()
            }
            return _value!!
        }
}

class Person5 {
    @Deprecated("age를 사용하세요!", ReplaceWith("age"))
    var num: Int = 0
    var age: Int by this::num
}

class Person6(map: Map<String, Any>) {
    val name: String by map
    val age: Int by map
}

class Person7 {
    val name by DelegateProperty("noose")
}

class DelegateProperty(
    private val initValue: String,
) : ReadOnlyProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return initValue
    }

}
