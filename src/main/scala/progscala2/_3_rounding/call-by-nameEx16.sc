// src/main/scala/progscala2/rounding/call-by-name.sc

// 이름에 의한 호출 매개변수: 매번 참조될 때마다 평가된다. 평가를 지연시키므로 어떤 의미에서는 지연 계산이다. 하지만 계산을 매번 반복하는 지연 계산이다.

@annotation.tailrec                                                  // <1> 구현이 꼬리재귀가 되도록 보장한다.
def continue(conditional: => Boolean)(body: => Unit) {               // <2> 인자 목록을 둘 받는 continue 함수를 정의한다.
  if (conditional) {                                                 // <3> 조건을 검사한다.
    body                                                             // <4> body 를 계산한 다음 continue 를 재귀 호출한다.
    continue(conditional)(body)
  }
}

var count = 0                                                        // <5> 사용해보자.
continue(count < 5) {
  println(s"at $count")
  count += 1
}
