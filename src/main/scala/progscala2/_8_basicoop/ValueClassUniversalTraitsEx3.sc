// src/main/scala/progscala2/basicoop/ValueClassUniversalTraits.sc

// 인스턴스화가 필요한 경우
// 1. 값 클래스의 인스턴스를, 그 인스턴스가 구현한 범용 트레이트를 인자로 받는 함수에 전달하는 경우 인스턴스화가 필요하다. 하지만 어떤 함수가 값 클래스
//    자체의 인스턴스를 인자로 받는 경우에는 인스턴스화를 할 필요가 없다.
// 2. 값 클래스 인스턴스를 배열에 할당하는 경우 인스턴스화가 필요하다.
// 3. 값 클래스의 타입을 타입 매개변수로 사용하는 경우 인스턴스화가 필요하다.

// 값 클래스는 부가 비용을 적게 쓰면서 확장 클래스(타입 클래스)를 정의하거나, 어떤 분야에서 의미 있는 이름을 타입에 부여할 수 있는 방법이다.

// 부모 클래스를 확장하지 않는 경우 AnyRef 가 기본적으로 부모다.

trait Digitizer extends Any {
  def digits(s: String): String = s.replaceAll("""\D""", "") // <1> 원래 digits 메서드만 포함하는 트레이트
}

trait Formatter extends Any {                // <2> 전화번호를 우리가 원하는 대로 형식화한다.
  def format(areaCode: String, exchange: String, subnumber: String): String =
    s"($areaCode) $exchange-$subnumber"
}

class USPhoneNumber(val s: String) extends AnyVal 
    with Digitizer with Formatter {

  override def toString = {
    val digs = digits(s)
    val areaCode = digs.substring(0,3)
    val exchange = digs.substring(3,6)
    val subnumber  = digs.substring(6,10)
    format(areaCode, exchange, subnumber)    // <3> Formatter.format 을 사용한다.
  }
}

val number = new USPhoneNumber("987-654-3210")
// 결과: number: USPhoneNumber = (987) 654-3210
