package committee.nova.dateutils

import java.util.concurrent.TimeUnit
import scala.collection.mutable.ListBuffer

object DateUtils {
  val units: List[String] = List("d", "h", "m", "s")

  def formatDateDiff(startTime: Long): String = {
    val diff = System.currentTimeMillis() - startTime
    val days = TimeUnit.MILLISECONDS.toDays(diff)
    val hours = TimeUnit.MILLISECONDS.toHours(diff) % 24
    val minutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(diff) % 60

    val sb = new StringBuilder
    if (days > 0) sb.append(days).append("d ")
    if (hours > 0 || days > 0) sb.append(hours).append("h ")
    if (minutes > 0 || hours > 0 || days > 0) sb.append(minutes).append("m ")
    sb.append(seconds).append("s")
    sb.toString().trim
  }
}
