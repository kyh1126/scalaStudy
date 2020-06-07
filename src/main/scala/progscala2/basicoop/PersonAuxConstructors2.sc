// src/main/scala/progscala2/basicoop/PersonAuxConstructors2.sc
import progscala2.basicoop.Address

val a1 = new Address("1 Scala Lane", "Anytown", "CA", "98765")
val a2 = new Address("98765")

case class Person2(
  name: String,
  age: Option[Int] = None,
  address: Option[Address] = None)

new Person2("Buck Trends1")
// 결과: Person2 = Person2(Buck Trends1,None,None)

new Person2("Buck Trends2", Some(20), Some(a1))
// 결과: Person2(Buck Trends2,Some(20),
//           Some(Address(1 Scala Lane,Anytown,CA,98765)))

new Person2("Buck Trends3", Some(20))
// 결과: Person2(Buck Trends3,Some(20),None)

new Person2("Buck Trends4", address = Some(a2))
// 결과: Person2(Buck Trends4,None,
//           Some(Address([unknown],Anytown,CA,98765)))
