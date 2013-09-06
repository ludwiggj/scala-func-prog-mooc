package week6

object maps {
  val capitalOfCountry = Map("US" -> "Washington", "England" -> "London")
                                                  //> capitalOfCountry  : scala.collection.immutable.Map[String,String] = Map(US -
                                                  //| > Washington, England -> London)
  def showCapital(country: String) =
    capitalOfCountry.get(country) match {
      case Some(capital) => capital
      case None => "oops"
    }                                             //> showCapital: (country: String)String
  showCapital("US")                               //> res0: String = Washington
  showCapital("France")                           //> res1: String = oops
  capitalOfCountry("US")                          //> res2: String = Washington
  //capitalOfCountry("France")
  
  val fruit = List("banana","plum", "pear", "kumquat")
                                                  //> fruit  : List[String] = List(banana, plum, pear, kumquat)
  
  fruit sortWith(_.length > _.length)             //> res3: List[String] = List(kumquat, banana, plum, pear)
  fruit groupBy(_.length)                         //> res4: scala.collection.immutable.Map[Int,List[String]] = Map(4 -> List(plum,
                                                  //|  pear), 7 -> List(kumquat), 6 -> List(banana))
   
}