package intset

/** Created by Ravil on 10/03/2018. */
abstract class IntSet {
  def incl(x: Int): IntSet

  def contains(x: Int): Boolean

  def union(other: IntSet):IntSet
}
