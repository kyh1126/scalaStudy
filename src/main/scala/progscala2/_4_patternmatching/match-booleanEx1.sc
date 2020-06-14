// src/main/scala/progscala2/patternmatching/match-boolean.sc

// 스칼라의 패턴 매칭: 타입, 와일드카드, 시퀀스, 정규 표현식에 대한 판별은 물론 객체 상태를 깊이 관찰하는 것도 가능하다.

// 1. 단순 매치
val bools = Seq(true, false)

for (bool <- bools) {
  bool match {
    case true => println("Got heads")
    case false => println("Got tails")
  }
}

for (bool <- bools) {
  val which = if (bool) "head" else "tails"
  println("Got " + which)
}
