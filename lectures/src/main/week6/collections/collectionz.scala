object collectionz {
  def main(args: Array[String]) {
    val people = Vector("Bob", "James", "Peter")
    println("Annie" +: people :+ "Zelda")

    val xs: Array[Int] = Array(1, 2, 3)
    println(xs.mkString(","))
    println((xs map (x => 2 * x)).mkString(","))

    val ys: String = "Hello World!"
    println(ys filter (_.isUpper))

    val r: Range = 1 until 5
    println(r)
  }
}