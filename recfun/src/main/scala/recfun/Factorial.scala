package recfun

import scala.annotation.tailrec

/** Created by Ravil on 28/02/2018. */
object Factorial {


  def main(args: Array[String]): Unit = {
    println("factorial(5) " + factorial(5))
  }

  def factorial(n: Int): Int = {

    @tailrec
    def tFactorial(n: Int, buf: Int): Int = if (n == 1) buf else tFactorial(n - 1, buf * n)

    tFactorial(n, 1)
  }
}
