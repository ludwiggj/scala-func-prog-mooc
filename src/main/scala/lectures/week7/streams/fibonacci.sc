import scala.language.postfixOps

/*
def loop(h: Int, n: Int): Stream[Int] = h #:: loop(n, h + n)
loop(0, 1)

0 #:: loop(1, 0 + 1)
=> 0 #:: loop(1, 1)
0 #:: 1 #:: loop(1, 1 + 1)
=> 0 #:: 1 #:: loop(1, 2)
0 #:: 1 #:: 1 #:: loop(2, 1 + 2)
=> 0 #:: 1 #:: 1 #:: loop(2, 3)
0 #:: 1 #:: 1 #:: 2 #:: loop(3, 2 + 3)
=> 0 #:: 1 #:: 1 #:: 2 #:: loop(3, 5)
etc...
*/
object fibonacci {
  lazy val fib: Stream[Int] = {
    def loop(h: Int, n: Int): Stream[Int] = h #:: loop(n, h + n)
    loop(0, 1)
  }

  fib;
  (fib take 4) toList;

  fib;
  (fib take 6) toList;

  fib;

  fib.head;
  fib.tail;
}


