// src/main/scala/progscala2/patternmatching/match-guard.sc

// 케이스 절의 가드: 리터럴 값에 대해 일치시키는 것 외에 약간의 판단이 추가로 필요할 때 사용.

for (i <- Seq(1,2,3,4)) {
  i match {
    case _ if i%2 == 0 => println(s"even: $i")       // <1> 짝수인 경우에만 일치시킨다.
    case _             => println(s"odd:  $i")
  }
}
