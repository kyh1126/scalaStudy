// src/main/scala/progscala2/fp/datastructs/set.sc

// 집합: 순서가 없는 컬렉션. 시퀀스가 아니다. 또한 집합의 각 원소는 유일하다. Map 과 마찬가지로 scala.collection.Set 트레이트는 오직 변경 불가능한
//      연산만 정의한다. 파생 트레이트인 scala.collection.immutable.Set 과 scala.collection.mutable.Set 이 존재한다. 변경 가능한 버전을
//      명시적으로 임포트해야 하지만, 변경 불가능한 버전이 이미 Predef 에 의해 노출되어 있다. +, -, ++, -- 연산을 제공한다.

val states = Set("Alabama", "Alaska", "Wyoming")

val lengths = states map (st => st.length)
println(lengths)

val states2 = states + "Virginia"
println(states2)

val states3 = states2 + ("New York", "Illinois")
println(states3)
