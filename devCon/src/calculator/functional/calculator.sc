object calculator {
  def sum(a: Int, b: Int): Int = {
    if (a > b) 0 else a + sum(a + 1, b)
  }

  def sumOfSquares(a: Int, b: Int): Int = {
    if (a > b) 0 else (a * a) + sumOfSquares(a + 1, b)
  }

  def sumOfCubes(a: Int, b: Int): Int = {
    if (a > b) 0 else (a * a * a) + sumOfCubes(a + 1, b)
  }

  println(sum(2, 4))
  println(sumOfSquares(2, 4))
  println(sumOfCubes(2, 4))
}