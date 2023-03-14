import scala.annotation.tailrec

object Exercise1 {

  private def power(x: Double, n: Int): Double = {
    val pow: Double = x

    if (n == 0) return 1
    if (n == 1) return pow
    if (n == 2) return pow * pow

    if (n > 0) {
      if (n % 2 == 0) {
        val y = power(pow, n / 2)
        return y * y
      } else {
        return pow * power(pow, n-1)
      }
    } else {
      return 1 / power(pow, n * -1)
    }
  }

  private def power_tail_recursive(base: Int, exp: Int): BigInt = {
    @tailrec
    def _power(result: BigInt, exp: Int): BigInt = exp match {
      case 0 => result
      case _ => _power(result * base, exp - 1)
    }

    _power(1, exp)
  }

    def main(args: Array[String]): Unit = {
//    println("power 2^10  z : " + power(2, 10))
//    println("power 2^10  z : " + power_tail_recursive(2, 10))
      val rand = new util.Random(100)
      val tab = Seq.fill(10000)(rand.nextInt(200)).toArray

      def timeOf[A](f: => A): Unit = {
        val s = System.nanoTime
        val r = f
        println("time: " + (System.nanoTime - s) / 1e9 + " sec.")
      }

      timeOf(tab.map((x) => power_tail_recursive(x, x)));
      timeOf(tab.map((x) => power(x, x)));
  }
}
