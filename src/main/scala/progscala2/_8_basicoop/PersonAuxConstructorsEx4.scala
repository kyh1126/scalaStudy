// src/main/scala/progscala2/basicoop/PersonAuxConstructors.scala
package progscala2._8_basicoop

// 보조 생성자: 보조 생성자 이름은 this. 반드시 주 생성자나 다른 보조 생성자를 맨 첫 번째 식으로 호출해야 한다. 컴파일러는 보조 생성자가 소스 코드에서
//           자신보다 더 앞에 있는 생성자만 호출하도록 요구한다.

case class Address(street: String, city: String, state: String, zip: String) {

  def this(zip: String) =                                        // <1> 우편번호만 인자로 받는 보조 생성자.
    this("[unknown]", Address.zipToCity(zip), Address.zipToState(zip), zip)
}

object Address {

  def zipToCity(zip: String)  = "Anytown"                        // <2> 우편번호로부터 도시와 주를 찾아내는 도우미 함수.
  def zipToState(zip: String) = "CA"
}

case class Person(name: String, age: Option[Int], address: Option[Address]) {    // <3> 개인의 나이와 주소를 선택적으로 받을 수 있게 한다.

  def this(name: String) = this(name, None, None)                // <4> 사용자가 여러 값 중 일부 또는 저눕를 지정할 수 있도록 편의를 제공하기 위해 보조 생성자를 제공한다.

  def this(name: String, age: Int) = this(name, Some(age), None)

  def this(name: String, age: Int, address: Address) =
    this(name, Some(age), Some(address))

  def this(name: String, address: Address) = this(name, None, Some(address))
}
