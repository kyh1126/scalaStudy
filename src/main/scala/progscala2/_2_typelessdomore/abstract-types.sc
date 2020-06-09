// src/main/scala/progscala2/typelessdomore/abstract-types.sc
import java.io._

// 매개변수화한 타입
// 1. 공변성 타입: ex> sealed abstract class List[+A] :: B가 A의 서브타입인 경우, List[B]도 List[A]의 서브타입이라는 뜻.
// 2. 반공변성 타입: ex> Foo[-A] :: B가 A의 서브타입인 경우, Foo[B]는 Foo[A]의 슈퍼타입이다.

// 추상화 방식
//  추상 타입: 부모 클래스 안에서 추상 타입을 사용하고, 그 타입 멤버를 자식 클래스에서 구체화 한다.

// 매개변수화한 타입 대신 타입 멤버를 사용하는 것의 이점: 타입 멤버는 그 멤버를 둘러싼 타입과 함께 진화하는 경우에 가장 잘 들어맞는다.

// 가족 다형성(공변 특화): 타입 멤버는 그 멤버를 둘러싼 타입에 의해 표현되는 동작과 일치해야 한다.

abstract class BulkReader {
  type In
  val source: In
  def read: String  // 원본을 읽어서 String을 반환한다.
}

class StringBulkReader(val source: String) extends BulkReader {
  type In = String
  def read: String = source
}

class FileBulkReader(val source: File) extends BulkReader {
  type In = File
  def read: String = {
    val in = new BufferedInputStream(new FileInputStream(source))
    val numBytes = in.available()
    val bytes = new Array[Byte](numBytes)
    in.read(bytes, 0, numBytes)
    new String(bytes)
  }
}

println(new StringBulkReader("Hello Scala!").read)
// 현재 디렉터리가 src/main/scala라고 가정한다.
//println(new FileBulkReader(
//  new File("TypeLessDoMore/abstract-types.sc")).read)
