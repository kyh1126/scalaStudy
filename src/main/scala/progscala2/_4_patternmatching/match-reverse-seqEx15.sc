// src/main/scala/progscala2/patternmatching/match-reverse-seq.sc
// match-seq.sc와 비교해 보라.

// 싱글턴 객체 +:
// 임의의 비어있지 않은 컬렉션에 대해 패턴 매치용. 컴파일러가 case 문을 처리할 때 필요한 unapply 메서드 하나만 들어있다.
// 4를 Nil 앞에 붙이고, 그 결과 리스트 앞에 다시 3을 붙이는 방식.
val list = 1 +: 2 +: 3 +: 4 +: Nil

// ::
// List 에 있는 +: 와 비슷한 객체다.(right bind)

// 원소의 시퀀스를 역방향으로 처리하는 :+
// 마지막 원소에 대해 일치시켜서 역순으로 작업할 수 있다.

val nonEmptyList   = List(1, 2, 3, 4, 5)
val nonEmptyVector = Vector(1, 2, 3, 4, 5)
val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3)

def reverseSeqToString[T](l: Seq[T]): String = l match {
  case prefix :+ end => reverseSeqToString(prefix) + s" :+ $end"
  case Nil => "Nil"
}

for (seq <- Seq(nonEmptyList, nonEmptyVector, nonEmptyMap.toSeq)) {
  println(reverseSeqToString(seq))
}
