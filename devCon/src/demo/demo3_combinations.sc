package demo
import demo.Anagrams.{combinations, wordOccurrences}
object demo3_combinations {

  // combinations of occurrences
  wordOccurrences("is")

  combinations(wordOccurrences("is"))

  wordOccurrences("Ernie")

  combinations(wordOccurrences("Ernie"))
}