// src/main/scala/progscala2/typelessdomore/shapes/shapes.scala
package progscala2._2_typelessdomore.shapes

// 메서드의 기본 인자와 이름 붙은 인자(named argument)
// 이름 붙은 인자를 사용하면 메서드를 사용하는 코드를 더 읽기 쉽게 만들 수 있다. 또한 인자 목록이 길고, 같은 타입 필드가 여럿 있는 경우 버그 방지 용이.
case class Point(x: Double = 0.0, y: Double = 0.0) {
  // 위치 조정한 새 Point 를 만든다.
  def shift(deltax: Double = 0.0, deltay: Double = 0.0) =
    copy(x + deltax, y + deltay) // 케이스 클래스의 기존 인스턴스를 복사하면서 일부 필드를 변경해서 새로운 객체를 만들 수 있다.
}
// test (sbt shell) >
// import progscala2._2_typelessdomore.shapes._
// val p1 = new Point (x = 3.3, y = 4.4)
// val p2 = p1.copy (y = 6.4)

// 인자 목록이 여럿 있는 메서드
// 원하는 만큼 인자 목록을 추가할 수 있으나 둘 이상 사용하는 경우는 드물다.
abstract class Shape() {
  /**
   * 두 인자 목록을 받는다.
   * 한 인자 목록은 그림을 그릴 때, x, y 축 방향으로 이동시킬 오프셋 값이고,
   * 나머지 인자 목록은 앞에서 봤던 함수 인자다.
   */
  def draw(offset: Point = Point(0.0, 0.0))(f: String => Unit): Unit =
    f(s"draw(offset = $offset), ${this.toString}")
}

case class Circle(center: Point, radius: Double) extends Shape

case class Rectangle(lowerLeft: Point, height: Double, width: Double)
  extends Shape
// test (sbt shell) >
// var s = Circle(Point(1.0, 1.0), 3.14)
// s.draw(Point(1.0, 2.0))(str => println(s"Actor: $str"))

// Currying
// 1. 블록 구조 구문을 사용할 수 있다.
// s.draw(Point(1.0, 2.0)){str => println(s"Actor: $str")}
// s.draw(){str => println(s"Actor: $str")}   // offset 기본값을 사용하더라도 첫 인자 목록의 괄호를 지정해줘야 한다.
// cf> 한 인자 목록 안에서 두 인자 받는 경우
// s.draw(Point(1.0, 2.0), str => println(s"Actor: $str"))
// s.draw(f = str => println(s"Actor: $str")) // offset 기본값을 사용하더라도 그리 편하지 않다.

// 2. 어떤 인자 목록의 다음에 위치한 인자 목록에 관해 타입 추론이 가능해진다.
// def m1[A](a: A, f: A => String) = f(a)     // Int => String 함수 인자 전달하며 호출.
// def m2[A](a: A)(f: A => String) = f(a)
// m1(100, i => s"$i + $i")                   // 함수 인자 i의 타입을 추론하지 못한다.
// m2(100)(i => s"$i + $i")

// 3. 마지막 인자 목록을 암시적 인자(implicit argument)를 위해 사용할 수 있다.
//    암시적 인자는 implicit 키워드를 사용해 선언한 인자. 메서드를 호출할 때 이런 인자에 명시적으로 인자를 지정하거나,
//    컴파일러가 영역 안에서 적절한 값을 찾아서 채워 넣도록 할 수 있다. 암시적 인자는 기본값이 있는 인자보다 더 유연하다.
/**
 * @see progscala2._2_typelessdomore.futuresEx6
 */