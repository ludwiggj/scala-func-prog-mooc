object primes {
  def isPrime(n: Int): Boolean = {
    (2 until n) forall (x => n % x != 0)
  }

  (1 until 50).foreach({
    x =>

      val str = if (isPrime(x)) (x + " is prime") else (x + " is not prime")
      println(str)
  }
  )
































































































}