object calculator1 {

  // pass in function to process each term in the list...
  // sum is now a higher-order function....
  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 0 else f(a) + sum(f)(a + 1, b)
  }

  println(sum(x => x)(2, 4))
  println(sum(x => x * x)(2, 4))
  println(sum(x => x * x * x)(2, 4))
}
