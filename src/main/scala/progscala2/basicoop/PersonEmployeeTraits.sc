// src/main/scala/progscala2/basicoop/PersonEmployeeTraits.sc
import progscala2.basicoop.{ Address, Person, Employee }

val ceoAddress  = Address("1 Scala Lane", "Anytown", "CA", "98765")
// 결과: ceoAddress: oop2.Address = Address(1 Scala Lane,Anytown,CA,98765)

val buckAddress = Address("98765")
// 결과: buckAddress: oop2.Address = Address([unknown],Anytown,CA,98765)

val ceo = Employee(
  name = "Joe CEO", title = "CEO", age = Some(50),
  address = Some(ceoAddress), manager = None)
// 결과: ceo: oop2.Employee = Employee(Joe CEO,Some(50),
//            Some(Address(1 Scala Lane,Anytown,CA,98765)),CEO,None)

val ceoSpouse = Person("Jane Smith", address = Some(ceoAddress))
// 결과: ceoSpouse: oop2.Person = Person(Jane Smith,None,
//            Some(Address(1 Scala Lane,Anytown,CA,98765)))

val buck = Employee(
  name = "Buck Trends", title = "Zombie Dev", age = Some(20),
  address = Some(buckAddress), manager = Some(ceo))
// 결과: buck: oop2.Employee = Employee(Buck Trends,Some(20),
//             Some(Address([unknown],Anytown,CA,98765)),Zombie Dev,
//             Some(Employee(Joe CEO,Some(50),
//               Some(Address(1 Scala Lane,Anytown,CA,98765)),CEO,None)))

val buckSpouse = Person("Ann Collins", address = Some(buckAddress))
// 결과: buckSpouse: oop2.Person = Person(Ann Collins,None,
//             Some(Address([unknown],Anytown,CA,98765)))
