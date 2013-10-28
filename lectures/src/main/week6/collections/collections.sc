object collections {
  val people = Vector("Bob", "James", "Peter")
  "Annie" +: people :+ "Zelda"

  val xs: Array[Int] = Array(1, 2, 3)
  xs map (x => 2 * x)
  xs.mkString(",")
  (xs map (x => 2 * x)).mkString(",")
  val ys: String = "Hello World!"
  ys filter (_.isUpper)
  val r: Range = 1 until 5
  val s: Range = 1 to 5
  1 to 10 by 3
  6 to 1 by -2

  Set("apple", "banana", "pear")
  Set("apple", "banana", "pear") filter (_.startsWith("ap"))
  (1 to 6).toSet
  (1 to 6).toSet[Int] map (_ + 12)
  val s = (1 to 6).toSet
  s map (_ + 2)
  s.nonEmpty
  s map (_ / 2)
  s contains 3
}