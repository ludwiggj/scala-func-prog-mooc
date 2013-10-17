/*
 * Created by IntelliJ IDEA.
 * User: 802940498
 * Date: 16/10/13
 * Time: 23:17
 */
package main.week3

trait Planar {
  def height: Int = 3
  def width:Int
  def surface = height * width
}

class Square extends Planar {
  def width = 6
}

abstract class Blob extends Planar