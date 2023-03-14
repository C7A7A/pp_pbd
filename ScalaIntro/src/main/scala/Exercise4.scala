import scala.io.Source

object Exercise4 {
  def main(args: Array[String]): Unit = {
    val file = "http://www.textfiles.com/etext/AUTHORS/DOYLE/doyle-hound-383.txt"
    val html = Source.fromURL(file)
    val s = html.mkString.replaceAll("""[\p{Punct}]""", "").toLowerCase()
    val tokens = s.split("\\W+").toList
    var words = Map[String, Int]()

    words = tokens.groupBy(identity).view.mapValues(_.size).toMap
    val wordsSorted = words.toSeq.sortBy(_._2)(Ordering.Int.reverse)
    val wordsThreeMostCommon = wordsSorted.take(3)
    println(wordsThreeMostCommon)

    var chosenWords = List(
      ("murder", words.get("murder")),
      ("scream", words.get("scream")),
      ("Watson", words.get("Watson")),
      ("watson", words.get("watson"))
    )
    println(chosenWords)
  }
}
