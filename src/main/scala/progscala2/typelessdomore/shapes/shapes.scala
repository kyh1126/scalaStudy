// src/main/scala/progscala2/typelessdomore/shapes/shapes.scala
package progscala2.typelessdomore.shapes

case class Point(x: Double = 0.0, y: Double = 0.0) {

  def shift(deltax: Double = 0.0, deltay: Double = 0.0) =
    copy (x + deltax, y + deltay)
}

abstract class Shape() {
  /**
   * 두 인자 목록을 받는다.
   * 한 인자 목록은 그림을 그릴 때, x, y 축 방향으로 이동시킬 오프셋 값이고,
   * 나머지 이자 목록은 앞에서 봤던 일자다.
   */
  def draw(offset: Point = Point(0.0, 0.0))(f: String => Unit): Unit =
    f(s"draw(offset = $offset), ${this.toString}")
}

case class Circle(center: Point, radius: Double) extends Shape

case class Rectangle(lowerLeft: Point, height: Double, width: Double)
  extends Shape
