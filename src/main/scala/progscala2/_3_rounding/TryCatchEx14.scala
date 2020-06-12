// src/main/scala/progscala2/rounding/TryCatch.scala
package progscala2._3_rounding

// <1> foreach 를 사용해서 인자 목록에 대해 루프를 돌면서 각 인자를 처리한다. 루프를 매번 돌 때 그리고 마지막 식인 foreach 의 결과로 Unit 이 반환된다.
// <2> 입력을 읽기 위해, 치명적이지 않은 오류에 대해 매치시키기 위해 각각 임포트.
// <3> 각 파일 이름에 대해 라인 수를 센다.
// <4> source 를 Option 으로 정의해서 실제 인스턴스가 있는지 여부를 finally 절에서 알 수 있게 한다.
// <6> 파일이 없으면 FileNotFoundException, 있으면 돌려받은 Source 인스턴스를 Some 으로 감싼다. 그래서 get 호출해도 안전하다.
// <7> 치명적이지 않은 오류를 잡아낸다.
// <8> for 내장을 사용해서 Some 에서 Source 인스턴스를 뽑아내고 닫는다. None 이면 아무일도 일어나지 않는다.(close 호출 블록이 실행되지 않는다)

object TryCatchEx14 {
  /** 사용법: scala rounding.TryCatch 파일이름1 파일이름2 ... */
  def main(args: Array[String]) = {
    args foreach (arg => countLines(arg))                            // <1>
  }

  import scala.io.Source                                             // <2>
  import scala.util.control.NonFatal

  def countLines(fileName: String) = {                         // <3>
    println()  // 읽기 쉽게 하기 위해 빈 줄을 넣는다.
    var source: Option[Source] = None                                // <4>
    try {                                                            // <5>
      source = Some(Source.fromFile(fileName))                       // <6>
      val size = source.get.getLines.size
      println(s"file $fileName has $size lines")
    } catch {
      case NonFatal(ex) => println(s"Non fatal exception! $ex")      // <7>
    } finally {
      for (s <- source) {                                            // <8>
        println(s"Closing $fileName...")
        s.close
      }
    }
  }
}
