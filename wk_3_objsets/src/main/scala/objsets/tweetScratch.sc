import objsets._

val usr = "user"
val tw1 = new Tweet(usr, "A", 9)
val tw2 = new Tweet(usr, "B", 19)
val tw3 = new Tweet(usr, "C", 29)
val tweetSet = new Empty(0)
val tweetSet132 = tweetSet incl tw1 incl tw3 incl tw2
tweetSet132 remove tw1
tweetSet132 remove tw2
tweetSet132 remove tw3
def p: Tweet => Boolean = (tweet => tweet.retweets < 20)
def p: Tweet => Boolean = (_.retweets < 20)
tweetSet132 filter p
tweetSet132 filter {_.retweets > 9}
val tweetSet213 = tweetSet incl tw2 incl tw1 incl tw3
val tweetSet123 = tweetSet incl tw1 incl tw2 incl tw3
tweetSet incl tw1 remove tw1
tweetSet incl tw1 incl tw2 remove tw2
tweetSet incl tw1 incl tw2 remove tw1
val set1 = new Empty(0) incl new Tweet(usr, "A", 9) incl new Tweet(usr, "B", 19)
val set2 = new Empty(0) incl new Tweet(usr, "C", 29) incl new Tweet(usr, "D", 39)
set1 union set2
GoogleVsApple.trending foreach println