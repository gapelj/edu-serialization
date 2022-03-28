import kotlinx.serialization.Serializable

interface Info{
    val string: String
}

@Serializable
class Teacher(
    val name: String,
    val title: String
): Info {
    override val string: String
        get() = "$title $name"
}

@Serializable
class Learner(
    val name: String,
    val group: Group
): Info {
    override val string: String
        get() = "$name ($group)"
}
