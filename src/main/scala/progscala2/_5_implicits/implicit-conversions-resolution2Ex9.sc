// src/main/scala/progscala2/implicits/implicit-conversions-resolution2.sc
import scala.language.implicitConversions

// 암시적 값이나 변환을 동반 객체에 선언하지 않은 경우, implicits 이름 패키지나 Implicits 객체에 넣는 것이 권장 관례다.

// 스칼라는 String, Array 같은 자바 타입에 대한 암시적 래퍼 타입을 몇 가지 제공한다.
// ex> "String".reverse 는 WrappedString 에 정의가 들어있다.

case class Foo(s: String)
object Foo {
  implicit def fromString(s: String): Foo = Foo(s)
}

// 하지만 다른 Foo 변환이 범위에 있다면, 그 변환이 Foo.fromString 보다 우선 실행된다.
implicit def overridingConversion(s: String): Foo = Foo("Boo: "+s)

class O {
  def m1(foo: Foo) = println(foo)
  def m(s: String) = m1(s)
}
