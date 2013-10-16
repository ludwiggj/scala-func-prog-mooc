import funsets.FunSets
type Set = Int => Boolean
def contains(elem: Int)(s: Set): Boolean = s(elem)
def positive: Set = _ > 0
def divisibleBy17: Set = _ % 17 == 0
contains(-3)(positive)
contains(3)(positive)
contains(3) {_ < 0}
contains(-3) {_ < 0}
FunSets.printSet(divisibleBy17)





