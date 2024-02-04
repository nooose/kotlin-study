package advanced.dsl

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@MyYamlDsl
class DockerCompose {
    private var version: Int by onceNotNull()
    private val services = mutableListOf<Service>()

    fun version(init: () -> Int) {
        version = init()
    }

    fun service(name: String, init: Service.() -> Unit) {
        val service = Service(name)
        service.init()
        services.add(service)
    }

    fun render(indent: String): String {
        val builder = StringBuilder()
        builder.appendNew("version: '$version'")
        builder.appendNew("services:")
        builder.appendNew(services.joinToString("\n") {
            it.render(indent)
        }.addIndent(indent, 1))
        return builder.toString()
    }
}

fun <T> onceNotNull() = object : ReadWriteProperty<Any?, T> {
    private var value: T? = null
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (this.value == null) {
            throw IllegalArgumentException("변수가 초기화되지 않았습니다.")
        }
        return this.value!!
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (this.value != null) {
            throw IllegalArgumentException("이 변수는 한 번만 값을 초기화할 수 있습니다.")
        }
        this.value = value
    }
}

fun StringBuilder.appendNew(str: String, indent: String = "", times: Int = 0) {
    (1..times).forEach { _ -> this.append(indent)}
    this.append(str)
    this.append("\n")
}

fun String.addIndent(indent: String, times: Int = 0): String {
    val allIndent = indent.repeat(times)
    return this.split("\n")
        .joinToString("\n") { "$allIndent$it" }
}
