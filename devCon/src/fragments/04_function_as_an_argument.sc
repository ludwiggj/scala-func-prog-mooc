// 04 - A function as an argument, and function types

object functionAsAnArgument {

  def apply(f: (Int) => (Int), value: Int) = {
    f(value)
  }

  def add5(x: Int) = { x + 5 }

  def subtract3(x: Int) = { x - 3 }

  apply(add5, 2)

  apply(subtract3, 7)

  type operation = (Int) => (Int)

  def applyOperation(f: operation, value: Int) = {
    f(value)
  }

  applyOperation(add5, 2)
}