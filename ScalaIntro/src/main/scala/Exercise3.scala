object Exercise3 {
  private def funTab1(arr: Array[Int]): Array[Int] = {
    arr.sorted
  }

  private def funTab2(arr: Array[Int]): Array[Int] = {
    val evenNums: Array[Int] = arr.filter(x => x % 2 == 0).sorted
    val oddNums: Array[Int] = arr.filter(x => x % 2 != 0).sorted(Ordering[Int].reverse)

    evenNums ++ oddNums
  }

  private def funTab3(arr: Array[Int]): Array[Int] = {
    arr.sortBy(x => x.abs)
  }

  def main(args: Array[String]): Unit = {
    val tab = Seq.fill(40)(util.Random.nextInt(200)-100).toArray
    println(funTab1(tab).mkString("Array(", ", ", ")"))
    println(funTab2(tab).mkString("Array(", ", ", ")"))
    println(funTab3(tab).mkString("Array(", ", ", ")"))
  }
}
