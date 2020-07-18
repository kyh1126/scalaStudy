// src/main/scala/progscala2/fp/recursion/factorial-recur1.sc
import scala.annotation.tailrec

// 재귀 사용 2가지 단점
// 1. 반복된 함수 호출로 인한 성능상의 부가 비용
// 2. 스택 넘침 위험성

// 꼬리 호출 재귀: 어떤 함수가 자기 자신을 호출하되, 그 호출이 해당 함수의 마지막 연산인 경우. 아래의 경우 'i *' 로 인해 꼬리 호출 재귀가 아니다.

// What happens if you uncomment the annotation??
// @tailrec
def factorial(i: BigInt): BigInt = {
  if (i == 1) i
  else i * factorial(i - 1)
}

// 여기의 i는 변경 가능하지 않다.
for (i <- 1 to 10)
  println(s"$i:\t${factorial(i)}")
