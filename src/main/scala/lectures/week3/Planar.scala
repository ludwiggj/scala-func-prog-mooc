package lectures.week3

trait Planar {
  def height: Int = 3
  def width:Int
  def surface = height * width
}

class Square extends Planar {
  def width = 6
}

abstract class Blob extends Planar