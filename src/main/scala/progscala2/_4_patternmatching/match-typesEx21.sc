// src/main/scala/progscala2/patternmatching/match-types.sc

// 스칼라는 JVM 위에서 돌고, 여기선 JVM 의 타입 소거로 인해 컴파일러는 주어진 객체가 List 라는걸 알고 있지만,
// 실행 시점에 List[Double], List[String]을 구별할 수 없다.

for {
  x <- Seq(List(5.5, 5.6, 5.7), List("a", "b"))
} yield (x match {
  case seqd: Seq[Double] => ("seq double", seqd)
  case seqs: Seq[String] => ("seq string", seqs)
  case _                 => ("unknown!", x)
})
