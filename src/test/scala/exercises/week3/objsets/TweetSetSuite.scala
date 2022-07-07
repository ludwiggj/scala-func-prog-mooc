package exercises.week3.objsets

import org.scalatest.funsuite.AnyFunSuite

class TweetSetSuite extends AnyFunSuite {
  trait TestSets {
    val set1 = new Empty(0)
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }

  def asSet(tweets: TweetSet): Set[Tweet] = {
    var res = Set[Tweet]()
    tweets.foreach(res += _)
    res
  }

  def size(set: TweetSet): Int = asSet(set).size

  test("filter: on empty set") {
    new TestSets {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: retweeted 20 times on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("filter: retweets < 20 times on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.retweets < 20)) === 2)
    }
  }

  test("union: set4c and set4d") {
    new TestSets {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1)") {
    new TestSets {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2)") {
    new TestSets {
      assert(size(set1.union(set5)) === 4)
    }
  }


  test("mostRetweeted throws an exception on an empty set") {
    intercept[NoSuchElementException] {
      new Empty(0).mostRetweeted
    }
  }


  test("mostRetweeted on non-empty set with duplicates") {
    new TestSets {
      assert(set5.mostRetweeted.retweets === 20)
    }
  }

  test("mostRetweeted on non-empty set without duplicates") {
    new TestSets {
      assert(new Empty(0).incl(new Tweet("a", "a body", 12)).incl(new Tweet("b", "b body", 5)).mostRetweeted.retweets === 12)
    }
  }

  test("mostRetweeted on non-empty set without duplicates different order") {
    new TestSets {
      assert(new Empty(0).incl(new Tweet("a", "a body", 5)).incl(new Tweet("b", "b body", 12)).mostRetweeted.retweets === 12)
    }
  }

  test("mostRetweeted on non-empty set with a tricky order") {
    new TestSets {
      assert(new Empty(0)
          .incl(new Tweet("a", "3", 3)).incl(new Tweet("a", "2", 9))
          .incl(new Tweet("a", "1", 1)).incl(new Tweet("a", "4", 1))
          .incl(new Tweet("a", "5", 4))
          .mostRetweeted.retweets === 9)
    }
  }

  test("descending: set6") {
    new TestSets {
      val trends = set5.incl(new Tweet("z", "err", 13)).descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  test("descending: empty set is empty set") {
    new TestSets {
      val trends = set1.descendingByRetweet
      assert(trends.isEmpty)
    }
  }
}