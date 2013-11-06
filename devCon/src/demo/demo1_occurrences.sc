package demo

object demo1_occurrences {
  type Word = String
  type Sentence = List[Word]
  type Occurrences = List[(Char, Int)]

  // Word occurrencies - step 1
  "Ernie".toLowerCase groupBy ((ch: Char) => ch)

  // Function - wordOccurrences
  def wordOccurrences(w: Word): Occurrences = {
    val countMap = (w.toLowerCase groupBy ((ch: Char) => ch)) map {
      case (key, value) => (key, value length)
    }
    countMap.toList.sorted
  }

  wordOccurrences("Ernie")

  // Function - sentenceOccurrences
  def sentenceOccurrences(s: Sentence): Occurrences
     = wordOccurrences(s mkString)

  sentenceOccurrences(List("Ernie", "is", "evil"))
}