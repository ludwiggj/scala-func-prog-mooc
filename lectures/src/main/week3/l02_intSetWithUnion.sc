import main.week3.{Empty_WithUnion, NonEmpty_WithUnion}

object main {
  val t1 = new NonEmpty_WithUnion(1, Empty_WithUnion, Empty_WithUnion)
  val t2 = t1 incl 2
  val t3 = new NonEmpty_WithUnion(3, Empty_WithUnion, Empty_WithUnion) incl 4
  val t4 = t2 union t3
  val t5 = t3 union t2
}