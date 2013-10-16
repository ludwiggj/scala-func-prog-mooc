import annotation.tailrec

object l03_tailRecSum {

  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    @tailrec
    def loop(a: Int, acc: Int): Int = {
      if (a > b)
        acc
      else
        loop(a+1, f(a) + acc)
    }
    loop(a, 0)
  }

  println("Sum 1 to 11 => " + sum(identity)(1, 11))
}