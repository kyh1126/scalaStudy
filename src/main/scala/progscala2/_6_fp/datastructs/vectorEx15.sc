// src/main/scala/progscala2/fp/datastructs/vector.sc

// immutable.Vector: 벡터는 모든 연산에서 O(l)상수 시간 연산을 제공하지만, List 는 머리 원소에 접근하는 연산 제외하고는 모든 연산이 O(n)이다.

val vect1 = Vector("Programming", "Scala")
val vect2 = "People" +: "should" +: "read" +: vect1
println(vect2)

val vect3 = "Programming" +: "Scala" +: Vector.empty[String]
val vect4 = "People" +: "should" +: "read" +: Vector.empty[String]
val vect5 = vect4 ++ vect3
println(vect5)
// 첨자를 기반으로 원소에 접근할 수도 있다.
vect5(3)
