package committee.nova.sjl10n

import com.google.gson.{Gson, JsonObject}
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

object L10nUtilities {
  class JsonText(data: JsonObject) {
    def get(key: String): String = {
      if (data != null && data.has(key)) {
         try {
           data.get(key).getAsString
         } catch {
           case _: Exception => key
         }
      } else {
        key
      }
    }
  }

  def create(modid: String, lang: String): JsonText = {
    val path = s"/assets/$modid/lang/$lang.json"
    val is = getClass.getResourceAsStream(path)
    if (is == null) {
      return new JsonText(new JsonObject)
    }
    try {
      val reader = new InputStreamReader(is, StandardCharsets.UTF_8)
      val gson = new Gson()
      val json = gson.fromJson(reader, classOf[JsonObject])
      new JsonText(json)
    } catch {
      case e: Exception =>
        e.printStackTrace()
        new JsonText(new JsonObject)
    }
  }
}
