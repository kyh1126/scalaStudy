// src/main/scala/progscala2/fp/basics/hofs-example.sc

// reduce: 정수의 컬렉션을 단일 값으로 축약한다. 인자 둘 중 하나는 입력 컬렉션의 현재 원소, 다른 하나는 '누적값'.

// 반복 횟수를 추적하기 위한 var 변수, 축약 중인 값을 갱신할 var 변수 사용하지 않으면서 루프 돌 수 있다.
(1 to 10) filter (_ % 2 == 0) map (_ * 2) reduce (_ * _)

