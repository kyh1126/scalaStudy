// src/main/scala/progscala2/rounding/TryCatchArm.scala
package progscala2._3_rounding
import scala.language.reflectiveCalls
import scala.util.control.NonFatal

// <1> R은 관리할 자원의 타입. <: 는 R이 다른 어떤 것의 서브 클래스임을 의미. T는 자원을 가지고 작업을 수행하도록 넘겨지는 익명 함수에서 반환하는 타입.
//     resource 는 이름에 의한 매개변수.(괄호 없이 호출할 수 있는 함수로 생각하자) 자원을 가지고 처리할 작업이 들어 있는 두 번째 인자 목록을 넘긴다.
//     자원을 인자로 받고 T라는 타입의 결과를 반환하는 함수다.

// resource 는 이름에 의한 호출 매개변수다.

// 딘 왐플러(2015/12/21): 반환된 타입 T인 객체를 처리한다는 사실을 더 분명히 보여주기 위해 구현을 좀 더 변경하고 사용 예제도 바꿨다.
object manage {
  def apply[R <: { def close():Unit }, T](resource: => R)(f: R => T): T = {   // <1>
    var res: Option[R] = None
    try {
      res = Some(resource)         // '자원'에 대한 유일한 참조 
      f(res.get)                   // T 타입의 인스턴스 반환
    } catch {
      case NonFatal(ex) =>
        println(s"manage.apply(): Non fatal exception! $ex")
        throw ex
    } finally {
      if (res != None) {
        println(s"Closing resource...")
        res.get.close
      }
    }
  }
}

object TryCatchARM {
  /** 사용법: scala rounding.TryCatch 파일이름1 파일이름2 ... */
  def main(args: Array[String]) = {
    val sizes = args map (arg => returnFileLength(arg))
    println("Returned sizes: " + (sizes.mkString(", ")))
  }

  import scala.io.Source

  def returnFileLength(fileName: String): Int = {
    println()  // 가독성을 위해 빈 줄을 넣는다.
    manage(Source.fromFile(fileName)) { source =>
      val size = source.getLines.size
      println(s"file $fileName has $size lines")
      if (size > 20) throw new RuntimeException(s"Big file: $fileName!")
      size
    }
  }
}
