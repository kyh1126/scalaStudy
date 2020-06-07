// src/main/scala/progscala2/patternmatching/match-surprise-fix.sc

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
