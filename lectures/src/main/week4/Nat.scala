package week4

abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat = new Succ(this)
  def + (that: Nat): Nat
  def - (that: Nat): Nat
}

object Zero extends Nat {
  def isZero = true
  def predecessor = throw new Error("0.predecessor")
  def + (that: Nat) = that
  def - (that: Nat) = if (that.isZero) this else throw new Error("0.predecessor")
  override def toString = "Zero"
}

class Succ(n: Nat) extends Nat {
  def isZero = false
  def predecessor = n
  def + (that: Nat) = new Succ(n + that)
  //def - (that: Nat) = new Succ(n - that)
  def - (that: Nat) = if (that.isZero) this else n - that.predecessor
  override def toString = "Succ (" + predecessor+ ")"
}

object doIt {
  def main(args: Array[String]) {  
    val One = Zero.successor 
    val Two = Zero.successor.successor 
    println("Zero=" + Zero)
    println("1=" + One)
    println("2=" + Two)
    println("0=" + One.predecessor)
    println("0+0=" + (Zero + Zero))
    println("0+2=" + (Zero + Two))
    println("2+0=" + (Two + Zero))
    println("2+1=" + (Two + One))
    println("0-0=" + (Zero - Zero))
    //println("0-1=" + (Zero - succ1))
    println("1-1=" + (One - One))
    println("2-1=" + (Two - One))
  }
}