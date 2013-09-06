package week3

object traits {
  println (new Square().surface)                  //> 18
  // val z: Int = null
  if (true) 1 else false                          //> res0: AnyVal = 1
  if (true) 1 else 1.0F                           //> res1: Float = 1.0
  if (true) 'd' else 2.9                          //> res2: Double = 100.0
}

trait Planar {
  def height: Int = 3
  def width:Int
  def surface = height * width
}

class Square extends Planar {
  def width = 6
}