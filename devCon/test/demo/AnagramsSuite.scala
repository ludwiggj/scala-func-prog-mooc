package demo

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import demo.Anagrams._

@RunWith(classOf[JUnitRunner])
class AnagramsSuite extends FunSuite {

  trait TestData {
    val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
    val r = List(('r', 1))
    val lad = List(('a', 1), ('d', 1), ('l', 1))
    val larder = List(('a', 1), ('d', 1), ('e', 1), ('l', 1), ('r', 2))
    val larde = List(('a', 1), ('d', 1), ('e', 1), ('l', 1), ('r', 1))
    val Linux_rulez = List(('e', 1), ('i', 1), ('l', 2), ('n', 1), ('r', 1), ('u', 2), ('x', 1), ('z', 1))
    val rulez = List(('e', 1), ('l', 1), ('r', 1), ('u', 1), ('z', 1))
    val Linux = List(('i', 1), ('l', 1), ('n', 1), ('u', 1), ('x', 1))
  }

  test("wordOccurrences: abcd") {
    new TestData {
      assert(wordOccurrences("abcd") === List(('a', 1), ('b', 1), ('c', 1), ('d', 1)))
    }
  }

  test("wordOccurrences: Robert") {
      new TestData {
        assert(wordOccurrences("Robert") === List(('b', 1), ('e', 1), ('o', 1), ('r', 2), ('t', 1)))
      }
    }

  test("sentenceOccurrences: abcd e") {
    new TestData {
      assert(sentenceOccurrences(List("abcd", "e"))
        === List(('a', 1), ('b', 1), ('c', 1), ('d', 1), ('e', 1)))
    }
  }

  test("sentenceOccurrences: Ro ber t") {
    new TestData {
      assert(sentenceOccurrences(List("Ro", "ber", "t"))
        === List(('b', 1), ('e', 1), ('o', 1), ('r', 2), ('t', 1)))
    }
  }

  test("sentenceOccurrences: [Empty List]") {
    new TestData {
      assert(sentenceOccurrences(List()) === List())
    }
  }

  test("dictionaryByOccurrences.get: eat") {
    new TestData {
      assert(dictionaryByOccurrences.get(List(('a', 1), ('e', 1), ('t', 1))).map(_.toSet)
        === Some(Set("ate", "eat", "tea")))
    }
  }

  test("dictionaryByOccurrences.get: bleat") {
    new TestData {
      assert(dictionaryByOccurrences.get(List(('a', 1), ('b', 1), ('e', 1), ('l', 1), ('t', 1))).map(_.toSet)
        === Some(Set("bleat", "table")))
    }
  }

  test("dictionaryByOccurrences.get: zzzzzz") {
    new TestData {
      assert(dictionaryByOccurrences.get(List(('z', 6))).map(_.toSet) === None)
    }
  }

  test("word anagrams: married") {
    new TestData {
      assert(wordAnagrams("married").toSet === Set("married", "admirer"))
    }
  }

  test("word anagrams: player") {
    new TestData {
      assert(wordAnagrams("player").toSet === Set("parley", "pearly", "player", "replay"))
    }
  }

  test("word anagrams: madeUpWord") {
    new TestData {
      assert(wordAnagrams("madeUpWord").toSet === Set())
    }
  }

  test("subtract: lard - r") {
    new TestData {
      assert(subtract(lard, r) === lad)
    }
  }

  test("subtract: larder - r") {
    new TestData {
      assert(subtract(larder, r) === larde)
    }
  }

  test("subtract: lard - lard") {
    new TestData {
      assert(subtract(lard, lard) === List())
    }
  }

  test("subtract maintains order") {
    new TestData {
      assert(subtract(Linux_rulez, rulez) === Linux)
    }
  }

  test("ernie is evil") {
    val ernieIsEvil = List(('e', 3), ('i', 3), ('l', 1), ('n', 1), ('r', 1), ('s', 1), ('v', 1))
    val ernie = List(('e', 2), ('i', 1), ('n', 1), ('r', 1))
    assert(subtract(ernieIsEvil, ernie) === List(('e', 1), ('i', 2), ('l', 1), ('s', 1), ('v', 1)))
  }

  test("combinations: []") {
    new TestData {
      assert(combinations(Nil) === List(Nil))
    }
  }

  test("combinations: abba") {
    new TestData {
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
  }

  test("sentence anagrams: []") {
    new TestData {
      val sentence = List()
      assert(sentenceAnagrams(sentence) === List(Nil))
    }
  }

  test("sentence anagrams: Linux rulez") {
    new TestData {
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
  }

  test("sentence anagrams: Yes man") {
    new TestData {
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
}