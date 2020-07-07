// src/main/scala/progscala2/patternmatching/match-types2.sc

// 컬렉션에 대해 먼저 일치시키고, head 원소에 대한 매치를 내포시켜서 head 의 타입으로부터 리스트의 타입을 결정.
// 이렇게 하려면 빈 시퀀스도 따로 처리해야 한다.
def doSeqMatch[T](seq: Seq[T]): String = seq match {
  case Nil => "Nothing"
  case head +: _ => head match {
    case _ : Double => "Double"
    case _ : String => "String"
    case _ => "Unmatched seq element"
  }
}

for {
  x <- Seq(List(5.5,5.6,5.7), List("a", "b"), Nil) 
} yield {
  x match {
    case seq: Seq[_] => (s"seq ${doSeqMatch(seq)}", seq)
    case _           => ("unknown!", x)
  }
}
