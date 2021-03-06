// src/main/scala/progscala2/patternmatching/match-variable.sc

// 2. 특정 값이나 특정 타입의 모든 값과 매치, 모든 값에 일치하는 '기본'절을 쓰는 방법.
//    하지만 PartialFunction 을 만들 때는 가능한 값 중 일부분만 처리하는 것이 의도기 때문에 모든 가능한 값에 대해
//    매치시킬 필요는 없다.
for {
  x <- Seq(1, 2, 2.7, "one", "two", 'four)                   // <1> 여러 타입과 값이 섞여있어 Seq[Any] 타입의 목록 사용.
} {
  // 컴파일러는 모든 case 절이 반환하는 값의 가장 가까운 슈퍼타입(최소 상위 바운드)을 추론한다.
  val str = x match {                                        // <2> x는 Any 타입.
    case 1          => "int 1"                               // <3> x가 1인 경우 일치.
    case i: Int     => "other int: "+i                       // <4> 다른 Int 와 일치. 안전하게 x를 Int 변환하고 i에 대입.
    case d: Double  => "a double: "+d                        // <5> Double 과 일치. x를 Double 변수 d에 대입.
    case "one"      => "string one"                          // <6> 문자열 'one'과 일치.
    case s: String  => "other string: "+s                    // <7> 다른 문자열과 일치. x를 String 변수 s에 대입.
    case unexpected => "unexpected value: " + unexpected     // <8> '기본'절 역할을 한다.
  }
  println(str)                                               // <9> 돌려받은 문자열 출력.
}

