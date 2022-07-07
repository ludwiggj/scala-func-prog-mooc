import lectures.week4.objects_everywhere_2.Zero

object testNats {
    val One = Zero.successor
    val Two = Zero.successor.successor

    println("Zero=" + Zero)
    println("1=" + One)
    println("2=" + Two)
    println("0=" + One.predecessor)
    println("0+0=" + (Zero + Zero))
    println("0+2=" + (Zero + Two))
    println("2+0=" + (Two + Zero))
    println("2+1=" + (Two + One))
    println("0-0=" + (Zero - Zero))
    println("1-1=" + (One - One))
    println("2-1=" + (Two - One))
    println("0-1=" + (Zero - One))
}