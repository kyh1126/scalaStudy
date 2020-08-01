// src/main/scala/progscala2/basicoop/PersonAuxConstructors3.sc
import progscala2._8_basicoop.Address
import progscala2.basicoop3.Person3

// 보조 생성자를 이렇게 정의해서 쓸 수 있지만, 이름 있는 인자나 선택적 인자를 더 주의깊게 사용하고, 오버로딩한 apply 팩토리 메서드를 활용하라.

val a1 = new Address("1 Scala Lane", "Anytown", "CA", "98765")
val a2 = new Address("98765")

Person3("Buck Trends1")                            // 주 생성자: 자동으로 케이스 클래스의 일부분으로 만들어지는 주 apply 메서드.
// 결과: Person3(Buck Trends1,None,None)

Person3("Buck Trends2", Some(20), Some(a1))        // 주 생성자
// 결과: Person3(Buck Trends2,Some(20),
//           Some(Address(1 Scala Lane,Anytown,CA,98765)))

Person3("Buck Trends3", 20, a1)
// 결과: Person3(Buck Trends3,Some(20),
//           Some(Address(1 Scala Lane,Anytown,CA,98765)))

Person3("Buck Trends4", Some(20))                  // 주 생성자
// 결과: Person3(Buck Trends4,Some(20),None)

Person3("Buck Trends5", 20)
// 결과: Person3(Buck Trends5,Some(20),None)

Person3("Buck Trends6", address = Some(a2))        // 주 생성자
// 결과: Person3(Buck Trends6,None,
//           Some(Address([unknown],Anytown,CA,98765)))

Person3("Buck Trends7", address = a2)
// 결과: Person3(Buck Trends7,None,
//           Some(Address([unknown],Anytown,CA,98765)))
