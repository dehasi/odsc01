package expr

trait Expr {
  def eval: Int
}

class Number(n: Int) extends Expr {
  def numValue = n

  override def eval = n
}

class Sum(e1: Expr, e2: Expr) extends Expr {
  override def eval = e1.eval + e2.eval
}