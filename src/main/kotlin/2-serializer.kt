import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.util.*

val task = Task("Test", Date(System.currentTimeMillis()))
val curator = Curator("Leader", Group("team", emptyList()))

val module = SerializersModule {
    polymorphic(
        Info::class,
        Teacher::class,
        Teacher.serializer()
    )
    polymorphic(
        Info::class,
        Learner::class,
        Learner.serializer()
    )
}
val json = Json { serializersModule = module }

val info: List<Info> = listOf(
    Teacher("Cooper", "Dr."),
    Learner("Penny", Group("girls", emptyList()))
)

fun main() {
    println(Json.encodeToString(task))
    println(Json.encodeToString(curator))
    println(json.encodeToString(info))
}