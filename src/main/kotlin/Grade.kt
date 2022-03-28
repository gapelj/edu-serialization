import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
class Grade(
    @Transient val id: Int=-1,
    @SerialName("person") val student: Student,
    @Required val value: Int = 1
)