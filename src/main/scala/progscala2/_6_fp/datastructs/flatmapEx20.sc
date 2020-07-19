// src/main/scala/progscala2/fp/datastructs/flatmap.sc

// 펼치면서 연관시키기
// flatMap: 생성한 여러 컬렉션을 펼쳐서 한 컬렉션에 넣어준다.

val list = List("now", "is", "", "the", "time")

list flatMap (s => s.toList)  // List[Char] = List(n, o, w, i, s, t, h, e, t, i, m, e)

// 중간 컬렉션을 생성하지 않기 때문에 더 효율적이다.
// Like flatMap, but flatMap eliminates the intermediate step!
val list2 = List("now", "is", "", "the", "time") map (s => s.toList)
// List[List[Char]] = List(List(n, o, w), List(i, s), List(), List(t, h, e), List(t, i, m, e))

list2.flatten   // List[Char] = List(n, o, w, i, s, t, h, e, t, i, m, e)