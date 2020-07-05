// src/main/scala/progscala2/patternmatching/match-seq-parens.sc

val nonEmptySeq    = Seq(1, 2, 3, 4, 5)
val emptySeq       = Seq.empty[Int]
val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3)

// 시퀀스를 두 개의 case 절과 재귀를 사용해서 처리 가능. 시퀀스는 비어 있거나, 그렇지 않거나 둘 중 하나다.
// 이 패턴으로 분할 정복을 활용할 수 있다.
def seqToString2[T](seq: Seq[T]): String = seq match {
  case head +: tail => s"($head +: ${seqToString2(tail)})"   // <1> 바깥에 괄호를 추가해서 표시 변경.
  case Nil => "(Nil)"
}

for (seq <- Seq(nonEmptySeq, emptySeq, nonEmptyMap.toSeq)) {
  println(seqToString2(seq))
}
