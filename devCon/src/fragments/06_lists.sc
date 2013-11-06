// 06 - lists

object lists {

  val fruit = List("apples", "oranges", "pears")

  val moreFruit = "apples" :: "oranges" :: "pears" :: Nil

  fruit.head

  fruit.tail

  fruit.tail.head

  fruit.tail.tail

  fruit.tail.tail.tail

  fruit.tail.tail.tail.tail
}