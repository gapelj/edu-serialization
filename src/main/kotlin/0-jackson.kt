import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

@JsonRootName("MyObj")
@JsonPropertyOrder(value = ["age", "name"])
data class MyStateObject(val name: String="", val age: Int=-1)

val jsonMapper = jacksonObjectMapper()
val xmlMapper = XmlMapper()

val myStateObject = MyStateObject("Sheldon", 30)

fun main(){
    val json = jsonMapper.writeValueAsString(myStateObject)
    val xml = xmlMapper.writeValueAsString(myStateObject)

    val newJson = json.replace("30", "35")
    val newXml = xml.replace("30", "35")

    val newJsonObject = jsonMapper.readValue<MyStateObject>(newJson)
    val newXmlObject = xmlMapper.readValue<MyStateObject>(newXml)

    println(json)
    println(xml)
    println(newJson)
    println(newXml)
    println(newJsonObject)
    println(newXmlObject)
}

