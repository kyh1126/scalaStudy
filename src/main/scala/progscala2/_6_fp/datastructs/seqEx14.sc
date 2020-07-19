// src/main/scala/progscala2/fp/datastructs/seq.sc

// 콘즈 연산자(+:): Seq 의 경우 콘즈 연산자는 :: 이 아니라 +: 다.
// Seq 가 트레이트고 구체적 클래스가 아니기 때문에 동반 객체에 대해 Seq.apply 메서드를 호출하면 List 를 만든다.

// Seq.empty: 빈 꼬리를 정의.

// +: , :+ 가 헷갈리면 : 가 컬렉션이 있는 쪽에 위치하면 된다고 생각하자.
// list :+ x , x +: list 중, 원소를 뒤에 추가할 때는 :+ 를, 앞에 추가할 때는 +: 를 쓴다.

// List 는 변경 불가능, ListBuffer, MutableList 는 변경 가능한 리스트 타입.

val seq1 = Seq("Programming", "Scala")                       // <1>
val seq2 = "People" +: "should" +: "read" +: seq1            // <2>

val seq3 = "Programming" +: "Scala" +: Seq.empty             // <3>
val seq4 = "People" +: "should" +: "read" +: Seq.empty
val seq5 = seq4 ++ seq3                                      // <4>
