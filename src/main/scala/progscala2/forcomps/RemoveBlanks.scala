// src/main/scala/progscala2/forcomps/RemoveBlanks.scala
package progscala2.forcomps

object RemoveBlanks {

  /**
   * 지정한 입력 파일에서 빈 줄을 제거한다.
   */
  def apply(path: String, compressWhiteSpace: Boolean = false): Seq[String] =
    for {
      line <- scala.io.Source.fromFile(path).getLines.toSeq          // <1>
      if line.matches("""^\s*$""") == false                          // <2>
      line2 = if (compressWhiteSpace) line replaceAll ("\\s+", " ")  // <3>
              else line
    } yield line2                                                    // <4>

  /**
   * 지정한 입력 파일에서 빈 줄을 제거하고 남은 줄들을 표준 출력에 
   * 하나씩 보낸다. 
   * @param args 파일 경로 목록이다. 파일 이름의 앞에 '-'를 붙이면 
   *             파일에서 연속된 여러 공백을 하나로 압축해준다. 
   */
  def main(args: Array[String]) = for {
    path2 <- args                                                    // <5>
    (compress, path) = if (path2 startsWith "-") (true, path2.substring(1))
                       else (false, path2)                           // <6>
    line <- apply(path, compress)
  } println(line)                                                    // <7>
}
