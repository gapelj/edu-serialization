import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main() {
    println("\t\tsimple")
    val sheldon = Student("Sheldon")
    val sheldonJson = Json.encodeToString(sheldon)
    val leonardJson = sheldonJson.replace("Sheldon", "Leonard")
    val leonard = Json.decodeFromString<Student>(leonardJson)
    listOf(sheldon, sheldonJson, leonardJson, leonard)
        .forEach { println(it) }

    println("\t\tconstructor")
    println(Json.encodeToString(Person("Sheldon Cooper")))

    println("\t\tnested object")
    println(Json.encodeToString(Group("Physicist", listOf(sheldon, leonard))))

    println("\t\tcontrol properties")
    println(Json.encodeToString(Grade(0, sheldon)))

    println("\t\tcollections")
    println(Json.encodeToString(listOf(leonard, sheldon)))
    println(
        Json.encodeToString(
            Json.decodeFromString<Set<Int>>("[1, 1, 2]")
        )
    )
    println(Json.encodeToString(mapOf(1 to leonard, 2 to sheldon)))
    println(
        (Json { allowStructuredMapKeys = true })
            .encodeToString(mapOf(leonard to 1, sheldon to 2))
    )

}