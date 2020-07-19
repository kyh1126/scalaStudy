// src/main/scala/progscala2/fp/datastructs/foreach.sc

// foreach: 스칼라 컨테이너의 표준 순회 메서드. scala.collection.IterableLike 에 정의되어 있다. 이 메서드는 오버라이딩 가능하다.
//          부수 효과만 수행할 수 있기 때문에 순수 함수가 아니다. 하지만 일단 foreach 만 잇으면 연관시키기(mapping), 걸러내기(filtering),
//          접기(folding), 축약시키기(reducing) 같은 순수 연산을 구현할 수 있다.

List(1, 2, 3, 4, 5) foreach { i => println("Int: " + i) }

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")

//stateCapitals foreach { kv => println(kv._1 + ": " + kv._2) }
stateCapitals foreach { case (k, v) => println(k + ": " + v) }
