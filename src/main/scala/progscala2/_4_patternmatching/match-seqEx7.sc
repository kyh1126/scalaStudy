// src/main/scala/progscala2/patternmatching/match-seq.sc

// Seq: 정해진 순서대로 원소를 순회할 수 있는 List 나 Vector 등의 모든 구체적인 컬렉션 타입의 부모 타입.

val nonEmptySeq    = Seq(1, 2, 3, 4, 5)                          // <1> Seq[Int]
val emptySeq       = Seq.empty[Int]
val nonEmptyList   = List(1, 2, 3, 4, 5)                         // <2> List[Int] (immutable Nil)
val emptyList      = Nil
val nonEmptyVector = Vector(1, 2, 3, 4, 5)                       // <3> Vector[Int]
val emptyVector    = Vector.empty[Int]

val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3)   // <4> Map[String, Int] (Seq 서브타입 아님)
val emptyMap       = Map.empty[String,Int]

def seqToString[T](seq: Seq[T]): String = seq match {            // <5> T 타입에 대해 Seq[T]로부터 String 생성하는 재귀적 메서드
  case head +: tail => s"$head +: " + seqToString(tail)          // <6> 비어있지 않은 Seq 와 일치. +: 는 시퀀스의 '콘즈' 연산자.
  case Nil => "Nil"                                              // <7> 빈 List 를 표현하는 특별 객체. List 같은 몇몇 타입만 실제로 빈 시퀀스 인스턴스 구현.
}                                                                //     모든 Seq 는 항상 같은 타입의 빈 인스턴스로 끝나는 것처럼 해석.
                                                                 //     List 아닌 컬렉션에도 사용할 수 있다.(시퀀스의 동등성)

for (seq <- Seq(                                                 // <8> Seq 를 다른 Seq 에 넣는다. toSeq: (키,값) 튜플 시퀀스로.
    nonEmptySeq, emptySeq, nonEmptyList, emptyList, 
    nonEmptyVector, emptyVector, nonEmptyMap.toSeq, emptyMap.toSeq)) {
  println(seqToString(seq))
}
