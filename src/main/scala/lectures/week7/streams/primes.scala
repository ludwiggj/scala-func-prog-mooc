package lectures.week7.streams

object primes {
  def isPrime(n: Int): Boolean = {
    (2 until n) forall (x => n % x != 0)
  }

  def nthPrime(from: Int, to: Int, n: Int): Int = {
    if (from >= to) {
      throw new Error("no prime")
    }
    else if (isPrime(from)) {
      if (n == 1) from else nthPrime(from + 1, to, n - 1)
    }
    else nthPrime(from + 1, to, n)
  }

  def main(args: Array[String]) = {
    println("Calculate primes using recursion....")

    println("  First prime = " + nthPrime(1000, 10000, 2))
    println("  Sixth prime = " + nthPrime(1000, 10000, 6))

    val primeList: List[Int] = (1000 to 1050).toList filter isPrime

    println("\nCalculate primes with a List[Int]....")
    println("  primes [" + primeList + "]\n")

    println("  First prime = " + primeList(0))
    println("  primes [" + primeList + "]\n")

    println("  Second prime = " + primeList(1))
    println("  primes [" + primeList + "]\n")

    println("  Sixth prime = " + primeList(5))
    println("  primes [" + primeList + "]\n")


    val primeStream: Stream[Int] = (1000 to 1050).toStream filter isPrime

    println("Calculate primes with a Stream[Int]....")
    println("  primes [" + primeStream + "]\n")

    println("  First prime = " + primeStream(0))
    println("  primes [" + primeStream + "]\n")

    println("  Second prime = " + primeStream(1))
    println("  primes [" + primeStream + "]\n")

    println("  Sixth prime = " + primeStream(5))
    println("  primes [" + primeStream + "]\n")
  }
}