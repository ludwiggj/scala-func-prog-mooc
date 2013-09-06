package week6

import scala.io.Source
import forcomp.Anagrams._

object mnem {
  val words = forcomp.loadDictionary filter (word => word forall (chr => chr.isLetter))
                                                  //> words  : List[String] = List(Aarhus, Aaron, Ababa, aback, abaft, abandon, ab
                                                  //| andoned, abandoning, abandonment, abandons, abase, abased, abasement, abasem
                                                  //| ents, abases, abash, abashed, abashes, abashing, abasing, abate, abated, aba
                                                  //| tement, abatements, abater, abates, abating, Abba, abbe, abbey, abbeys, abbo
                                                  //| t, abbots, Abbott, abbreviate, abbreviated, abbreviates, abbreviating, abbre
                                                  //| viation, abbreviations, Abby, abdomen, abdomens, abdominal, abduct, abducted
                                                  //| , abduction, abductions, abductor, abductors, abducts, Abe, abed, Abel, Abel
                                                  //| ian, Abelson, Aberdeen, Abernathy, aberrant, aberration, aberrations, abet, 
                                                  //| abets, abetted, abetter, abetting, abeyance, abhor, abhorred, abhorrent, abh
                                                  //| orrer, abhorring, abhors, abide, abided, abides, abiding, Abidjan, Abigail, 
                                                  //| Abilene, abilities, ability, abject, abjection, abjections, abjectly, abject
                                                  //| ness, abjure, abjured, abjures, abjuring, ablate, ablated, ablates, ablating
                                                  //| , ablation, ablative, ab
                                                  //| Output exceeds cutoff limit.
  
