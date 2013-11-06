// 11 - maps

object maps {
  val capitalOfCountry =
    Map("US" -> "Washington", "England" -> "London")

  capitalOfCountry("US")

  capitalOfCountry("France")

  val capitalOfCountryWithDefault =
    capitalOfCountry withDefaultValue "<unknown>"

  capitalOfCountryWithDefault("England")

  capitalOfCountryWithDefault("France")
}