package main.week4.pattern_matching

trait Expr2 {
  def eval: Int = this match {
    case ANumber(n) => n
    case ASum(e1, e2) => e1.eval + e2.eval
  }

  def show: String = this match {
    case ANumber(n) => n.toString
    case ASum(e1, e2) => "(" + e1.show + " + " + e2.show + ")"
  }

  override def toString: String = show + " = " + eval
}
case class ANumber(n: Int) extends Expr2
case class ASum(e1: Expr2, e2: Expr2) extends Expr2