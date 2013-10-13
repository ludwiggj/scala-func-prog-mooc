import main.week3.{Empty, NonEmpty}

object doIt {
  val t1 = new NonEmpty(3, new Empty, new Empty)  //> t1  : week3.NonEmpty = {.3.}
  val t2 = t1 incl 7                              //> t2  : week3.IntSet = {.3{.5.}}
}