package main.week4.covariance

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

  // Generates following compile error...
  /*
  type mismatch;
   found   : Array[NonEmpty]
   required: Array[IntSet]
  Note: NonEmpty <: IntSet, but class Array is invariant in type T.
  You may wish to investigate a wildcard type such as `_ <: IntSet`. (SLS 3.2.10)
    isa = nea
          ^
  */
}