// src/main/scala/progscala2/typelessdomore/method-broad-inference-return.sc

// Nil: 빈 리스트를 나타내는 특별한 표식 타입.
// Unit: ()라는 이름의 유일한 값을 가지는 타입.

// 예상과 다른 반환 타입 추론이 발생할 수 있는 시나리오 2가지 더.
// 1. 함수가 호출하는 다른 함수로 인해 타입을 잘못 추론할 수도 있다.
// 2. 프로젝트 규모가 커지고 여러 모듈이 서로 다른 시간에 빌드되는 경우.

def makeList(strings: String*) = {
  if (strings.length == 0)
    Nil  // #1
  else
    strings.toList
}

val list: List[String] = makeList()  // ERROR
