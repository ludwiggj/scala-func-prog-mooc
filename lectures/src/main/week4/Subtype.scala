package week4.subtypes

//class IntSet {}
//class NonEmpty {}

abstract class Subtype [IntSet, NonEmpty <: IntSet] {
  var nel:List[NonEmpty]
  var isl:List[IntSet]
  
  // Lists support co-variance
  isl = nel
  
  var nea:Array[NonEmpty]
  var isa:Array[IntSet]
  
  // Arrays do not support co-variance - following does not compile
//  isa = nea
}