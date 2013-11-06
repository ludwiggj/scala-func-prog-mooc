// 12 - maps

object maps {
  val capitalOfCountry =
    Map("US" -> "Washington", "England" -> "London")

  capitalOfCountry("US")

  capitalOfCountry("France")

  val capitalOfCountryWithDefault =
    capitalOfCountry withDefaultValue "<unknown>"

  capitalOfCountryWithDefault("England")

  capitalOfCountryWithDefault("France")

  val map123 =
    Map(1 -> "One", 2 -> "Two", 3 -> "Three")

  capitalOfCountry.foldLeft("") {
    case (acc, (country, capital))
      => (acc
            + "(Country[" + country + "]"
            + ", Capital[" + capital + "]) ")
  }

  capitalOfCountry - "England"

  capitalOfCountry.updated("US", "Puerto Angeles")
}