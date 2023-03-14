object MyScalaWorksheet {
  def main(args: Array[String]): Unit = {
    val stolice = List("Warszawa", "Berlin", "Praga")
    val kraje = List("Polska", "Niemcy", "Czechy")
    val stoliceKrajow = (kraje zip stolice).toMap
    val capOfCzechy = stoliceKrajow.get("Czechy")
    for (capital <- capOfCzechy) println(capital)
  }
}