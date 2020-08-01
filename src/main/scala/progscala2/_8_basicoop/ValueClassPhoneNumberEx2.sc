// src/main/scala/progscala2/basicoop/ValueClassPhoneNumber.sc

// 값 클래스를 케이스 클래스로 정의 가능. 자동으로 만들어지는 여러 메서드와 동반 객체를 사용할 가능성이 적다. 만들어지는 클래스 파일에서 공간 낭비하기 십상이다.

// 범용 트레이트 특성
// 1. Any 를 상속한다.(하지만 다른 범용 트레이트를 상속할 수는 없다)
// 2. 메서드만 정의한다.
// 3. 자체 초기화가 없다.

class USPhoneNumber(val s: String) extends AnyVal {

  override def toString = {
    val digs = digits(s)
    val areaCode  = digs.substring(0,3)
    val exchange  = digs.substring(3,6)
    val subnumber = digs.substring(6,10)  // 가입자 번호
    s"($areaCode) $exchange-$subnumber"
  }

  private def digits(str: String): String = str.replaceAll("""\D""", "") 
}

val number = new USPhoneNumber("987-654-3210")
// 결과: number: USPhoneNumber = (987) 654-3210
