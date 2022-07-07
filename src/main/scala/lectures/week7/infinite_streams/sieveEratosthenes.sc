import scala.language.postfixOps

object sieveEratosthenes {
  // Stream of all integers from a given start point
  def from(n: Int): Stream[Int] = n #:: from(n + 1)

  def sieve(s: Stream[Int]): Stream[Int] =
    s.head #:: sieve(s.tail filter (_ % s.head != 0))

  val from2_5 = from(2)
  val primes_5 = sieve(from2_5) take 5 toList

  val show_from2_5 = from2_5
  val from2_26: Stream[Int] = from(2)
  val primes_26 = sieve(from2_26) take 26 toList

  val show_from2_26 = from2_26

  // Build up from first principles
  val from_2 = from(2)
  from_2.head;
  from_2.tail;
  lazy val recurse = from_2.tail filter (_ % from_2.head != 0);
  (recurse take 10).toList;
  recurse.head
  recurse.tail

  lazy val recurse1 = recurse.tail filter (_ % recurse.head != 0);
  (recurse1 take 10).toList;
  recurse1.head
  recurse1.tail
}