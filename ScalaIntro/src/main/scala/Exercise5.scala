object Exercise5 {

  private def conReduceLeft(a: Array[String]): String = {
    a.reduceLeft(_ + " " + _)
  }

  private def conReduceRight(a: Array[String]): String = {
    a.reduceRight(_ + " " + _)
  }

  def main(args: Array[String]): Unit = {
    val a = Array("Mr.", "Sherlock", "Holmes,", "who", "was",
      "usually", "very", "late", "in", "the", "mornings,",
      "save", "upon", "those", "not", "infrequent", "occasions", "when", "he", "was", "up",
      "all", "night,", "was", "seated", "at", "the", "breakfast", "table.")

    println(a.mkString(" "))
    println(conReduceLeft(a))
    println(conReduceRight(a))
  }
}
