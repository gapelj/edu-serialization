import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class Task(
    val name: String,
    @Serializable(with = DateAsLongSerializer::class)
    val date: Date
)