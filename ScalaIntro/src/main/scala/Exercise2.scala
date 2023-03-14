import java.util.TimeZone.getAvailableIDs

object Exercise2 {
  def main(args: Array[String]): Unit = {
    var timezones: Array[String] = getAvailableIDs
    timezones = timezones
      .filter(zone => zone.startsWith("Australia"))
      .map(zone => zone.replace("Australia/", ""))
      .sorted

    println(timezones.mkString("Array(", ", ", ")"))
  }
}
