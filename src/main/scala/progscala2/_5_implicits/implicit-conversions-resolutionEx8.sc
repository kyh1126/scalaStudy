// src/main/scala/progscala2/implicits/implicit-conversions-resolution.sc
import scala.language.implicitConversions

// 암시적 변환
// Map("a" -> 1) 에서 -> 는 튜플을 위한 리터럴 구문이 아니고 Predef 에 정의된 -> 래퍼 객체를 사용하는 것이다.
// Map(new ArrowAssoc("a") -> 1) 도 가능. 물론, Map(("a", 1)) 보다 나쁘다.
// 암시적 변환의 고려 대상이 되기 위해서는 반드시 implicit 키워드와 함께 선언되어야 하며, 반드시 인자를 하나만 받는 생성자가 있는 클래스거나 인자를 단
// 하나만 받는 메서드여야 한다.

// 컴파일러가 적용 가능한 암시적 변환 메서드를 찾을 때 사용하는 검색 규칙
// 1. 객체와 메서드의 조합이 성공적으로 타입 검사를 통과하는 경우 타입 변환을 시도하지 않는다.
// 2. implicit 키워드가 앞에 붙은 클래스와 메서드만 고려한다.
// 3. 현재 범위 안에 있는 암시적 클래스와 메서드만 고려한다. 또한 대상 타입의 동반 객체 안에 정의된 암시적 메서드도 고려시 포함시킨다.
// 4. 암시 메서드를 둘 이상 조합해서, 한 타입을 다른 중간 타입으로 변환한 다음에 최종 타입으로 변환하는 식의 변환은 시도하지 않는다. 오직 타입 인스턴스를
//    하나 받아서 목표로 하는 타입의 인스턴스를 반환하는 메서드만 고려한다.
// 5. 적용 가능한 암시적 변환이 둘 이상 있는 경우에는 변환을 수행할 수 없다. 유일하고 모호하지 않을 가능성이 있어야 변환을 수행한다.

// WARNING: You must :paste mode in the REPL for the following.
// Using :load won't compile the two definitions together!
case class Foo(s: String)
object Foo {
  implicit def fromString(s: String): Foo = Foo(s)
}

class O {
  // 범위 안에 명시적으로 임포트한 적은 없지만 Foo.fromString 변환 메서드를 찾을 수 있다.
  // 변환이 이루어진 결과 타입의 동반 객체 -> 자동적으로 최종 검색에 포함시키는 범위이다.(3번)
  def m1(foo: Foo) = println(foo)
  def m(s: String) = m1(s)
}
