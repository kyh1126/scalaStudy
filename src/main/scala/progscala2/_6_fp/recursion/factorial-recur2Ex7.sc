// src/main/scala/progscala2/fp/recursion/factorial-recur2.sc
import scala.annotation.tailrec

// @tailrec: 컴파일러가 재귀를 루프로 성공적으로 바꿀수 있음을 알려준다.

// 루프를 사용하면 스택 넘침의 가능성이 없어지며, 재귀적 함수 호출에 따른 부가 비용도 사라진다.

def factorial(i: BigInt): BigInt = {
  @tailrec
  def fact(i: BigInt, accumulator: BigInt): BigInt =
    if (i == 1) accumulator
    else fact(i - 1, i * accumulator)

  fact(i, 1)
}

for (i <- 1 to 10)
  println(s"$i:\t${factorial(i)}")
