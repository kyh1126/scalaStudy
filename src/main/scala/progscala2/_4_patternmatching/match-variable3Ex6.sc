// src/main/scala/progscala2/patternmatching/match-variable3.sc

// 다음과 같이 한 case 문 안에 여러 조건을 달 수도 있다.
for {
  x <- Seq(1, 2, 2.7, "one", "two", 'four)
} {
  val str = x match {
    case _: Int | _: Double => "a number: "+x
    case "one"              => "string one"
    case _: String          => "other string: "+x
    case _                  => "unexpected value: " + x
  }
  println(str)
}
