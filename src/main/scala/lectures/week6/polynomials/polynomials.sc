object polynomials {
  class Poly(val terms: Map[Int, Double]) {
    def + (other:Poly) = {
//      new Poly(terms ++ (other.terms map adjust))
      println("terms: " + terms)
      println("other.terms: " + other.terms)
      val adjustedTerms = (other.terms map adjust)
      println("adjustedTerms: " + adjustedTerms)

      // NOTE - This method relies on fact that if the same term
      // appears in both terms and adjustedTerms, then when using
      // ++ operator on two maps, the second value of the key (in
      // adjusted terms) wins out.
      val newTerms = terms ++ adjustedTerms

      println("newTerms: " + newTerms)
      new Poly(newTerms)
    }
    def adjust(term: (Int, Double)):(Int, Double) = {
      val (exp, coeff) = term
      terms get exp match {
        case Some(coeff1) => (exp -> (coeff + coeff1)) // a key / value pair
        case None => exp -> coeff                      // a key / value pair
      }
    }
    override def toString =
     (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
  }

  class PolyWithoutMap(val terms0: Map[Int, Double]) {
    def this(bindings: (Int, Double)*) = this(bindings.toMap)
    val terms = terms0 withDefaultValue 0.0 // Now map returns default value is key is not present
    def + (other:PolyWithoutMap) = new PolyWithoutMap(terms ++ (other.terms map adjust))
    // Compare this to Poly.adjust method
    // No longer need a match clause
    def adjust(term: (Int, Double)):(Int, Double) = {
      val (exp, coeff) = term
      exp -> (coeff + terms(exp))
    }
    override def toString =
     (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
  }

  class PolyWithFoldLeft(val terms0: Map[Int, Double]) {
    def this(bindings: (Int, Double)*) = this(bindings.toMap)
    val terms = terms0 withDefaultValue 0.0
    // New version of +, using FoldLeft
    def + (other:PolyWithFoldLeft) = new PolyWithFoldLeft((other.terms foldLeft terms) (addTerm))
    def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
      val (exp, coeff) = term
      terms + (exp -> (coeff + terms(exp)))
    }
    override def toString =
     (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
  }
  val p1 = new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
  val p2 = new Poly(Map(0 -> 3.0, 3 -> 7.0))
  p1 + p2




  val p3 = new PolyWithoutMap(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
  val p4 = new PolyWithoutMap(Map(0 -> 3.0, 3 -> 7.0))
  p3 + p4
  val p5 = new PolyWithoutMap(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)
  val p6 = new PolyWithoutMap(0 -> 3.0, 3 -> 7.0)
  p5 + p6

  val p7 = new PolyWithFoldLeft(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)
  val p8 = new PolyWithFoldLeft(0 -> 3.0, 3 -> 7.0)
  p7 + p8
}