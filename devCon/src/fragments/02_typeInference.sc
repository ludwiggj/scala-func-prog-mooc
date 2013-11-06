// 02 - Type inference, and a simple method

object typeInferenceAndSimpleMethod {
  var x = 10

  var y = "Hello I am " + 43 + " years old"

  def greeting(age: Int) = {
    "Hello I am " + age + " years old"
  }

  greeting(43)

  def recurse(count: Int): Int = {
    if (count > 0) recurse(count - 1) else 0
  }

  recurse(12)
}