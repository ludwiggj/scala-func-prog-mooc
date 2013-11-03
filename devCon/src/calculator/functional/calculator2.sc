object calculator2 {

  // introduction of function type
  type termTransformer = (Int => Int)

  def sum(f: termTransformer)(a: Int, b: Int): Int = {
    if (a > b) 0 else f(a) + sum(f)(a + 1, b)
  }

  def identity: termTransformer = (x => x)
  def squarer: termTransformer = (x => x * x)
  println(sum(identity)(2, 4))
  println(sum(squarer)(2, 4))
  println(sum(x => x * x * x)(2, 4))
}
