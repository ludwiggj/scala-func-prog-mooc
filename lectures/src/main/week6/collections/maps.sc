object maps {
  val capitalOfCountry = Map("US" -> "Washington", "England" -> "London")


  val countryOfCapital = capitalOfCountry map {
    case (x, y) => (y, x)
  }


  def showCapital(country: String) =
    capitalOfCountry.get(country) match {
      case Some(capital) => capital
      case None => "oops"
    }
  showCapital("US")
  showCapital("France")
  capitalOfCountry("US")
  countryOfCapital("London")
  val fruit = List("banana", "plum", "pear", "kumquat")
  fruit sortWith (_.length > _.length)
  fruit groupBy (_.length)


  fruit groupBy (_.head)



  val capitalOfCountryWithDefault = capitalOfCountry withDefaultValue "<unknown>"


  capitalOfCountryWithDefault("NotACountryWillResultInDefaultValue")
  capitalOfCountryWithDefault("England")
  // Following line raises an exception
  //  > java.util.NoSuchElementException: key not found: NotACountryWillRaiseAnException
  //    	at scala.collection.MapLike$class.default(MapLike.scala:228)
  // etc...
  // capitalOfCountry("NotACountryWillRaiseAnException")

  val map123 = Map(1 -> "One", 2 -> "Two", 3 -> "Three")

  map123(1)
  map123.apply(1)
  map123.get(1)
  map123.getOrElse(1, "Not there")
  // map123(0)        //  > java.util.NoSuchElementException: key not found
  // map123.apply(0)  //  > java.util.NoSuchElementException: key not found
  map123.get(0)
  map123.getOrElse(0, "Not there")

  val map123WithDefaultValue = Map(1 -> "One", 2 -> "Two", 3 -> "Three") withDefaultValue ("No dice!")

  map123WithDefaultValue(1)
  map123WithDefaultValue.apply(1)
  map123WithDefaultValue.get(1)
  map123WithDefaultValue.getOrElse(1, "Not there")
  map123WithDefaultValue(0)
  map123WithDefaultValue.apply(0)
  map123WithDefaultValue.get(0) // Returns None
  map123WithDefaultValue.getOrElse(0, "Not there")
  // Not there returned

  val map123WithDefault = Map(1 -> "One", 2 -> "Two", 3 -> "Three") withDefault (x => "Key " + x + " not present")
  map123WithDefault(1)
  map123WithDefault.apply(1)
  map123WithDefault.get(1)
  map123WithDefault.getOrElse(1, "Not there")
  map123WithDefault(0)
  map123WithDefault.apply(0)
  map123WithDefault.get(0) // Returns None
  map123WithDefault.getOrElse(0, "Not there") // Not there returned
}