  val mnem = Map(
    '2' -> "ABC", '3' -> "DEF", '4' -> "GHI",'5' -> "JKL",
    '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")
                                                  //> mnem  : scala.collection.immutable.Map[Char,String] = Map(8 -> TUV, 4 -> GHI
                                                  //| , 9 -> WXYZ, 5 -> JKL, 6 -> MNO, 2 -> ABC, 7 -> PQRS, 3 -> DEF)
  val charCode: Map[Char, Char] =
  for {
    (digit, str) <- mnem
    ltr <- str
  } yield (ltr -> digit)                          //> charCode  : Map[Char,Char] = Map(E -> 3, X -> 9, N -> 6, T -> 8, Y -> 9, J -
                                                  //| > 5, U -> 8, F -> 3, A -> 2, M -> 6, I -> 4, G -> 4, V -> 8, Q -> 7, L -> 5,
                                                  //|  B -> 2, P -> 7, C -> 2, H -> 4, W -> 9, K -> 5, R -> 7, O -> 6, D -> 3, Z -
                                                  //| > 9, S -> 7)
  
  def wordCode(word: String): String = {
    word.toUpperCase map charCode
  }                                               //> wordCode: (word: String)String
  
  val wordsForNum: Map[String, Seq[String]] = words groupBy wordCode
                                                  //> wordsForNum  : Map[String,Seq[String]] = Map(63972278 -> List(newscast), 292
                                                  //| 37638427 -> List(cybernetics), 782754448 -> List(starlight), 2559464 -> List
                                                  //| (allying), 862532733 -> List(uncleared), 365692259 -> List(enjoyably), 86843
                                                  //| 7 -> List(unties), 33767833 -> List(deportee), 742533 -> List(picked), 33646
                                                  //| 46489 -> List(femininity), 3987267346279 -> List(extraordinary), 7855397 -> 
                                                  //| List(pulleys), 67846493 -> List(optimize), 4723837 -> List(grafter), 386583 
                                                  //| -> List(evolve), 78475464 -> List(Stirling), 746459 -> List(singly), 847827 
                                                  //| -> List(vistas), 546637737 -> List(lionesses), 28754283 -> List(curlicue), 8
                                                  //| 4863372658 -> List(thunderbolt), 46767833 -> List(imported), 26437464 -> Lis
                                                  //| t(angering, cohering), 8872267 -> List(turbans), 77665377 -> List(spoolers),
                                                  //|  46636233 -> List(homemade), 7446768759 -> List(rigorously), 74644647 -> Lis
                                                  //| t(ringings), 633738 -> List(offset), 847825 -> List(visual), 772832 -> List(
                                                  //| Pravda), 4729378 -> List
                                                  //| Output exceeds cutoff limit.
  
  def encode(number: String): Set[List[String]] = {
  
    def xproduct (xx: List [List[String]]) : List [List[String]] =
      xx match {
        case aa :: bb :: Nil =>
          aa.map (a => bb.map (b => List (a, b))).flatten
        case aa :: bb :: cc =>
          xproduct (bb :: cc).map (li => aa.map (a => a :: li)).flatten
        case _ => xx
    }
    
    def getStringRep(list: List[String]): String = list.reduceLeft(_ + _)
    
    def isSubString(list: List[String]):Boolean = (number indexOf getStringRep(list.reverse)) == 0
    
    val keys = wordsForNum.keys
    
    def doEncode(encodedLists: Set[List[String]], acc: Set[List[String]]): Set[List[String]] = {
       val newEncodedLists = (for {
                                  list <- encodedLists
                                  key <- keys
                                 } yield ((key :: list))).filter (x => (isSubString(x)))
       val (completed, candidates) = newEncodedLists partition (getStringRep(_).size == number.size)
       if (candidates isEmpty) (acc ++ (completed map (_.reverse))) else doEncode(candidates, (acc ++ (completed map (_.reverse))))
    }
    
    val encodedSequences = doEncode(Set(List()), Set(List())).filter(_.size > 0)
    val step1 = for {
      encodedSequence <- encodedSequences
    } yield (encodedSequence map (num => wordsForNum(num)))
    val step2: Set[List[List[String]]] = step1.map(list => list.map(_.toList))
    val result = (step2 map (xproduct(_))).flatten.toSet

    result
  }                                               //> encode: (number: String)Set[List[String]]
  
  def translate(number: String): Set[String] =
    encode(number) map (_ mkString " ")           //> translate: (number: String)Set[String]
    
  encode("7225247386")                            //> res0: Set[List[String]] = Set(List(rack, ah, re, to), List(sack, ah, re, to
                                                  //| ), List(Scala, ire, to), List(sack, air, fun), List(rack, air, fun), List(r
                                                  //| ack, bird, to), List(pack, air, fun), List(pack, ah, re, to), List(pack, bi
                                                  //| rd, to), List(Scala, is, fun), List(sack, bird, to))
  translate("7225247386")                         //> res1: Set[String] = Set(sack air fun, pack ah re to, pack bird to, Scala ir
                                                  //| e to, Scala is fun, rack ah re to, pack air fun, sack bird to, rack bird to
                                                  //| , sack ah re to, rack air fun)
 /*
  Set(List(rack, ah, re, to),
      List(sack, ah, re, to),
      List(Scala, ire, to),
      List(sack, air, fun),
      List(rack, air, fun),
      List(rack, bird, to),
      List(pack, air, fun),
      List(pack, ah, re, to),
      List(pack, bird, to),
      List(Scala, is, fun),
      List(sack, bird, to)
     )
*/
}

object otherVersions {
  val mnem = Map(
    '2' -> "ABC", '3' -> "DEF", '4' -> "GHI",'5' -> "JKL",
    '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")

  ("ABC" map (ch => (ch, 'a'))).toMap

  val charCode1: Map[Char, Char] = {
    val x = (for {
      (digit, str) <- (mnem)
    } yield ((str map (letter => (letter, digit))).toMap))
    x.reduceLeft(_ ++ _)
  }
  val charCode2: Map[Char, Char] =
    mnem flatMap (entry => (entry._2) map (letter => (letter, entry._1)))
    
  def wordCode(word: String): String = {
    word map (x => charCode1(x toUpper))
  }
  
  //def finished(set: Set[List[String]]): Boolean = set.forall(getStringRep(_).size == number.size)
  
  /*
  def encode1(number: String): Set[List[String]] = {
    val target = "7225247386"
    //val target = "528267812"
    val keys = wordsForNum.keys
    val initial = keys filter (x => (target indexOf x) == 0)
    println("initial " + initial)
    println("keys size " + keys.size)
    val x = for (seq <- initial) yield ((keys map (seq :: _ :: List[String]())) filter (x => (target indexOf getStringRep(x)) == 0))
           
    println ("next step " + x.flatten)
    println ("finished? " + x.flatten.forall(getStringRep(_).size == target.size))
    Set(List())
  }
*/

  // "5282" -> List("Java", "Kata", "Lava", ...)
  // Missing number should map to empty set
  
  /*
  def wordsForNum1(num: String): Map[String, Seq[String]] = {
    words groupBy wordCode filter (_._1 == num)
  }
  
  wordCode("Java")
  wordsForNum1("2838266")
  wordsForNum1("5282")
  */
  
  def xproduct (xx: List [List[_]]) : List [List[_]] =
  xx match {
    case aa :: bb :: Nil =>
      aa.map (a => bb.map (b => List (a, b))).flatten
    case aa :: bb :: cc =>
      xproduct (bb :: cc).map (li => aa.map (a => a :: li)).flatten
    case _ => xx
  }
  
  xproduct (List (List ("a ", "b ", "c "), List ("x", "y")))
  
  println(xproduct(List(List("Scala"), List("ire"), List("to"))))
  println(xproduct(List(List("pack", "rack", "sack"), List("air"), List("fun"))))
  /*
    val z = for {
      encodedSequence <- encodedSequences
      wordCode <- encodedSequence
      wordz <- wordsForNum(wordCode)
    } yield ((wordz + " <END>"))*/
  /*
    println ("zz " + zz.toList)
    println ("zz " + zz.toList(0))
    
    println("hey " + xproduct(zz.toList(0).map(_.toList)))
    */
    
    /*
    zz Set(List(List(Scala), List(ire), List(to)),
           List(List(Scala), List(is), List(fun)),
           List(List(pack, rack, sack), List(air), List(fun)),
           List(List(pack, rack, sack), List(ah), List(re), List(to)),
           List(List(pack, rack, sack), List(bird), List(to)))
           */
    
    
    
    // res3: Set[List[String]] = Set(List(72252, 47, 386), List(7225, 24, 73, 86),
    // List(7225, 2473, 86), List(), List(72252, 473, 86), List(7225, 247, 386))
    // wordsForNum("72252")
}