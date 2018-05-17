package idealized

abstract class Nat {
  def isZero: Boolean

  def predecessor: Nat

  def successor: Nat = new Succ(Zero)

  def +(that: Nat): Nat

  def -(that: Nat): Nat
}

object Zero extends Nat {
  override def isZero = true

  override def predecessor = throw new NoSuchElementException

  override def +(that: Nat) = that

  override def -(that: Nat) =
    if (that.isZero) that
    else throw new NoSuchElementException
}

class Succ(n: Nat) extends Nat {
  override def isZero = false

  override def predecessor = n

  override def +(that: Nat) =
    if (that.isZero) this
    else new Succ(n + that)

  override def -(that: Nat) =
    if (that.isZero) this
    else n - that.predecessor
}