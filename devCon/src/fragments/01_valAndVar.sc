// 01 - Vals & Vars

object valAndVars {

  var iCanChange: Double = 8.9

  // This is allowed...

  iCanChange = iCanChange + 2
  val hasItChanged = iCanChange

  // This isn't

  val iCannotChange: Int = 6

  // iCannotChange = iCannotChange + 1
}