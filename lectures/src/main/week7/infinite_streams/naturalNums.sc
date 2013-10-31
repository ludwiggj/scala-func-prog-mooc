object naturalNums {
  // Stream of all integers from a given start point
  def from(n: Int): Stream[Int] = n #:: from(n + 1)

  // All natural numbers
  val nats = from(0)
  (nats take 10) toList



  val nats_200 = from(200)


  (nats_200 take 5) toList





  //all multiples of 4
  (nats map (_ * 4)) take 10 toList



}
