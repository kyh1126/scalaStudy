// src/main/scala/progscala2/implicits/toJSON-type-class.sc

// 스칼라에서는 implicit 과 case 키워드를 동시에 사용할 수 없다. 즉, 추상 클래스가 동시에 케이스 클래스가 될 수 없다.

// 타입 클래스: 임의 다형성.(toJSON 다형적 동작이 타입 시스템과 연동되어 있지 않다)

// cats: Future 연산자 같은걸 쉽게 해줄수 있는 joda.time 같은 라이브러리. 우리팀에서 많이 쓰고있다.

case class Address(street: String, city: String)
case class Person(name: String, address: Address)

trait ToJSON {
  def toJSON(level: Int = 0): String

  val INDENTATION = "  "
  def indentation(level: Int = 0): (String,String) = 
    (INDENTATION * level, INDENTATION * (level+1))
}

// 확장 메서드들
implicit class AddressToJSON(address: Address) extends ToJSON {
  def toJSON(level: Int = 0): String = {
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"street": "${address.street}", 
      |${indent}"city":   "${address.city}"
      |$outdent}""".stripMargin
  }
}

implicit class PersonToJSON(person: Person) extends ToJSON {
  def toJSON(level: Int = 0): String = {
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"name":    "${person.name}", 
      |${indent}"address": ${person.address.toJSON(level + 1)} 
      |$outdent}""".stripMargin
  }
}

val a = Address("1 Scala Lane", "Anytown")
val p = Person("Buck Trends", a)

println(a.toJSON())
println()
println(p.toJSON())
