object maps {
  val capitalOfCountry = Map("US" -> "Washington", "England" -> "London")

  val countryOfCapital = capitalOfCountry map {
    case(x, y) => (y, x)
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
  val fruit = List("banana","plum", "pear", "kumquat")
  fruit sortWith(_.length > _.length)
  fruit groupBy(_.length)

  fruit groupBy(_.head)


  val capitalOfCountryWithDefault = capitalOfCountry withDefaultValue "<unknown>"


  capitalOfCountryWithDefault("NotACountryWillResultInDefaultValue")
  capitalOfCountryWithDefault("England")
  capitalOfCountry("NotACountryWillRaiseAnException")











































}