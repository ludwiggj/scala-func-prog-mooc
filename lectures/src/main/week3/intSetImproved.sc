package week3

object doItAgain {
  val t1 = new NonEmptyN(3, EmptyN, EmptyN)       //> t1  : week3.NonEmptyN = {.[3].}
  val t2 = t1 incl 4                              //> t2  : week3.IntSetN = {.[3]{.[4].}}
  
  val t3 = new NonEmptyN(6, EmptyN, EmptyN) incl 1//> t3  : week3.IntSetN = {{.[1].}[6].}
                                                  
  val t4 = t2 union t3                            //> t4  : week3.IntSetN = {{.[1]{.[3]{.[4].}}}[6].}
  val t5 = t3 union t2                            //> t5  : week3.IntSetN = {{.[1].}[3]{.[4]{.[6].}}}
}

abstract class IntSetN {
  def contains(x: Int): Boolean
  def incl(x: Int): IntSetN
  def union(other: IntSetN): IntSetN
}

object EmptyN extends IntSetN {
  def contains(x: Int) = false
  def incl(x: Int) = new NonEmptyN(x, EmptyN, EmptyN)
  def union(other: IntSetN) = {
    other
  }
  override def toString = "."
}

class NonEmptyN(elem:Int, left: IntSetN, right: IntSetN) extends IntSetN {
  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
    
  def incl(x: Int): IntSetN =
    if (x < elem) new NonEmptyN(elem, left incl x, right)
    else if (x > elem) new NonEmptyN(elem, left, right incl x)
    else this
    
  def union(other: IntSetN) = {
    left union (right union (other incl elem))
  }
    
  override def toString = "{" + left + "[" + elem + "]" + right + "}"
}