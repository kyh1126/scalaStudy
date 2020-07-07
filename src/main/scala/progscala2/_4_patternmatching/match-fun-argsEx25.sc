// src/main/scala/progscala2/patternmatching/match-fun-args.sc

// case 절, 패턴 매칭 편리하게 사용할 수 있는 경우: 인자가 복잡한 함수 리터럴을 쉽게 사용하도록 만드는 것.

case class Address(street: String, city: String, country: String)
case class Person(name: String, age: Int)

val as = Seq(
  Address("1 Scala Lane", "Anytown", "USA"),
  Address("2 Clojure Lane", "Othertown", "USA"))
val ps = Seq(
  Person("Buck Trends", 29),
  Person("Clo Jure", 28))

val pas = ps zip as

// 보기 좋지 않은 예: 튜플을 인자로 받아서 패턴 매치로 튜플의 두 값을 뽑아내는 일반적인 함수
pas map { tup =>
  val Person(name, age) = tup._1
  val Address(street, city, country) = tup._2
  s"$name (age: $age) lives at $street, $city, in $country"
}

// 보기 좋은 예: 부분 함수
pas map {
  case (Person(name, age), Address(street, city, country)) =>
    s"$name (age: $age) lives at $street, $city, in $country"
}
