import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@JsonRootName("MyObj")
@JsonPropertyOrder(value = ["age", "name"])
data class MyStateObject(val name: String = "", val age: Int = -1)

val jsonMapper = jacksonObjectMapper()
val xmlMapper = XmlMapper()

val myStateObject = MyStateObject("Sheldon", 30)

/* 1. Библиотека Jackson */

val dat = SimpleDateFormat("yyyy.MM.dd")

val jksnMapper = jacksonObjectMapper()
    .apply {
        dateFormat = dat
    }

data class Lesson(
    val name: String,
    // @JsonFormat(pattern = "dd.MM.YYYY")
    // Аннотация - меняющая формат вывода,
    // в случае использования двух одновременно, будет использоваться эта аннотация
    val date: Date
)

/* 2. Библиотека kotlinx.serialization */

@Serializable
data class Course(
    val name: String,
    @SerialName(value = "tutor")
    val person: Person? = null
)

val ktlnxCourse: Course = Course("Math", Person("Leonard Euler"))
val serKtlnxCourse = Json.encodeToString(ktlnxCourse)
val deserKtlnxCourse = Json.decodeFromString<Course>("{\"name\": \"Phys\"}")

/* 3. Настройка сериализации */

@Serializable
enum class WeekType {
    TRAINING, SESSION, HOLIDAY
}

val arrayOfWeakType = arrayListOf(
    1 to WeekType.TRAINING,
    2 to WeekType.TRAINING,
    3 to WeekType.SESSION,
    4 to WeekType.HOLIDAY
)

val serArrayOfWeakType: String = Json.encodeToString(arrayOfWeakType)

@Serializable
data class Week(
    val number: Int,
    @Serializable(with = WeekTypeSerializer::class)
    //Аннотация для указания того, что для сериализации необходимо использовать созданный нами объект
    val type: WeekType?
)
val weekArrayForSer = arrayOfWeakType.map{ Week(it.first, it.second)}
val serWeekArray: String = Json.encodeToString(weekArrayForSer)

object WeekTypeSerializer : KSerializer<WeekType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("c2_lab2", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: WeekType) =
        encoder.encodeString(
            when(value) {
                WeekType.HOLIDAY -> "Каникулы"
                WeekType.SESSION -> "Сессия"
                WeekType.TRAINING -> "Обучение"
            }
        )
    override fun deserialize(decoder: Decoder): WeekType =
        when(decoder.decodeString()) {
            "Каникулы" -> WeekType.HOLIDAY
            "Сессия" -> WeekType.SESSION
            else -> WeekType.TRAINING
        }
}

val withCreatedSerializer = arrayOfWeakType.map {Week(it.first, it.second)}
val serWithCreatedSerializer = Json.encodeToString(withCreatedSerializer)

fun main() {
    println("\n 1. Библиотека Jackson /")
    val date = Lesson("Java Date", Date(System.currentTimeMillis()))
    val jsonJksn: String = jksnMapper.writeValueAsString(date)
    println(jsonJksn)
    println("\n 2. Библиотека kotlinx.serialization /")
    println(serKtlnxCourse)
    println(deserKtlnxCourse)
    println("\n 3. Настройка сериализации /")
    println(serArrayOfWeakType)
    println(serWeekArray)
    println()

}

