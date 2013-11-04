// Initial load
import demo.Anagrams._

// The sentence we shall work on is...
"Ernie is evil"

// Word occurrencies
"Ernie".toLowerCase groupBy ((ch: Char) => ch)
wordOccurrences("Ernie")

// Sentence occurrences
sentenceOccurrences(List("Ernie", "is", "evil"))

// dictionaryByOccurrences
dictionaryByOccurrences

// AN ASIDE... wordAnagrams
wordAnagrams("evil")
wordAnagrams("ernie")
wordAnagrams("zzz")

// combinations of occurrences
combinations(wordOccurrences("is"))
combinations(wordOccurrences("Ernie"))

// subtract
sentenceOccurrences(List("Ernie", "is", "evil"))
wordOccurrences("Ernie")
subtract(sentenceOccurrences(List("Ernie", "is", "evil")), wordOccurrences("Ernie"))

// sentence anagrams
sentenceAnagrams(List("Ernie", "is", "evil")).filter(_.contains("reel"))
