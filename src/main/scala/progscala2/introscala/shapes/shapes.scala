// src/main/scala/progscala2/introscala/shapes/shapes.scala
package progscala2.introscala.shapes

case class Point(x: Double = 0.0, y: Double = 0.0)                   // <1>

abstract class Shape() {                                             // <2>
  /**
   * draw는 함수를 인자로 받는다. 각 함수는 '그리기'를 담당한다.
   * 각 Shape는 자신을 문자열로 바꾼 결과를 그 함수에 전달할 것이다.
   */
  def draw(f: String => Unit): Unit = f(s"draw: ${this.toString}")   // <3>
}

case class Circle(center: Point, radius: Double) extends Shape       // <4>

case class Rectangle(lowerLeft: Point, height: Double, width: Double) // <5>
      extends Shape

case class Triangle(point1: Point, point2: Point, point3: Point)     // <6>
      extends Shape
