package demo
import demo.Anagrams.{ Occurrences, wordOccurrences }

object demo3_combinations {

  // Function - combinations
  def combinations(occurrences: Occurrences): List[Occurrences] = {
    occurrences match {
      case Nil => List(List())
      case (headCh, headCount) :: tail => {
        val combis = for {
          chosenCount <- 0 to headCount
          tailSolution <- combinations(tail)
        } yield if (chosenCount > 0) ((headCh, chosenCount) :: tailSolution) else tailSolution
        combis.toList
      }
    }
  }

  // combinations of occurrences
  combinations(wordOccurrences("is"))

  combinations(wordOccurrences("Ernie"))
}