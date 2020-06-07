// src/main/scala/progscala2/typelessdomore/factorial.sc

// 내포(nest)된 메서드 정의와 재귀
// 길이가 긴 메서드의 본문을 여러 작은 메서드로 리팩토링하는 경우 내포 메서드가 유용하다.

// 꼬리 재귀 최적화: 꼬리 재귀 함수를 일반 루프로 변환하는 것. 꼬리 재귀라는 말은 재귀 호출이 어떤 식의 맨 마지막에 벌어지는 일이라서
//               붙은 이름. 표현식의 반환값은 마지막 호출이 반환하는 값. JVM 과 여러 언어는 이를 수행하지 않는다.
//               스칼라 컴파일러는 약간의 제약이 있는 tail-call optimization 을 직접 수행한다.
/**
 * @see progscala2._2_typelessdomore.fibonacci-tailrecEx8.scX
 */
def factorial(i: Int): Long = {
  // fact 반환 타입은 꼭 명시해야 한다. 스칼라의 지역 영역 타입 추론은 재귀 함수의 반환 타입을 추론할 수 없기 때문.
  def fact(i: Int, accumulator: Long): Long = {
    if (i <= 1) accumulator
    else fact(i - 1, i * accumulator)
  }
    
  fact(i, 1L)
}

(0 to 5) foreach ( i => println(factorial(i)) )
