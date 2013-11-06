package demo
import demo.Anagrams.{Word, Occurrences, dictionary, wordOccurrences}

object demo2_dictionaryAndAnagrams {

  // Function - dictionaryByOccurrences
  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] =
    dictionary groupBy wordOccurrences withDefaultValue List()

  println(dictionaryByOccurrences)

  println(
    dictionaryByOccurrences.filter {
      case (_, value) => (value.length > 5)
    }.head
  )
}