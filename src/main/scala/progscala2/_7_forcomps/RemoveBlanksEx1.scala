// src/main/scala/progscala2/forcomps/RemoveBlanks.scala
package progscala2._7_forcomps

object RemoveBlanksEx1 {

  /**
   * 지정한 입력 파일에서 빈 줄을 제거한다.
   */
  def apply(path: String, compressWhiteSpace: Boolean = false): Seq[String] =
    for {
      line <- scala.io.Source.fromFile(path).getLines.toSeq          // <1> 파일을 열고 각 줄을 읽는다. getLines 는 Iterator 반환.
                                                                     //     for 내장의 반환 타입이 최초 제너레이터에 의해 결정되었다.(시퀀스)
      if line.matches("""^\s*$""") == false                  // <2> 정규 표현식을 사용해 빈 줄을 걸러낸다.
      line2 = if (compressWhiteSpace) line replaceAll ("\\s+", " ")  // <3> 지역 변수 정의. 연속된 공백을 한 공백으로 압축한다.
              else line
    } yield line2                                                    // <4> yield 사용해서 줄을 반환한다. for 내장이 만드는 Seq 를
                                                                     //     apply가 반환한다.

  /**
   * 지정한 입력 파일에서 빈 줄을 제거하고 남은 줄들을 표준 출력에 
   * 하나씩 보낸다. 
   * @param args 파일 경로 목록이다. 파일 이름의 앞에 '-'를 붙이면 
   *             파일에서 연속된 여러 공백을 하나로 압축해준다. 
   */
  def main(args: Array[String]) = for {
    path2 <- args                                                    // <5> for 내장을 사용해서 인자 목록을 처리한다.
    (compress, path) = if (path2 startsWith "-") (true, path2.substring(1))
                       else (false, path2)                           // <6> 파일 경로가 - 로 시작하는 경우 공백 압축을 활성화하고,
                                                                     //     그렇지 않은 경우 빈 줄만 제거한다.
    line <- apply(path, compress)
  } println(line)                                                    // <7> 처리한 모든 줄을 stdout 에 쓴다.
}
