package lectures.week4.pattern_matching

trait Expr3 {
  def eval: Int = this match {
    case A_Number(n) => n
    case A_Sum(e1, e2) => e1.eval + e2.eval
  }

  def show: String = this match {
    case A_Number(n) => n.toString
    case A_Sum(e1, e2) => e1.show + " + " + e2.show
    case A_Var(name) => name
//    case A_Product(e1, e2) => e1.show + " * " + e2.show
    case A_Product(e1, e2) => showProductTerm(e1) + " * " + showProductTerm(e2)
  }

  def showProductTerm(term: Expr3): String = term match {
    case A_Sum(e1, e2) => "(" + term.show + ")"
    case _ => term.show
  }

  override def toString: String = show
}
case class A_Number(n: Int) extends Expr3
case class A_Sum(e1: Expr3, e2: Expr3) extends Expr3
case class A_Var(name: String) extends Expr3
case class A_Product(e1: Expr3, e2: Expr3) extends Expr3