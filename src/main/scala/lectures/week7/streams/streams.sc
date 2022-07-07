object streams {
  def streamRange(lo: Int, hi: Int): Stream[Int] =
    if (lo >= hi) Stream.empty
    else Stream.cons(lo, streamRange(lo + 1, hi))

  def streamRange2(lo: Int, hi: Int): Stream[Int] = {
    println(lo + " ")
    if (lo >= hi) Stream.empty
    else Stream.cons(lo, streamRange2(lo + 1, hi))
  }

  def listRange(lo: Int, hi: Int): List[Int] =
    if (lo >= hi) Nil
    else lo :: listRange(lo + 1, hi)
  Stream.empty
  Stream.cons(1, Stream.cons(2, Stream.empty))
  Stream(1, 2)
  List()
  1 :: 2 :: Nil
  List(1, 2)
  listRange(1, 1)
  listRange(1, 5)
  streamRange(1, 1)
  streamRange(1, 5)
  streamRange(1, 5).tail
  val x = streamRange(1, 10)
  val evens = (x filter (_ % 2 == 0))
  evens.tail
  evens
  evens.tail.tail
  evens
  evens.toList
  evens
  evens.tail
  evens
  evens.tail.tail.tail
  evens.tail.tail.tail.tail
  val z = streamRange(1, 10)
  z take 3
  z take 4
  (z take 3).toList
  z
  z take 4
  z
  (z take 4).toList
  z
  streamRange2(1, 10)

  streamRange2(1, 10).take(3)

  streamRange2(1, 10).take(3).toList

  val zz = streamRange(1,10)
  zz.take(3).toList
  zz.head
  zz.tail
}