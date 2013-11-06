package demo
import demo.Anagrams.{ Occurrences, sentenceOccurrences, wordOccurrences }

object demo4_subtract {

  // Function - subtract
  def subtract(x: Occurrences, y: Occurrences): Occurrences = {
    y.toMap.foldLeft(x.toMap) {
      case (xMap, (char, currentCount)) => {
        val newCount = xMap(char) - currentCount
        if (newCount == 0) (xMap - char) else xMap.updated(char, newCount)
      }
    }.toList.sorted
  }

  // subtract
  sentenceOccurrences(List("Ernie", "is", "evil"))

  wordOccurrences("Ernie")

  subtract(sentenceOccurrences(List("Ernie", "is", "evil")),
    wordOccurrences("Ernie"))

  subtract(sentenceOccurrences(List("Ernie", "is", "evil")),
    sentenceOccurrences(List("Ernie", "is", "evil")))
}