import scala.Int

object l02_currying {

  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 0 else f(a) + sum(f)(a + 1, b)
  }

  // Curried form
  def curriedSum(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0 else f(a) + sumF(a + 1, b)
    sumF
  }

  def curriedProduct(f: Int => Int): (Int, Int) => Int = {
    def productF(a: Int, b: Int): Int =
      if (a > b) 1 else f(a) * productF(a + 1, b)
    productF
  }

  def factorial(n: Int): Int = {
    curriedProduct(x => x)(1, n)
  }

  def sumInts = curriedSum(identity)
  def sumCubes = curriedSum(x => x * x * x)

  def sumFactorials = curriedSum(factorial)
  println("Welcome to the Scala worksheet")
  println(sumInts(1, 10))
  println(sumCubes(1, 10))
  println(sumFactorials(1, 10))
  def identity(x: Int) = x

  def cube(x: Int) = x * x * x
  // Now the full curried form
  println(curriedSum(identity)(1, 10))
  println(curriedSum(cube)(1, 10))
  println(curriedSum(factorial)(1, 10))

  // vs. the non-curried form
  sum(identity)(1, 10)

  // partially applied function
  def n = sum(identity) _

  n(2, 10)
  // Now short version of curried function
  def curriedSumShort(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + curriedSumShort(f)(a + 1, b)

  curriedSumShort(cube)(1, 10)

  // Flipped so we can take advantage of shorthand for fn defn
  def curriedSumShortFlipped(a: Int, b: Int)(f: Int => Int): Int =
    if (a > b) 0 else f(a) + curriedSumShortFlipped(a + 1, b)(f)

  curriedSumShortFlipped(1, 10)(cube)

  // Type of cube function can now be inferred (compare to cube fn definition)
  curriedSumShortFlipped(1, 10)(x => x * x * x)
  // Alternative notation
  curriedSumShortFlipped(1, 10) { x =>
    x * x * x
  }



  println("End of the Scala worksheet")
}