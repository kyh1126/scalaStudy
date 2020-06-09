// src/main/scala/progscala2/typelessdomore/multiline-strings2.sc

// 수직 막대(|) 대신 다른 문자를 최초 위치 기준 문자로 사용하고 싶다면 Char 타입의 인자를 받는 stripMargin 의 오버로딩 버전을 활용하라.
// stripPrefix, stripSuffix: 접두사나 접미사를 제거.

def goodbye(name: String) = 
  s"""xxxGoodbye, ${name}yyy
  xxxCome again!yyy""".stripPrefix("xxx").stripSuffix("yyy")

goodbye("Programming Scala")
