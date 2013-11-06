// 02 - Functions

object functions {

  def double(x: Int) = { 2 * x }

  double(12)

  // A function literal (anonymous function)
  // (<List of fn parameters>) => { ... fn body as  a block ...}

  (x: Int) => { 2 * x }

  // Assign function literal

  val doubler = (x: Int) => { 2 * x }

  doubler(24)
}