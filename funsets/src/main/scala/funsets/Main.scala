package funsets

object Main extends App {

  import FunSets._

  println(contains(singletonSet(1), 1))
  printSet(union(singletonSet(1), singletonSet(2)))

  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, f(a) + acc)
    }

    loop(a, 0)
  }
}
