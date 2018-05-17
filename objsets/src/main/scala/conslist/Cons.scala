package conslist

/** Created by Ravil on 10/03/2018. */
class Cons[T](val  head: T, val tail: List[T]) extends List[T] {
  override def isEmpty: Boolean = false
}
