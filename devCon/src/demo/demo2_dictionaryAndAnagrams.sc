package demo
import demo.Anagrams.{Word, Occurrences, dictionary, wordOccurrences}

object demo2_dictionaryAndAnagrams {

  // Function - dictionaryByOccurrences
  val dictionaryByOccurrences: Map[Occurrences, List[Word]] =
    dictionary groupBy wordOccurrences withDefaultValue List()
}