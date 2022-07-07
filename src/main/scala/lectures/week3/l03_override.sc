import lectures.week3.{Base, Sub}

object overrides {
  println("Welcome to the Scala worksheet")
  new Sub
  (new Sub).foo
  (new Sub).bar
  // (new Sub).type // Doesn't compile
}