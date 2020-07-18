// src/main/scala/progscala2/fp/basics/hofs-closure-example.sc

// 클로저: 이름이 붙어 있거나 익명인 함수. 함수에서 사용하는 자유 변수가 참조하는 모든 영역의 닫힌 환경을 포함한다.

// 스칼라에서는 람다를 익명 함수나 함수 리터럴이라고 부른다.
var factor = 2
val multiplier = (i: Int) => i * factor

(1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)

factor = 3
(1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)

