object l01_sum {

  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 0 else f(a) + sum(f)(a + 1, b)
  }

  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1 else f(a) * product(f)(a + 1, b)
  }

  def factorial(n: Int): Int = {
    product(identity)(1, n)
  }

  def multiply(x: Int, y: Int): Int = {
    x * y
  }

  def square(x: Int): Int = {
    multiply(x, x)
  }

  def add(x: Int, y: Int): Int = {
    x + y
  }

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, identityTerm: Int)(a: Int, b: Int): Int = {
    if (a > b) identityTerm else combine(f(a), mapReduce(f, combine, identityTerm)(a + 1, b))
  }




  def anotherFactorial(n: Int): Int = {
    mapReduce(identity, multiply, 1)(1, n)
  }

  def anotherProduct(f: Int => Int)(a: Int, b: Int): Int = {
    mapReduce(f, (x,y) => x * y, 1)(a, b)
  }

  def sumOfSquares(n: Int): Int = {
    mapReduce(square, add, 0)(1, n)
  }

  println("Welcome to the Scala worksheet")
  println("Sum 1 to 11 => " + sum(identity)(1, 11))
  println("Product 1 to 11 => " + product(identity)(1, 11))
  println("5! => " + factorial(5))
  println("6! => " + anotherFactorial(6))
  println("7! => " + anotherProduct(identity)(1, 7))
  println("sum of squares 1..5 => " + sumOfSquares(5))
  println("End of the Scala worksheet")
}