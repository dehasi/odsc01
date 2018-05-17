package conslist

/** Created by Ravil on 10/03/2018. */
trait List[T] {
  def isEmpty : Boolean
  def head: T
  def tail: List[T]
}
