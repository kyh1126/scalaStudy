// src/main/scala/progscala2/patternmatching/match-surprise-fix.sc

// 컴파일러는 case 다음에 대문자로 시작하는 이름이 오면 타입 이름으로 간주.
// 소문자 시작은 매치 또는 추출한 값을 담을 변수로 취급.
def checkY(y: Int) = {
  for {
    x <- Seq(99, 100, 101)
  } {
    val str = x match {
      case `y` => "found y!" // 바뀐 것은 `y`(역 작은 따옴표로 둘러쌈) 뿐이다.
      case i: Int => "int: "+i
    }
    println(str)
  }
}
checkY(100)
