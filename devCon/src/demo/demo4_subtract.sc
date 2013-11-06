package demo
import demo.Anagrams.{ Occurrences, sentenceOccurrences, wordOccurrences, subtract }

object demo4_subtract {

  // subtract
  sentenceOccurrences(List("Ernie", "is", "evil"))

  wordOccurrences("Ernie")

  subtract(
    sentenceOccurrences(List("Ernie", "is", "evil")),
    wordOccurrences("Ernie")
  )

  subtract(
    sentenceOccurrences(List("Ernie", "is", "evil")),
    sentenceOccurrences(List("Ernie", "is", "evil"))
  )
}