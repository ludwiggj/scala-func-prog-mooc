package demo

import demo.Anagrams._

object demo1_occurrences {

  // The sentence we shall work on is...
  "Ernie is evil"

  // Word occurrencies
  "Ernie".toLowerCase groupBy ((ch: Char) => ch)

  wordOccurrences("Ernie")

  // Sentence occurrences
  sentenceOccurrences(List("Ernie", "is", "evil"))
}