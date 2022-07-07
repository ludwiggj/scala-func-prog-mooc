package exercises.week6.forcomp

import exercises.week6.forcomp.Anagrams._
import org.scalatest.funsuite.AnyFunSuite

class AnagramsSuite extends AnyFunSuite {

  test("wordOccurrences: abcd") {
    assert(wordOccurrences("abcd") === List(('a', 1), ('b', 1), ('c', 1), ('d', 1)))
  }

  test("wordOccurrences: Robert") {
    assert(wordOccurrences("Robert") === List(('b', 1), ('e', 1), ('o', 1), ('r', 2), ('t', 1)))
  }

  test("sentenceOccurrences: abcd e") {
    assert(sentenceOccurrences(List("abcd", "e")) === List(('a', 1), ('b', 1), ('c', 1), ('d', 1), ('e', 1)))
  }

  test("sentenceOccurrences: Ro ber t") {
    assert(sentenceOccurrences(List("Ro", "ber", "t")) === List(('b', 1), ('e', 1), ('o', 1), ('r', 2), ('t', 1)))
  }

  test("dictionaryByOccurrences.get: eat") {
    assert(dictionaryByOccurrences.get(List(('a', 1), ('e', 1), ('t', 1))).map(_.toSet) === Some(Set("ate", "eat", "tea")))
  }

  test("dictionaryByOccurrences.get: bleat") {
    assert(dictionaryByOccurrences.get(List(('a', 1), ('b', 1), ('e', 1), ('l', 1), ('t', 1))).map(_.toSet) === Some(Set("bleat", "table")))
  }

  test("dictionaryByOccurrences.get: zzzzzz") {
    assert(dictionaryByOccurrences.get(List(('z', 6))).map(_.toSet) === None)
  }

  test("word anagrams: married") {
    assert(wordAnagrams("married").toSet === Set("married", "admirer"))
  }

  test("word anagrams: player") {
    assert(wordAnagrams("player").toSet === Set("parley", "pearly", "player", "replay"))
  }

  test("word anagrams: madeUpWord") {
    assert(wordAnagrams("madeUpWord").toSet === Set())
  }

  type subtractor = (Occurrences, Occurrences) => Occurrences

  val subtractors: List[subtractor] = List(subtract, subtract2, subtract3)

  test("subtract: lard - r") {
    val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
    val r = List(('r', 1))
    val lad = List(('a', 1), ('d', 1), ('l', 1))
    subtractors.foreach(f => assert(f(lard, r) === lad))
  }

  test("subtract: larder - r") {
    val larder = List(('a', 1), ('d', 1), ('e', 1), ('l', 1), ('r', 2))
    val r = List(('r', 1))
    val larde = List(('a', 1), ('d', 1), ('e', 1), ('l', 1), ('r', 1))
    subtractors.foreach(f => assert(f(larder, r) === larde))
  }

  test("subtract: lard - lard") {
    val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
    subtractors.foreach(f => assert(f(lard, lard) === List()))
  }

  test("subtract maintains order") {
    val Linux_rulez = List(('e', 1), ('i', 1), ('l', 2), ('n', 1), ('r', 1), ('u', 2), ('x', 1), ('z', 1))
    val rulez = List(('e', 1), ('l', 1), ('r', 1), ('u', 1), ('z', 1))
    val Linux = List(('i', 1), ('l', 1), ('n', 1), ('u', 1), ('x', 1))
    subtractors.foreach(f => assert(f(Linux_rulez, rulez) === Linux))
  }

  test("combinations: []") {
    assert(combinations(Nil) === List(Nil))
  }

  test("combinations: abba") {
    val abba = List(('a', 2), ('b', 2))
    val abbacomb = List(
      List(),
      List(('a', 1)),
      List(('a', 2)),
      List(('b', 1)),
      List(('a', 1), ('b', 1)),
      List(('a', 2), ('b', 1)),
      List(('b', 2)),
      List(('a', 1), ('b', 2)),
      List(('a', 2), ('b', 2))
    )
    assert(combinations(abba).toSet === abbacomb.toSet)
  }

  test("sentence anagrams: []") {
    val sentence = List()
    assert(sentenceAnagrams(sentence) === List(Nil))
  }

  test("sentence anagrams: Linux rulez") {
    val sentence = List("Linux", "rulez")
    val anas = List(
      List("Rex", "Lin", "Zulu"),
      List("nil", "Zulu", "Rex"),
      List("Rex", "nil", "Zulu"),
      List("Zulu", "Rex", "Lin"),
      List("null", "Uzi", "Rex"),
      List("Rex", "Zulu", "Lin"),
      List("Uzi", "null", "Rex"),
      List("Rex", "null", "Uzi"),
      List("null", "Rex", "Uzi"),
      List("Lin", "Rex", "Zulu"),
      List("nil", "Rex", "Zulu"),
      List("Rex", "Uzi", "null"),
      List("Rex", "Zulu", "nil"),
      List("Zulu", "Rex", "nil"),
      List("Zulu", "Lin", "Rex"),
      List("Lin", "Zulu", "Rex"),
      List("Uzi", "Rex", "null"),
      List("Zulu", "nil", "Rex"),
      List("rulez", "Linux"),
      List("Linux", "rulez")
    )
    assert(sentenceAnagrams(sentence).toSet === anas.toSet)
  }

  test("sentence anagrams: Yes man") {
    val sentence = List("Yes", "man")
    val anas = List(
      List("en", "as", "my"),
      List("en", "my", "as"),
      List("man", "yes"),
      List("men", "say"),
      List("as", "en", "my"),
      List("as", "my", "en"),
      List("sane", "my"),
      List("Sean", "my"),
      List("my", "en", "as"),
      List("my", "as", "en"),
      List("my", "sane"),
      List("my", "Sean"),
      List("say", "men"),
      List("yes", "man")
    )
    assert(sentenceAnagrams(sentence).toSet === anas.toSet)
  }
}