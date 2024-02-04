package advanced.dsl

@MyYamlDsl
class Service(val name: String) {
    private var image: String by onceNotNull()
    private val environments = mutableListOf<Environment>()
    private val portRules = mutableListOf<PortRule>()

    fun image(init: () -> String) {
        image = init()
    }

    fun env(environment: Environment) {
        this.environments.add(environment)
    }

    fun port(host: Int, container: Int) {
        this.portRules.add(PortRule(host = host, container = container))
    }

    fun render(indent: String): String {
        val builder = StringBuilder()
        builder.appendNew("$name:")
        builder.appendNew("image: $image", indent, 1)
        builder.appendNew("environment:", indent, 1)
        environments.joinToString("\n")
            .addIndent(indent, 2)
            .also { builder.appendNew(it) }
        builder.appendNew("port:")
        portRules.joinToString("\n")
            .addIndent(indent, 1)
            .also { builder.appendNew(it) }
        return builder.toString()
    }
}

data class PortRule(
    val host: Int,
    val container: Int,
) {
    override fun toString(): String {
        return "- ${host}:${container}"
    }
}


data class Environment(
    val key: String,
    val value: String,
) {
    override fun toString(): String {
        return "- $key: $value"
    }
}

operator fun String.minus(other: String): Environment {
    return Environment(
        key = this,
        value = other,
    )
}

infix fun String.toMapping(value: String): Environment {
    return Environment(this, value)
}
