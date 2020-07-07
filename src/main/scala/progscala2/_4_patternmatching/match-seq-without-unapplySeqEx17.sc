// src/main/scala/progscala2/patternmatching/match-seq-without-unapplySeq.sc

// 앞서 본 슬라이딩 윈도우 출력의 +: 방식
val nonEmptyList = List(1, 2, 3, 4, 5)
val emptyList = Nil
val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)

// Process pairs
def windows2[T](seq: Seq[T]): String = seq match {
  case head1 +: head2 +: tail => s"($head1, $head2), " + windows2(seq.tail)
  case head +: tail => s"($head, _), " + windows2(tail)
  case Nil => "Nil"
}

for (seq <- Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq)) {
  println(windows2(seq))
}

// Seq 의 sliding 메서드 두 가지: 반복자를 반환한다. 반환값이 지연값이어서 즉시 리스트의 복사본을 만들지 않는다.
// 1. toSeq: 반복자는 머리는 미리 계산해두되 꼬리는 필요할 때만 계산하는 지연리스트 Stream 으로 변환한다.
// 2. toList: 전체 반복자를 즉시 계산해서 리스트를 만든다.
val seq = Seq(1, 2, 3, 4, 5)
val slide2 = seq.sliding(2)
slide2.toSeq
slide2.toList
seq.sliding(3, 2).toList