// src/main/scala/progscala2/patternmatching/match-deep.sc

// 지나치게 간단한 주소 타입이다. 모든 필드가 문자열이어야 하는지는 의문이다.
case class Address(street: String, city: String, country: String)
case class Person(name: String, age: Int, address: Address)

val alice   = Person("Alice",   25, Address("1 Scala Lane", "Chicago", "USA"))
val bob     = Person("Bob",     29, Address("2 Java Ave.",  "Miami",   "USA"))
val charlie = Person("Charlie", 32, Address("3 Python Ct.", "Boston",  "USA"))

for (person <- Seq(alice, bob, charlie)) {
  person match {
    case Person("Alice", 25, Address(_, "Chicago", _)) => println("Hi Alice!")
    case Person("Bob", 29, Address("2 Java Ave.", "Miami", "USA")) => 
      println("Hi Bob!")
    case Person(name, age, _) => 
      println(s"Who are you, $age year-old person named $name?")
  }
}
