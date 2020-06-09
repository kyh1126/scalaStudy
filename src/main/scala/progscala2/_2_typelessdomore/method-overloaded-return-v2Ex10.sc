// src/main/scala/progscala2/typelessdomore/method-overloaded-return-v2.sc
// Version 2 of "StringUtil" (with a fixed compilation error).

object StringUtilV2 {
  def joiner(strings: String*): String = strings.mkString("-")

  // :_* 는 컴파일러에 주는 힌트로, strings 라는 리스트를 가변 인자 목록(*)으로 다루되, 타입은 알 수 없지만 추론한 타입(:_)을 사용하라는 뜻.
  def joiner(strings: List[String]): String = joiner(strings :_*)
}

println( StringUtilV2.joiner(List("Programming", "Scala")) )
