// src/main/scala/progscala2/patternmatching/match-variable2.sc

// 3. i, d, s, unexpected 변수를 위치지정자(_)로 바꿨다. 모든 경우에 x를 사용할수도 있다.
for {
  x <- Seq(1, 2, 2.7, "one", "two", 'four)
} {
  val str = x match {
    case 1          => "int 1"
    case _: Int     => "other int: "+x
    case _: Double  => "a double: "+x
    case "one"      => "string one"
    case _: String  => "other string: "+x
    case _          => "unexpected value: " + x
  }
  println(str)
}

