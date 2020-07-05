// src/main/scala/progscala2/patternmatching/match-seq-unapplySeq.sc

// Seq 의 동반 객체를 보면 apply 와 unapplySeq 정의는 있지만, unapply 는 없다.

val nonEmptyList   = List(1, 2, 3, 4, 5)         // <1>
val emptyList      = Nil
val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3)

// 한 쌍씩 처리한다.
def windows[T](seq: Seq[T]): String = seq match {
  case Seq(head1, head2, _*) =>                  // <2> Seq.apply 를 호출하는 것 같아 보이지만 매치 절에서 Seq.unapplySeq 를 호출하고 있다.
    s"($head1, $head2), " + windows(seq.tail)    // <3> 매치에서 꼬리를 잡아내지 않았고, seq.tail 로 '윈도우'를 하나 뒤로 이동시켰다.
  case Seq(head, _*) => 
    s"($head, _), " + windows(seq.tail)          // <4> 원소가 하나인 경우 잡아낸다. 매치의 완성을 위하여. 여기의 seq.tail 은 Nil 리턴
                                                 //     하지만 문자열 중복보다는 windows 메서드 한번 더 호출해서 얻는 성능상의 이점을 택했다.
  case Nil => "Nil"
}

for (seq <- Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq)) {
  println(windows(seq))
}
