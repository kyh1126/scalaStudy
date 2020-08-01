// src/main/scala/progscala2/basicoop/Zipcode.scala
package progscala2._8_basicoop

// require: 입력 인자를 검증해서 결과 인스턴스가 정상적인 상태임을 보장하는 메소드. 단언문 메서드이다.

// 단언문을 사용하려면 힙에 객체를 할당해야 해서 값 클래스의 본문에는 사용 불가하다.

case class ZipCode(zip: Int, extension: Option[Int] = None) {
  require(valid(zip, extension),                                 // <1>
    s"Invalid Zip+4 specified: $toString")

  protected def valid(z: Int, e: Option[Int]): Boolean = {
    if (0 < z && z <= 99999) e match {
      case None    => validUSPS(z, 0)
      case Some(e) => 0 < e && e <= 9999 && validUSPS(z, e)
    }
    else false
  }

  /** 실제 미국에서 사용 중인 우편번호인가? */
  protected def validUSPS(i: Int, e: Int): Boolean = true        // <2>

  override def toString =                                        // <3>
    if (extension != None) s"$zip-${extension.get}" else zip.toString
}

object ZipCode {
  def apply (zip: Int, extension: Int): ZipCode =
    new ZipCode(zip, Some(extension))
}
