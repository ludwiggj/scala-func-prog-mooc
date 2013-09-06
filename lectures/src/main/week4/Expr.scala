package week4

trait Expr {
  def eval: Int = this match {
    case Number(n) => n
    case Sum(e1, e2) => e1.eval + e2.eval
  }
  
  def show: String = this match {
    case Number(n) => n.toString
    case Sum(e1, e2) => "(" + e1.show + " + " + e2.show + ")"
  }
}
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr

object RunExpr {
  def main(args: Array[String]) {
    val four = new Number(4)
    val one_plus_eight = new Sum(new Number(1), new Number(8))
    val one_plus_two_plus_three = new Sum(new Sum(new Number(1), new Number(2)), new Number(3))
    val one_plus_two_plus_three_2 = new Sum(new Number(1), new Sum(new Number(2), new Number(3)))
    println(four.eval)
    println(four.show)
    println(one_plus_eight.show + " = " + one_plus_eight.eval)
    println(one_plus_two_plus_three.show + " = " + one_plus_two_plus_three.eval)
    println(one_plus_two_plus_three_2.show + " = " + one_plus_two_plus_three_2.eval)
  }
}