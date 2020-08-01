// src/main/scala/progscala2/basicoop/PersonAuxConstructors3.scala
package progscala2.basicoop3
import progscala2._8_basicoop.Address

// 동반 객체에 있는 Person.apply 를 오버로딩하면 우리 자신만의 생성자를 만들 수 있고 new 사용을 피할 수 있다.

case class Person3(
  name: String,
  age: Option[Int] = None,
  address: Option[Address] = None)

object Person3 {

  // (생성자가 아니라) 일반 메서드를 오버로딩하기 때문에
  // 반환 타입 표기를 추가해야 한다. 여기서는 Person3다.
  def apply(name: String): Person3 = new Person3(name)

  def apply(name: String, age: Int): Person3 = new Person3(name, Some(age))

  def apply(name: String, age: Int, address: Address): Person3 =
    new Person3(name, Some(age), Some(address))

  def apply(name: String, address: Address): Person3 =
    new Person3(name, address = Some(address))
}
