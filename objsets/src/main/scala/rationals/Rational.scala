package rationals

/** Created by Ravil on 08/03/2018. */
class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be nonzero")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  private val g = gcd(x, y)

  def numer = x / g

  def denom = y / g

  def <(that: Rational) = numer * that.denom < denom * that.numer

  def max(that: Rational) = if (this < that) that else this

  def unary_- : Rational = new Rational(-numer, denom)

  def -(that: Rational) = this + -that

  def +(that: Rational) =
    new Rational(
      numer * that.denom + denom * that.numer,
      denom * that.denom)

  def *(that: Rational) = new Rational(numer * that.numer, denom * that.denom)

  override def toString: String = numer + "/" + denom
}
