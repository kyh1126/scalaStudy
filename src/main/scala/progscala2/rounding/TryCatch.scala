// src/main/scala/progscala2/rounding/TryCatch.scala
package progscala2.rounding

object TryCatch {
  /** 사용법: scala rounding.TryCatch 파일이름1 파일이름2 ... */
  def main(args: Array[String]) = {
    args foreach (arg => countLines(arg))                            // <1>
  }

  import scala.io.Source                                             // <2>
  import scala.util.control.NonFatal

  def countLines(fileName: String) = {                               // <3>
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
