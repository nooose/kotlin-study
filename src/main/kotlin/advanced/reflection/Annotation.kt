package advanced.reflection

import kotlin.reflect.KClass

/**
 * SOURCE: 어노테이션이 컴파일 때만 존재
 * BINARY: 어노테이션이 런타임 때도 있지만, 리플렉션을 쓸 수 없다.
 * RUNTIME: 어노테이션이 런타임 때 존재하고, 리플렉션을 쓸 수 있다. (기본값)
 */

@Repeatable
@Target(AnnotationTarget.CLASS) // Target 을 명시하지 않으면 모든 위치에 붙일 수 있다.
annotation class Shape(
    val texts: Array<String>
)

@Shape(["Q"])
@Shape(["A", "B"])
class Annotation {
}

fun main() {
    val clazz: KClass<Annotation> = Annotation::class
}
