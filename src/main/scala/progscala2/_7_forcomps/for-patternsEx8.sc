// src/main/scala/progscala2/forcomps/for-patterns.sc

val ignoreRegex = """^\s*(#.*|\s*)$""".r                     // <1> 무시할 줄, 비었거나 주석인 줄 정규 표현식. (#: 주석)
val kvRegex = """^\s*([^=]+)\s*=\s*([^#]+)\s*.*$""".r        // <2> 키=값 쌍에 대한 정규 표현식. 임의의 공백과 프로퍼티 뒤 주석도 처리.

val properties = """
  |# Book properties
  |
  |book.name = Programming Scala, Second Edition # A comment
  |book.authors = Dean Wampler and Alex Payne
  |book.publisher = O'Reilly
  |book.publication-year = 2014
  |""".stripMargin                                           // <3> 여러줄로 된 예제 프로퍼티 문자열. 이 기법을 사용하면 공백을 문자열의
                                                             //     일부로 해석하지 않게 만들면서 들여쓰기를 맞출 수 있다.
val kvPairs = for {
  prop <- properties.split("\n")                     // <4> properties 프로퍼티 문자열을 줄 바꿈을 기준으로 분리한다.
  if ignoreRegex.findFirstIn(prop) == None                   // <5> 무시하지 않을 줄만 남기고 걸러낸다.
  kvRegex(key, value) = prop                                 // <6> 왼쪽 항에 패턴식 사용. 키와 값을 정규 표현식을 사용해 올바른 한 줄의 프로퍼티에서 뽑아낸다.
} yield (key.trim, value.trim)                               // <7> 필요 없는 공백을 제거한 결과 키-값 쌍을 산출한다.
// 반환: kvPairs: Array[(String, String)] = Array( 
//   (book.name,Programming Scala, Second Edition), 
//   (book.authors,Dean Wampler and Alex Payne), 
//   (book.publisher,O'Reilly), 
//   (book.publication-year,2014))
