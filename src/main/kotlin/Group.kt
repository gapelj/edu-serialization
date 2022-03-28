import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val name: String,
    val students: List<Student>,
)