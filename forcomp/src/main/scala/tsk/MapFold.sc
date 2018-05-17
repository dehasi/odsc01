def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  (xs foldRight List[U]()) (f(_) :: _)

mapFun[Int, Int](List(1, 2, 3, 4), x => x * x)

def lengthFun[T](xs: List[T]): Int =
  (xs foldRight 0) ((x, y) => y + 1)

lengthFun(List(1, 2, 3, 4))