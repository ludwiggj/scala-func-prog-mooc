package week4.idealised

abstract class Boolean {
  def ifThenElse[T](t: => T, e: => T): T
  def && (x: => Boolean): Boolean = {
    println("&& this [" + this + "] x [" + x + "]")
    ifThenElse(x, falsy)
  }
  
  def || (x: => Boolean): Boolean = {
    println("|| this [" + this + "] x [" + x + "]")
    ifThenElse(truthy, x)
  }
  
  def unary_! : Boolean = ifThenElse(falsy, truthy)
  
  def == (x: Boolean): Boolean = ifThenElse(x, !x)
  
  def != (x: Boolean): Boolean = ifThenElse(!x, x)
  
  def < (x: Boolean): Boolean = ifThenElse(falsy, x)
}

object falsy extends Boolean {
  def ifThenElse[T](t: => T, e: => T) = {
    println("falsy ifThenElse called t [" + t + "] e [" + e + "]")
    e
  }
  override def toString = "falsy!"
}

object truthy extends Boolean {
  def ifThenElse[T](t: => T, e: => T) = {
    println("truthy ifThenElse called t [" + t + "] e [" + e + "]")
    t
  }
  override def toString = "truthy!"
}

object Boolean {
  def testOperand(operand: String, f: (Boolean, Boolean) => Boolean, x: Boolean, y: Boolean) = {
    println(x + " " + operand + " " + y)
    println(f(x, y))
    println()
  }
  
  def testOperand(operand: String, f: (Boolean) => Boolean, x: Boolean) = {
    println(operand + " " + x)
    println(f(x))
    println()
  }
  
  def &&(x: Boolean, y: Boolean) = {
    testOperand("&&", (x,y) => x && y, x, y)
  }
  
  def ||(x: Boolean, y: Boolean) = {
    testOperand("||", (x,y) => x || y, x, y)
  }
  
  def not(x: Boolean) = {
    testOperand("!", (x) => !x, x)
  }
  
  def ==(x: Boolean, y: Boolean) = {
    testOperand("==", (x,y) => x == y, x, y)
  }
  
  def !=(x: Boolean, y: Boolean) = {
    testOperand("!=", (x,y) => x != y, x, y)
  }
  
  def <(x: Boolean, y: Boolean) = {
    testOperand("<", (x,y) => x < y, x, y)
  }
     
  def main(args: Array[String]) {  
     &&(falsy, falsy)
     &&(falsy, truthy)
     &&(truthy, falsy)
     &&(truthy, truthy)
     
     ||(falsy, falsy)
     ||(falsy, truthy)
     ||(truthy, falsy)
     ||(truthy, truthy)
     
     not(falsy)
     not(truthy)
     
     ==(falsy, falsy)
     ==(falsy, truthy)
     ==(truthy, falsy)
     ==(truthy, truthy)

     !=(falsy, falsy)
     !=(falsy, truthy)
     !=(truthy, falsy)
     !=(truthy, truthy)
     
     <(falsy, falsy)
     <(falsy, truthy)
     <(truthy, falsy)
     <(truthy, truthy)
  }
}