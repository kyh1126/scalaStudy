// src/main/scala/progscala2/introscala/shapes/shapes.scala
package progscala2._1_introscala.shapes

// 스칼라를 고려하는 이유 중 하나: 여러 CPU 코어나 클러스터에 속한 여러 서버 사이에서 병렬성을 활용할 수 있도록 애플리케이션을 확장하는 더 나은 방법 제시.

// 동시성 액터 모델(Actor Model of Concurrency)
// 서로 아무런 상태 정보를 공유하지 않는 액터 라는 독립적인 sw 요소가 존재.
// 상태를 공유하지 않는 대신, 액터들은 메시지 교환을 통해 서로 의사소통. 액터 모델을 사용하면 공유한 변경 가능 상태에 대해 동기화해야 할 필요성을 없앨 수
// 있기 때문에 튼튼한 동시성 애플리케이션을 훨씬 쉽게 작성 가능.
// 액터는 필요에 따라 자신의 상태를 바꿀 수 있지만, 각 액터가 베타적으로 자신의 상태에 대해서만 접근할 수 있고, 액터 프레임워크가 각 액터의 코드를 스레드
// 안전하게 실행해주기 때문에 프로그래머가 번거롭고 오류 생기기 쉬운 동기화를 사용할 필요가 없다.

// case 클래스
// 1. 클래스 선언 앞에 case 를 넣으면 각각의 생성자 매개변수가 자동으로 클래스 인스턴스의 읽기 전용 필드로 바뀐다. 인스턴스 초기화 후 각 필드를
//    읽을 수 있지만 그 값을 바꿀 수는 없다.
// 2. 컴파일러가 자동으로 toString, equals, hashCode 를 만들어준다.
//    == 를 REPL 질의하면 스칼라는 equals 메서드 호출. 이 부분은 == 가 참조만 비교하는 자바와 다르다.(자바는 논리적 비교를 위해 equals 명시적 호출)
// 3. 컴파일러가 모든 케이스 클래스에 대해 각 클래스와 이름이 같은 싱글턴 객체인 동반 객체를 자동으로 만들어낸다. (object Point)

// object, class 이름이 같고, 그 둘이 모두 같은 파일 안에 정의된 경우, 이 둘은 각각 상대방의 동반 객체, 동반 클래스다.

// 동반 객체: 동반 객체엔 메서드를 추가할 수도 있고, 자동 추가되는 메서드도 있다. 그 중 하나가 apply.
//          ex>
//          object Point {
//            def apply(x: Double = 0.0, y: Double = 0.0) = new Point(x, y)
//            ...
//          }

// apply: 동반 클래스의 생성자와 같은 인자를 받는다. 객체 뒤에 인자 목록을 덧붙이면 스칼라는 그 객체의 apply 호출하기 위해 찾는다.
//        해당 apply 없으면 컴파일 오류 발생. ex> Point.apply(1, 2) ==  Point(1, 2)
//        Point.apply 는 사실상 Point 를 만드는 팩토리다. new 키워드 없이 생성자를 호출하는 것과 같다.

// 추상 클래스: 자바와 마찬가지로 인스턴스화할 수 없다.
// Unit: 실제 타입이지만 자바의 void 와 거의 비슷하게 작동한다. 함수형 프로그래밍에서 이런 타입에 관례적으로 사용하는 이름이다.
//       어떤 함수가 Unit 을 반환하는 경우, 이 함수는 오직 부수 효과만을 위한 것이다. Unit 으로 할 수 있는 유용한 작업이 전혀 없는 만큼 그런 함수는
//       오직 특정 상태에 대한 부수 효과만 수행한다.(입출력, 지역적으로 어떤 객체 변경 등)

// 함수도 1급 계층값(first-class value)
// Shape.draw 가 String, Int, Point, 다른 객체와 같이 변수에 대입하거나, 다른 함수에 함수를 인자로 전달하거나, 함수에서 함수를 반환할 수 있다.

// 고차 함수(high-order function, HOF): 어떤 함수가 다른 함수를 값으로 반환하거나 인자로 받는 경우.

// <3>
// draw 는 필요에 따라 변경할 수 있는 프로토콜을 정의한다. toString 메서드로 자신의 상태 문자열 표현으로 직렬화하는 것은 각 모양이 결정.
// draw 는 f 메서드 호출, 스칼라 2.10 부터 문자열 인터폴레이션(interpolation) 등장. 인터폴레이션 문자열 앞에 s 입력해 ${} 내부 채워 넣는다.

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

// test (sbt shell) >
// import progscala2._1_introscala.shapes._
// val p00 = new Point
// val p20 = new Point(2,0)
// val p20b = new Point(2,0)
// val p02 = new Point(y=2)
//
//scala> p00 == p20
//res0: Boolean = false
//
//scala> p20 == p20b
//res1: Boolean = true
