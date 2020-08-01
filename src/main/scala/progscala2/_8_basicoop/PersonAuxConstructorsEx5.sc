// src/main/scala/progscala2/basicoop/PersonAuxConstructors.sc
import progscala2._8_basicoop.{Address, Person}

// 문제점: Person 은 보조 생성자로 인해 불필요한 준비 코드가 많이 생겼다.

val a1 = new Address("1 Scala Lane", "Anytown", "CA", "98765")
// 결과: Address(1 Scala Lane,Anytown,CA,98765)

val a2 = new Address("98765")
// 결과: Address([unknown],Anytown,CA,98765)

new Person("Buck Trends1")
// 결과: Person(Buck Trends1,None,None)

new Person("Buck Trends2", Some(20), Some(a1))
// 결과: Person(Buck Trends2,Some(20),
//           Some(Address(1 Scala Lane,Anytown,CA,98765)))


new Person("Buck Trends3", 20, a2)
// 결과: Person(Buck Trends3,Some(20),
//           Some(Address([unknown],Anytown,CA,98765)))

new Person("Buck Trends4", 20)
// 결과: Person(Buck Trends4,Some(20),None)
