// src/main/scala/progscala2/fp/datastructs/filter.sc

// 걸러내기: 컬렉션을 순회하면서 특정 선택 기준을 만족하는 원소를 뽑아내서 새 컬렉션을 만든다.

// scala.collection.TraversableLike: 원래 컬렉션을 걸러내거나, 일부를 반환하는 여러 메서드가 있다.
// 1. drop(n: Int): 맨 앞 n개 원소 제외한 나머지 선택. 새 순회가능 컬렉션 반환. 원래 컬렉션 원소 개수가 n보다 작은 경우 빈 컬렉션 반환.
// 2. dropWhile(p:(A)=>Boolean): 원소 중 주어진 술어(predicate) 만족하는 가장 긴 접두사(prefix)를 제외시킨다. 즉, 순회가능 컬렉션에 대해
//                               첫 원소가 술어 p를 만족하지 않는 가장 긴 접미사(suffix)를 반환한다.
// 3. exists(p:(A)=>Boolean): 순회가능 컬렉션 원소 중 술어를 만족하는 원소가 하나라도 있으면 true, 그렇지 않으면 false 반환.
// 4. filter(p:(A)=>Boolean): 순회가능 컬렉션 원소 중 술어를 만족하는 모든 원소 선택 후 새 순회가능 컬렉션 반환한다. 원소의 순서를 그대로 보존한다.
// 5. filterNot(p:(A)=>Boolean): filter 의 반대. 만족하지 않는 모든 원소를 선택한다.
// 6. find(p:(A)=>Boolean): 순회 가능 컬렉션에서 술어를 만족하는 첫 원소를 찾는다. 있으면 Option 에 담아서 반환, 없다면 None 반환.
// 7. forall(p:(A)=>Boolean): 순회가능 컬렉션 안의 모든 원소가 주어진 술어를 만족하면 true, 그렇지 않으면 false 반환.
// 8. partition(p:(A)=>Boolean): 주어진 술어에 따라 순회가능 컬렉션을 두 부분으로 분할. 순회가능 컬렉션의 쌍을 반환하며, 그 쌍의 첫 원소는 술어를
//                               만족하는 모든 원소로 이뤄지고, 두 번째 원소는 술어 p를 만족하지 않는 모든 원소로 이뤄진다. 각 분할에 속한 원소의
//                               순서는 원래 순회가능 컬렉션 원소 순서와 같다.
// 9. take(n:Int): 맨 앞 n개 원소를 선택. 원래 컬렉션 원소 중 맨 앞 n개가 포함된 새 순회가능 컬렉션 반환. 원래 컬렉션 원소 개수가 n보다 작은 경우
//                 전체 컬렉션을 반환.
// 10. takeWhile(p:(A)=>Boolean): 원소 중 주어진 술어 만족하는 가장 긴 접두사 선택. 즉, 순회가능 컬렉션에서 모든 원소가 술어 p를 만족하는 가장 긴
//                                접두사를 반환한다.

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")
  
val map2 = stateCapitals filter { kv => kv._1 startsWith "A" }

println( map2 )
