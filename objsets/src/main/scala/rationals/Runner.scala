package rationals

/** Created by Ravil on 08/03/2018. */
object Runner {
  def main(args: Array[String]): Unit = {

    val x = new Rational(1, 3)
    val y = new Rational(5, 7)
    val z = new Rational(3, 2)
    println(x + y)
    println(x - y - z)
    println(x < y)
    println(x max y)
  }
 /*
 (all letters)
|
^
&
= !
< >
:
+ -
* / %
(all other special characters)

 a + b ^? c ?^ d less a ==> b | c
((a + b) ^? (c ?^ d)) less ((a ==> b) | c)

  */
}
