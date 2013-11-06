package demo
import demo.Anagrams.{Sentence, Occurrences, combinations, sentenceOccurrences,
                      dictionaryByOccurrences, subtract, wordOccurrences}

object demo5_sentenceAnagrams {

  // Function - sentenceAnagrams
  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {

    def findAnagram(occurrences: Occurrences): List[Sentence] = {
      occurrences match {
        case Nil => List(List())
        case _ => for {
          combination <- combinations(occurrences)
          word <- dictionaryByOccurrences(combination)
          restOfSentence <- findAnagram(subtract(occurrences, wordOccurrences(word)))
        } yield (word :: restOfSentence)
      }
    }

    findAnagram(sentenceOccurrences(sentence))
  }

  // sentence anagrams
  sentenceAnagrams(List("Ernie", "is", "evil"))

  sentenceAnagrams(List("Ernie", "is", "evil"))
    .filter(_.contains("reel"))

  sentenceAnagrams(List("Ernie", "is", "evil"))
    .filter(_.forall(_.length() >= 4))
}