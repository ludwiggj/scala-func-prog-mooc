package main.week6.combinatorial

object primePairs {

  def isPrime(n: Int): Boolean = {
    (2 until n) forall (x => n % x != 0)
  }

  def primePairs(n: Int) = {
    ((1 until n) map (i => (1 until i) map (j => (i, j)))).flatten filter {
      case (x, y) => isPrime(x + y)
    }
  }

  def primePairs2(n: Int) = {
    ((1 until n) flatMap (i => (1 until i) map (j => (i, j)))) filter {
      case (x, y) => isPrime(x + y)
    }
  }

  def primePairs3(n: Int) = {
    for {
      i <- 1 until n
      j <- 1 until i
      if isPrime(i + j)
    } yield (i, j)
  }

  def primePairsUnwound(n: Int) = {
    (1 until n).flatMap(i =>
      (1 until i).withFilter(j => isPrime(i + j))
        .map(j => (i, j)))
  }

  def main(args: Array[String]) = {
    println(primePairs(5))
    println(primePairs2(5))
    println(primePairs3(5))
    println(primePairs3(7))
    println(primePairsUnwound(7))

    var x = for {
      i <- List(10, 20, 30, 40, 50)
      j <- 1 to 3
    } yield i

    println(x)
  }
}