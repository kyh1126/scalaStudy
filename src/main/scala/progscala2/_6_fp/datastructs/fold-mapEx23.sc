// src/main/scala/progscala2/fp/datastructs/fold-map.sc

// foldRight: 컬렉션을 오른쪽에서 왼쪽으로 순회하기 떄문에, 새로 만들어지는 원소를 가지고 새 리스트를 올바른 순서로 생성할 수 있다.

// 6을 맨 처음 처리하고 빈 리스트에 덧붙인 다음, 그 결과 리스트의 앞에 5를 처리한 결과값을 추가하는 식의 연산을 계속 진행한다.
// 누적값은 익명 함수의 두 번째 인자다.

// scala.collection.TraversableOnce, scala.collection.TraversableLike: 여러 접기와 축약 연산에 대한 시그니처들이 있다.
// 1. fold[A1>:A](z:A1)(op:(A1,A1)=>A1): 지정한 결합 법칙이 성립하는 이항 연산자 op를 사용해서 이 순회가능 컬렉션 또는 반복자의 원소를 접는다.
//                                       원소에 대해 연산을 수행하는 순서는 정해져있지 않고, 비결정적일수도 있다. 하지만 List 같이 순서가 정해진
//                                       컬렉션의 경우, fold 는 foldLeft 와 같다.
// 2. foldLeft[B](z:B)(op:(B,A)=>B): 이항 연산 op를 시작값과 순회가능 컬렉션 또는 반복자 모든 원소에 적용하되, 왼쪽에서 오른쪽으로 진행한다.
// 3. foldRight[B](z:B)(op:(A,B)=>B): ", 오른쪽에서 왼쪽으로 진행한다.
// 4. /:[B](z:B)(op:(B,A)=>B): foldLeft 와 같다. 하지만 이러면 코드 읽기가 힘들다.
// 5. :\[B](z:B)(op:(A,B)=>B): foldRight 와 같다. 하지만 코드 읽기가 힘들다.
// 6. reduce[A1>:A](op:(A1,A1)=>A1): 지정한 결합 법칙이 성립하는 이항 연산자 op를 사용해서 이 순회가능 컬렉션 또는 반복자의 원소를 축약한다. 원소에
//                                   대해 연산을 수행하는 순서는 정해져있지 않고, 비결정적일수도 있다. 하지만 List 같이 순서가 정해진 컬렉션의
//                                   경우, reduce 는 reduceLeft 와 같다.
// 7. reduceLeft[A1>:A](op:(A1,A1)=>A1): 이항 연산 op를 순회가능 컬렉션 또는 반복자 모든 원소에 적용하되, 왼쪽에서 오른쪽으로 진행한다. 컬렉션이
//                                       빈 경우 예외 던진다.
// 8. reduceRight[A1>:A](op:(A1,A1)=>A1): ", 오른쪽에서 왼쪽으로 진행한다. 컬렉션이 빈 경우 예외 던진다.
// 9. reduceOption[A1>:A](op:(A1,A1)=>A1): reduce 와 비슷. 하지만 컬렉션이 비어있으면 None, 그렇지 않으면 Some(..) 반환.
// 10. reduceLeftOption[B>:A](op:(B,A)=>B): reduceLeft 와 비슷. 하지만 컬렉션이 비어있으면 None, 그렇지 않으면 Some(..) 반환.
// 11. reduceRightOption[B>:A](op:(A,B)=>B): reduceRight 와 비슷. 하지만 컬렉션이 비어있으면 None, 그렇지 않으면 Some(..) 반환.
// 12. aggregate[B](z:B)               : 여러 원소에 대해 연산자 적용한 결과를 응집시킨다. 이 연산은 접거나 축약 연산의 더 일반적 형태다. 의미는
//     (seqop:(B,A)=>B,combop:(B,B)=>B)  비슷하지만, 결과 타입이 원소 타입의 부모 타입일 필요는 없다. 이 함수는 컬렉션의 여러 분할로부터 원소를
//                                       순차적으로 순회하면서 seqop 로 결과 갱신한 후, 각 파티션에서 얻은 결과에 combop를 적용한다. 이 연산
//                                       구현은 컬렉션을 임의의 개수로 분할해서 수행할 수 있다. 따라서 combop 호출 횟수가 달라질 수 있다.
// 13. scan[B>:A](z:B)(op:(B,B)=>B): 컬렉션 원소를 접두사 스캔한다. 중립 원소인 z가 1번 이상 적용될 수 있다는 점에 유의하라.
// 14. scanLeft[B>:A](z:B)(op:(B,B)=>B): 연산자 op 왼쪽에서 오른쪽으로 적용하면서 누적 결과 포함하는 컬렉션 반환.
// 15. scanRight[B>:A](z:B)(op:(B,B)=>B): 연산자 op 오른쪽에서 왼쪽으로 ".
// 16. product: 컬렉션 원소들의 곱. 원소가 Numeric[A]로 암시적 변환 가능한 경우에 한해 곱셈 진행.(Int, Long, Float, Double, BigInt)
// 17. mkString: 순회가능 컬렉션이나 반복자 모든 원소를 문자열로 표현. 이 메서드는 컬렉션에서 문자열을 만들기 전용 fold 구현이다.
// 18. mkString(sep: String): ", sep 을 원소 사이에 삽입.
// 19. mkString(start:String, sep:String, end:String): ", start 를 문자열 맨 앞, end 를 문자열 맨 뒤, sep 을 원소 사이에 삽입.

// Left 메서드에서는 첫 번쨰 원소가 누적값, Right 메서드에서는 두 번째 원소가 누적값이다.

(List(1, 2, 3, 4, 5, 6) foldRight List.empty[String]) {
  (x, list) => ("[" + x + "]") :: list
}

// scan 메서드: 컬렉션의 연속적인 부분집합에 대한 처리를 해야하는 경우 유용.
(List(1, 2, 3, 4, 5) scan 10) (_ + _)