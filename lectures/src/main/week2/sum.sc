package week2

import annotation.tailrec

object sum {

  def verboseSum(f: Int => Int, a: Int, b: Int): Int = {
    @tailrec
    def loop(a: Int, acc: Int): Int = {
      if (a > b)
        acc
      else
        loop(a+1, f(a) + acc)
    }
    loop(a, 0)
  }                                               //> verboseSum: (f: Int => Int, a: Int, b: Int)Int
  
  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 0 else f(a) + sum(f)(a + 1, b)
  }                                               //> sum: (f: Int => Int)(a: Int, b: Int)Int
  
  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1 else f(a) * product(f)(a + 1, b)
  }                                               //> product: (f: Int => Int)(a: Int, b: Int)Int
  
  def factorial(n: Int): Int = {
    product(identity)(1, n)
  }                                               //> factorial: (n: Int)Int
  
  def identity(x: Int): Int = {
    x
  }                                               //> identity: (x: Int)Int
  
  def multiply(x: Int, y: Int): Int = {
    x * y
  }                                               //> multiply: (x: Int, y: Int)Int
  
  def square(x: Int): Int = {
    multiply(x, x)
  }                                               //> square: (x: Int)Int
  
  def add(x: Int, y: Int): Int = {
    x + y
  }                                               //> add: (x: Int, y: Int)Int
  
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, identityTerm: Int)(a: Int, b: Int): Int = {
    if (a > b) identityTerm else combine(f(a), mapReduce(f, combine, identityTerm)(a + 1, b))
  }                                               //> mapReduce: (f: Int => Int, combine: (Int, Int) => Int, identityTerm: Int)(a:
                                                  //|  Int, b: Int)Int
  def anotherFactorial(n: Int): Int = {
    mapReduce(identity, multiply, 1)(1, n)
  }                                               //> anotherFactorial: (n: Int)Int
  
  def anotherProduct(f: Int => Int)(a: Int, b: Int): Int = {
    mapReduce(f, (x,y) => x * y, 1)(a, b)
  }                                               //> anotherProduct: (f: Int => Int)(a: Int, b: Int)Int
  
  anotherProduct(x => x * x)(3,4)                 //> res0: Int = 144
  
  def sumOfSquares(n: Int): Int = {
    mapReduce(square, add, 0)(1, n)
  }                                               //> sumOfSquares: (n: Int)Int
  
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  println("Sum 1 to 10 => " + verboseSum(x => x, 1, 10))
                                                  //> Sum 1 to 10 => 55
  println("Sum 1 to 11 => " + sum(identity)(1, 11))
                                                  //> Sum 1 to 11 => 66
  println("Product 1 to 11 => " + product(identity)(1, 11))
                                                  //> Product 1 to 11 => 39916800
  println("5! => " + factorial(5))                //> 5! => 120
  println("5! => " + anotherFactorial(5))         //> 5! => 120
  println("sum of squares 1..5 => " + sumOfSquares(5))
                                                  //> sum of squares 1..5 => 55
}