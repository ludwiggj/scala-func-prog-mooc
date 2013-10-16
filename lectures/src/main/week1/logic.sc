object Logic {

  def loop:Boolean = loop

  // and(x, y)
  def and(x:Boolean, y: => Boolean) = if (x) y else false

  // or(x, y)
  def or(x:Boolean, y: => Boolean) = if (x) true else y

  and(false, loop)
  or(true, loop)
}