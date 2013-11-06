package demo

import common._
import scala.language.postfixOps

class Anagrams {}

object Anagrams {

  /** A word is simply a `String`. */

  type Word = String

  /** A sentence is a `List` of words. */
  
  type Sentence = List[Word]

  /** `Occurrences` is a `List` of pairs of characters and positive integers saying how often the character appears. */
  
  type Occurrences = List[(Char, Int)]

  /** Dictionary is a sequence of words. */
  
  val dictionary: List[Word] = loadDictionary

  /** Converts a word into its character occurrence list. */
  
  def wordOccurrences(w: Word): Occurrences = {
    val countMap = (w.toLowerCase groupBy ((ch: Char) => ch)) map {
      case (ch, occurrences) => (ch, occurrences length)
    }
    countMap.toList.sorted
  }

  /** Converts a sentence into its character occurrence list. */
  
  def sentenceOccurrences(s: Sentence): Occurrences = wordOccurrences(s mkString)

  /** The `dictionaryByOccurrences` is a `Map` from different occurrences to a sequence of all
    * the words that have that occurrence count.                                               */
  
  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] =
    dictionary groupBy wordOccurrences withDefaultValue List()

  /** Returns all the anagrams of a given word. */
  
  def wordAnagrams(word: Word): List[Word] =
    dictionaryByOccurrences(wordOccurrences(word))

  /** Returns the list of all subsets of the occurrence list.
    * This includes the occurrence itself, i.e. `List(('k', 1), ('o', 1))`
    * is a subset of `List(('k', 1), ('o', 1))`. It also include the empty subset `List()`. */
    
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

  /** Subtracts occurrence list `y` from occurrence list `x`, where `y` is a subset of `x` */

  def subtract(x: Occurrences, y: Occurrences): Occurrences = {
    y.toMap.foldLeft(x.toMap) {
      case (xMap, (char, currentCount)) => {
        val newCount = xMap(char) - currentCount
        if (newCount == 0) (xMap - char) else xMap.updated(char, newCount)
      }
    }.toList.sorted
  }

  /** Returns a list of all anagram sentences of the given sentence. */
  
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
}