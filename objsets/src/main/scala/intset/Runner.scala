package intset

/** Created by Ravil on 10/03/2018. */
object Runner {
  def main(args: Array[String]): Unit = {
    val t1 = new NonEmpty(3,  Empty,  Empty)
    val t2 = t1 incl 4
    val t3 = new NonEmpty(5,  Empty,  Empty)
    println(t1)
    println(t2)
    val u1 = t2 union t3
    println(u1)
  }
}
