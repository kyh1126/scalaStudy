// src/main/scala/progscala2/patternmatching/match-list.sc

val nonEmptyList = List(1, 2, 3, 4, 5)
val emptyList    = Nil

val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3)
val mapsSeq = (("one",1) +: (("two",2) +: (("three",3) +: (Nil))))

// 스칼라 2.19 이전에는 List 와 좀 더 밀접한 연관이 있는 관용구를 사용하는 것이 일반적이었다.

def listToString[T](list: List[T]): String = list match {
  case head :: tail => s"($head :: ${listToString(tail)})"       // <1> +: 를 :: 로 변경.
  case Nil => "(Nil)"
}

for (l <- List(nonEmptyList, emptyList)) { println(listToString(l)) }

// Map.apply 팩토리 메서드는 원소가 2개인 튜플의 가변인자 목록을 취한다.
// 시퀀스를 사용해서 Map 만들려면 : _* 로 컴파일러가 시퀀스를 가변인자 목록으로 바꾸게 해야한다.
val m = Map(mapsSeq: _*)

List("berry", "azalea") :: List("Jenny")  // 오른쪽 bind 된다.
List("berry", "azalea") +: List("Jenny")  // 오른쪽 bind 된다.
List("berry", "azalea") :+ List("Jenny")  // 왼쪽 bind 된다.