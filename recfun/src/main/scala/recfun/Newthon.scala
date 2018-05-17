package recfun

object Newthon {
  def main(args: Array[String]) {
    println("sqrt(4)= " + sqrt(4))
    println("sqrt(2)= " + sqrt(2))
    println("sqrt(1e-6)= " + sqrt(1e-6))
    println("sqrt(1e60)= " + sqrt(1e60))
  }

  def sqrt(x: Double): Double = {

    def sqrtIter(x: Double, guess: Double): Double = {
      if (isGoodEnough(x, guess)) guess
      else sqrtIter(x, improve(x, guess))
    }

    def abs(x: Double): Double = if (x >= 0) x else -x

    def isGoodEnough(x: Double, guess: Double): Boolean = abs(guess * guess - x) / x < 0.001

    def improve(x: Double, guess: Double): Double = (guess + x / guess) / 2.0

    sqrtIter(x, 1.0)
  }
}
