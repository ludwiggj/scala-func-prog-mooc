
val fruit = List("apples", "oranges", "pears")
val moreFruit = "apples" :: "oranges" :: "pears" :: Nil

fruit.head
moreFruit.tail
moreFruit.tail.head
moreFruit.tail.head.isEmpty
moreFruit.tail.tail
moreFruit.tail.tail.isEmpty
val grid = List(List(0,0,1), List(0,1,0), List(1,0,0))
val anotherGrid = (0 :: 0 :: 1 :: Nil) :: (0 :: 1 :: 0 :: Nil) :: (1 :: 0 :: 0 :: Nil) :: Nil


val flattenedGrid = (0 :: 0 :: 1 :: Nil) ::: (0 :: 1 :: 0 :: Nil) ::: (1 :: 0 :: 0 :: Nil) ::: Nil




grid.tail
val empty = List()
empty.isEmpty
val anotherEmptyOne = Nil
val isItEmpty = Nil :: Nil :: Nil
isItEmpty.head
isItEmpty.head.isEmpty
//Following command fails with:
//> java.util.NoSuchElementException: head of empty list
//  	at scala.collection.immutable.Nil$.head(List.scala:337)
//empty.head
55 :: Nil
// Following command fails with:
//> <console>:8: error: type mismatch;
//   found   : Int
//   required: List[?]

//66 ::: Nil
//55 :: 66 ::: Nil
// <console>:8: error: value :: is not a member of Int
//(55 :: 66) ::: Nil
(55 :: 66 :: Nil) ::: Nil
Nil
Nil :: Nil
Nil ::: Nil
(Nil :: Nil) ::: Nil