import kotlinx.serialization.Serializable

@Serializable
abstract class Tutor {
    abstract val name: String
}

@Serializable
class Curator(
    override val name: String,
    val group: Group
) : Tutor()
