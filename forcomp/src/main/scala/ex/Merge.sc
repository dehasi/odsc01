def merge[T](xs: List[T], ys: List[T])(implicit ord: Ordering[T]): List[T] =
  (xs, ys) match {
    case (xs, Nil) => xs
    case (Nil, ys) => ys
    case (x :: xss, y :: yss) => if (ord.lt(x, y)) x :: merge(xss, ys)
    else y :: merge(xs, yss)
  }

val l1 = List(1, 3, 5)
val l2 = List(2, 4, 6)
merge(l1, l2)

def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: xs1 =>
    val (first, second) = xs span (e => e == x)
    first :: pack(second)
}

def encode[T](xs: List[T]) : List[(T, Int)] =
  pack(xs) map (x => (x.head, x.size))
pack(List("a", "a", "a", "b", "b", "c", "a"))
encode(List("a", "a", "a", "b", "b", "c", "a"))

for (
  i <- 1 to 2
) yield for (
  j <- 1 to 4
) yield (i, j)