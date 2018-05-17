package idealized

abstract class Boolean1 {
  def ifThenElse[T](t: => T, e: => T): T
  def && (x: => Boolean1) : Boolean1 = ifThenElse(x, False)
  def || (x: => Boolean1) : Boolean1 = ifThenElse(True, x)
  def unary_!  : Boolean1 = ifThenElse(False, True)
  def == (x: => Boolean1) : Boolean1 = ifThenElse(x, x.unary_!)
  def != (x: => Boolean1) : Boolean1 = ifThenElse(x.unary_!, x)
  def  < (x: => Boolean1) : Boolean1 = ifThenElse(False, x)

}

object True extends Boolean1 {
  override def ifThenElse[T](t: => T, e: => T) = t
}

object False extends Boolean1 {
  override def ifThenElse[T](t: => T, e: => T) = e
}