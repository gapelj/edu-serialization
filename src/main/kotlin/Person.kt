import kotlinx.serialization.Serializable

@Serializable
data class Person private constructor(val firstname: String, val surname: String){
    constructor(name: String) : this(
        name.substringBefore(" "),
        name.substringAfter(" ")
    )

    val name: String
        get() = "$firstname $surname"
}