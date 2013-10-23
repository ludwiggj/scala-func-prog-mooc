import main.week4.covariance._

val a: Array[NonEmpty] = Array(new NonEmpty)

/* Following line gives error:
<console>:11: error: type mismatch;
found   : Array[main.week4.covariance.NonEmpty]
required: Array[main.week4.covariance.IntSet]
Note: main.week4.covariance.NonEmpty <: main.week4.covariance.IntSet, but class
Array is invariant in type T.
You may wish to investigate a wildcard type such as `_ <: main.week4.covariance.
IntSet`. (SLS 3.2.10)
*/

val b: Array[IntSet] = a







