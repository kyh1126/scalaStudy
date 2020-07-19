// src/main/scala/progscala2/fp/datastructs/list.sc

// 순차적: 원소를 미리 정해진 순서에 따라 순회 가능하며, 그 순서는 원소를 삽입한 순서거나, 다른 방식에 따라 정렬된 순서다.

// Collection.Seq 트레이트: 모든 변경 가능하거나 불변인 순차적 타입에 대한 추상화.
// Collection.mutable.Seq, Collection.immutable.Seq: Collection.Seq 의 자식 트레이트.

// 연결 리스트: 함수 프로그램에서 가장 자주 사용하는 시퀀스. 리스트는 변경 불가하여 다른 스레드에서 새로운 리스트를 만들어낸 코드가 리스트의 상태를 변경해서
//           다른 코드가 그로 인해 놀라운 일을 겪는 경우는 없다.

// 콘즈(::) 메소드: 콜론(:)으로 메서드 이름이 끝나면 오른쪽으로 결합된다. x :: list = list.::(x)
// ++ : 두 리스트(또는 임의의 시퀀스)를 함께 붙이기.

// Nil: List.empty[Nothing]과 같다.
// Nothing: 스칼라의 모든 다른 타입의 서브타입.

val list1 = List("Programming", "Scala")                 // <1>
val list2 = "People" :: "should" :: "read" :: list1      // <2>

val list3 = "Programming" :: "Scala" :: Nil              // <3>
val list4 = "People" :: "should" :: "read" :: Nil
val list5 = list4 ++ list3                               // <4>
