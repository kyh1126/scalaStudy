// src/main/scala/progscala2/fp/datastructs/foldreduce.sc

// 접기(fold): 초기 씨앗값으로부터 시작하며 각 원소를 그 씨앗값의 맥락에서 처리.
// 축약(reduce): 사용자가 지정한 초기값으로 시작하지 않고, 원소 중 하나를 초기값으로 사용. 보통 맨 처음 원소나 맨 마지막 원소가 씨앗값이 된다.
//              빈 컬렉션에 대해 사용할 수 없다. 반환할 것이 없기 때문에 예외가 던져진다.
// -> 두 종류 모두 컬렉션을 더 작은 컬렉션이나 하나의 값으로 축소시키는 연산이다.

List(1,2,3,4,5,6) reduce (_ + _)

List(1,2,3,4,5,6).fold (10) (_ * _)
(List(1,2,3,4,5,6) fold 10) (_ * _)       // 7200

// 이렇게 fold 부분 적용 가능하다.
val fold1 = (List(1,2,3,4,5,6) fold 10) _
fold1(_ * _)
(List.empty[Int] fold 10) (_ + _)


try {
  List.empty[Int] reduce (_ + _)
} catch {
  case e: java.lang.UnsupportedOperationException => e
}

// reduceOption: 컬렉션이 비어있는지 확신할 수 없을 때 사용.
List.empty[Int] reduceOption (_ + _)
List(1,2,3,4,5,6) reduceOption (_ * _)
