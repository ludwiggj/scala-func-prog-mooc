package week6

object polynomials {
  class Poly(val terms: Map[Int, Double]) {
    def + (other:Poly) = new Poly(terms ++ (other.terms map adjust))
    def adjust(term: (Int, Double)):(Int, Double) = {
      val (exp, coeff) = term
      terms get exp match {
        case Some(coeff1) => exp -> (coeff + coeff1)
        case None => exp -> coeff
      }
    }
    override def toString =
     (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
  }
  
  class Poly1(val terms0: Map[Int, Double]) {
    def this(bindings: (Int, Double)*) = this(bindings.toMap)
    val terms = terms0 withDefaultValue 0.0
    def + (other:Poly1) = new Poly1(terms ++ (other.terms map adjust))
    def adjust(term: (Int, Double)):(Int, Double) = {
      val (exp, coeff) = term
      exp -> (coeff + terms(exp))
    }
    override def toString =
     (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
  }
  
  class Poly2(val terms0: Map[Int, Double]) {
    def this(bindings: (Int, Double)*) = this(bindings.toMap)
    val terms = terms0 withDefaultValue 0.0
    def + (other:Poly2) = new Poly2((other.terms foldLeft terms) (addTerm))
    def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
      val (exp, coeff) = term
      terms + (exp -> (coeff + terms(exp)))
    }

    override def toString =
     (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
  }
  
  //def sum(list: List[Int]): Int = list.foldLeft(0)((r,c) => r+c)
  
  val p1 = new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
                                                  //> p1  : week6.polynomials.Poly = 6.2x^5 + 4.0x^3 + 2.0x^1
  val p2 = new Poly(Map(0 -> 3.0, 3 -> 7.0))      //> p2  : week6.polynomials.Poly = 7.0x^3 + 3.0x^0
  p1 + p2                                         //> res0: week6.polynomials.Poly = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  
  val p3 = new Poly1(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
                                                  //> p3  : week6.polynomials.Poly1 = 6.2x^5 + 4.0x^3 + 2.0x^1
  val p4 = new Poly1(Map(0 -> 3.0, 3 -> 7.0))     //> p4  : week6.polynomials.Poly1 = 7.0x^3 + 3.0x^0
  p3 + p4                                         //> res1: week6.polynomials.Poly1 = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  
  val p5 = new Poly1(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)//> p5  : week6.polynomials.Poly1 = 6.2x^5 + 4.0x^3 + 2.0x^1
  val p6 = new Poly1(0 -> 3.0, 3 -> 7.0)          //> p6  : week6.polynomials.Poly1 = 7.0x^3 + 3.0x^0
  p5 + p6                                         //> res2: week6.polynomials.Poly1 = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
  
  val p7 = new Poly2(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)//> p7  : week6.polynomials.Poly2 = 6.2x^5 + 4.0x^3 + 2.0x^1
  val p8 = new Poly2(0 -> 3.0, 3 -> 7.0)          //> p8  : week6.polynomials.Poly2 = 7.0x^3 + 3.0x^0
  p7 + p8                                         //> res3: week6.polynomials.Poly2 = 6.2x^5 + 11.0x^3 + 2.0x^1 + 3.0x^0
}