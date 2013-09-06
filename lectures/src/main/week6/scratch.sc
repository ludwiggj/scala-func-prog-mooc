package week6

object scratch {

// : List[(Char, Int)]

   def wordOccurrences(w: String) = {
     val lowerCaseW = w.toLowerCase
     //(lowerCaseW groupBy (ch => lowerCaseW count(_ == ch))) map ((_._2.toSet))
     val countMap = (lowerCaseW groupBy (ch => lowerCaseW count(_ == ch))) mapValues (_.toSet.toList)
     (for {
       (count, chars) <- countMap
       ch <- chars
     } yield ((ch, count))).toList.sorted
   }
   
   wordOccurrences("Hello Bonzo")
   
   Map('a' -> 1, 'b' -> 2) mapValues(_ * 2)
   
   wordOccurrences (List("Ro", "ber", "t") mkString)
   
   type Occurrences = List[(Char, Int)]
   
   def combinations(occurrences: Occurrences): List[Occurrences] = {
     def findCombis(occurrences: Occurrences): List[Occurrences] = {
       if (occurrences isEmpty) List(List())
       else {
         val (headCh, headCount) = (occurrences head)
         val theRest: List[Occurrences] = findCombis(occurrences tail)
         (for {
             chosenCount <- 0 to headCount
             tailSolution <- theRest
           } yield ((headCh, chosenCount) :: tailSolution)
         ).toList
       }
     }
     findCombis(occurrences).map(_.filter(_._2 != 0))
   }
                                                  
   combinations(List(('a', 2), ('b', 2)))
    
   combinations(List())
    
    
   def subtract(x: Occurrences, y: Occurrences): Occurrences = {
     val xMap = x.toMap
     val yMap = y.toMap
     
     def updateMapEntry(entry: (Char, Int)): (Char, Int) = {
       val (ch, count) = entry
       (ch, count - (yMap getOrElse(ch, 0)))
     }
     
     (xMap map (updateMapEntry(_))).toList.filter(_._2 > 0)
   }
   
   val x = List(('a', 1), ('d', 4), ('l', 1), ('r', 1))
   val y = List(('d', 2),('r', 1))
   
   subtract(x, y)
    
    
    Map(('a',1), ('b',2)) ++ Map(('a', 2),('c', 3))
   //foldLeft, -, apply and updated
    
             
             
             
             /*
             
             def combinations(occurrences: Occurrences): List[Occurrences] = {
     def findCombis(occurrences: Occurrences): List[Occurrences] = {
       if (occurrences isEmpty) List(List())
       else {
         println("The Head: " + occurrences.head)
         println("The Tail: " + occurrences.tail)
         val (headCh, headCount) = (occurrences head)
         val theRest: List[Occurrences] = findCombis(occurrences tail)
         //println("theRest: " + theRest)
         
         //val theRest: List[Occurrences] = List(List(('t', 0), ('s', 1)), List(('t', 0), ('s', 2)))
         //val theRest: List[Occurrences] = List(List())
         
         //(
         val z =
         (for {
           chosenCount <- 0 to headCount
           //rest <- findCombis(occurrences tail)
           tailSolution <- theRest
         } yield (headCh, chosenCount) :: tailSolution)
         //println("This loop's for: " + z)
         //println("Tail: " + theRest)
         val zz: List[Occurrences] = z.toList
         println("zz: " + zz)
         //println("zz flatten: " + zz.flatten)
         //zz
         //List(List())
         zz
       }
     }
     findCombis(occurrences)
   }
             
             
             
             
             
         val zz = findCombis(occurrences tail) :: List(findCombis(occurrences tail))
         //:: findCombis(occurrences tail)
         println("z (HeadCh: " + headCh + "): " + z)
         println("zz (Tail): " + zz)
         
         
         val zzz =
         (for {
           z1 <- z
           zz1 <- zz
         } yield (z1 :: List(zz1))
         ).toList
         println("zzz: " + zzz)
         //List(List())
         List()
         //(z.toList)
         */
         
    /*
    List(
       for {
        (ch, count) <- occurrences
        
        chosenCount <- 0 to count
        // rest <- encode(number drop split)
        } yield (ch, chosenCount)
       )*/
       
       /*
        def combinations(occurrences: Occurrences): List[(Char, Int)] = {
     if (occurrences isEmpty) (List())
     else {
       // Something else
       List(List(('z', 20)))
       //val headCount = (occurrences head)._2
       //val headCh = (occurrences head)._1
       val (headCh, headCount) = (occurrences head)
       (
       for {
        chosenCount <- 0 to headCount
        // rest <- encode(number drop split)
        } yield (headCh, chosenCount)
       ).toList
       
     }
   }  */
   
     /*
   *
   * def combinations(occurrences: Occurrences): List[Occurrences] = {
     def findCombis(occurrences: Occurrences): List[Occurrences] = {
       if (occurrences isEmpty) List(List())
       else {
         val (headCh, headCount) = (occurrences head)
         val theRest: List[Occurrences] = findCombis(occurrences tail)
         (for {
             chosenCount <- 0 to headCount
             tailSolution <- theRest
           } yield ((headCh, chosenCount) :: tailSolution)
         ).toList
       }
     }
     findCombis(occurrences).map(_.filter(_._2 != 0))
   }
   *
   *
   *
   * def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
    if (sentence isEmpty) {
      List(List())
    } else {
    val z =
    for {
      combination <- combinations(sentenceOccurrences(sentence))
      wordList    <- dictionaryByOccurrences(combination)
    } yield (combination, wordList)
    
    println(combinations(sentenceOccurrences(sentence)))
    println(z)
    List()
    }
  }
   *
   
   
   def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
    if (sentence isEmpty) {
      List(Nil)
    } else {
      def findAnagram(occurrences: Occurrences, iter: Int): List[Sentence] = {
        println("iter: " + iter + " occurrences: " + occurrences)
        if (occurrences isEmpty) {
          List(Nil)
        } else {
        val candidateWords: List[Sentence] =
        for {
          combination <- combinations(occurrences)
          word        <- dictionaryByOccurrences(combination);
          sentences = findAnagram(subtract(occurrences, wordOccurrences(word)),iter + 1);
          //println("sentences: " + sentences)
          sentence    <- sentences
          //sentence = List("I","am","stuck")
          //sentence = List()
          
        } yield (word :: sentence)
        
        //println(combinations(sentenceOccurrences(sentence)))
        println("iter: " + iter + " candidateWords " + candidateWords)
        //List()
        candidateWords
        }
      }
      val res = findAnagram(sentenceOccurrences(sentence), 0)
      println("res: " + res)
      res
    }
    */
  }
}