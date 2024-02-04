package advanced.dsl

fun main() {
    val yml = dockerCompose {
        version { 3 }
        service(name = "db") {
            image { "mysql" }
            env("USER" toMapping "noose")
            env("PASSWORD" toMapping "1234")
            env("PROFILE" - "local")
            port(3306, 3306)
        }
    }

    println(yml.render("  "))
}

fun dockerCompose(init: DockerCompose.() -> Unit): DockerCompose {
    val dockerCompose = DockerCompose()
    dockerCompose.init()
    return dockerCompose
}

