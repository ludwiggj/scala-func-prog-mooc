// 03 - Function returning a function

object functionReturningAFunction {
  def adder = (n: Int) => { (x: Int) => x + n }

  def add3 = adder(3)

  add3(2)

  add3(15)

  def add10 = adder(10)

  add10(2)
}