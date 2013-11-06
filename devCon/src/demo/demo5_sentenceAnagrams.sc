package demo

import demo.Anagrams._

object demo5_sentenceAnagrams {
  // sentence anagrams
  sentenceAnagrams(List("Ernie", "is", "evil"))

  sentenceAnagrams(List("Ernie", "is", "evil"))
                   .filter(_.contains("reel"))

  sentenceAnagrams(List("Ernie", "is", "evil"))
                   .filter(_.forall(_.length() >= 4))
}