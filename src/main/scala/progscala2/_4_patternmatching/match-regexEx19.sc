// src/main/scala/progscala2/patternmatching/match-regex.sc

// 정규 표현식과 일치시키기

val BookExtractorRE = """Book: title=([^,]+),\s+author=(.+)""".r     // <1> 포획 그룹(그 패턴과 일치하는 부문자열을 나중에 사용 가능)
val MagazineExtractorRE = """Magazine: title=([^,]+),\s+issue=(.+)""".r //  r 메서드를 호출하면 정규 표현식 객체를 만든다.
// 3중 큰따옴표 사용. 그렇지 않으면 \s, \\s 처럼 정규 표현식의 역슬래시를 이스케이프 시켜야 한다.
// 3중 큰따옴표를 사용한 문자열에 정규 표현식의 내부에서 인터폴레이션을 사용하면 다시 문자열을 이스케이프시켜야 한다.
// ex> s"""$first\s + $second""".r 대신
//     s"""$first\\s + $second""".r 써야 한다.

val catalog = Seq(
  "Book: title=Programming Scala Second Edition, author=Dean Wampler",
  "Magazine: title=The New Yorker, issue=January 2014",
  "Unknown: text=Who put this here??"
)

for (item <- catalog) {
  item match {
    case BookExtractorRE(title, author) =>                           // <2> 정규 표현식 객체를 케이스 클래스 객체처럼 사용해 쓸 수 있다.
      println(s"""Book "$title", written by $author""")
    case MagazineExtractorRE(title, issue) =>
      println(s"""Magazine "$title", issue $issue""")
    case entry => println(s"Unrecognized entry: $entry")
  }
}
