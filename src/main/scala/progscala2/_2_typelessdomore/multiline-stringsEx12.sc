// src/main/scala/progscala2/typelessdomore/multiline-strings.sc

// stripMargin 메서드: 수직 막대(|)가 각 줄의 처음에 나타난 위치까지의 공백이 실제 출력에는 나오지 않는다.

def hello(name: String) = s"""Welcome!
  Hello, $name!
  * (Gratuitous Star!!)
  |We're glad you're here.
  |  Have some extra whitespace.""".stripMargin

hello("Programming Scala")
