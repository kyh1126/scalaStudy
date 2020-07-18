// src/main/scala/progscala2/fp/recursion/trampoline.sc
// From: scala-lang.org/api/current/index.html#scala.util.control.TailCalls$

// 트램펄린: 여러 함수가 한 번에 하나씩 다른 함수를 호출하면서 이루어지는 루프.

// TailCalls: 트램펄린 목적의 스칼라 라이브러리 객체.

import scala.util.control.TailCalls._

// 리스트가 끝날 때까지 각 리스트 원소를 처리하면서 서로 주고받는다. isEven 안에서 리스트의 끝을 만나면 true 를, isOdd 안에서 리스트의 끝을 만나면
// false 반환한다.
def isEven(xs: List[Int]): TailRec[Boolean] =
  if (xs.isEmpty) done(true) else tailcall(isOdd(xs.tail))

def isOdd(xs: List[Int]): TailRec[Boolean] =
 if (xs.isEmpty) done(false) else tailcall(isEven(xs.tail))

for (i <- 1 to 5) {
  val even = isEven((1 to i).toList).result
  println(s"$i is even? $even")
}
