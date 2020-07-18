// src/main/scala/progscala2/fp/curry/curried-func2.sc

// 앞서 살펴본 cat3Curried 타입 시그니처인 String => (String => String) 관련.

// String => String => String 은 String => (String => String) 과 동일. 인자를 하나만 넘기면, 첫 번째 인자를 결부시킨 다음
// String => String 타입의 새로운 함수를 반환한다.

val f1: String =>  String => String  = (s1: String) => (s2: String) => s1 + s2
val f2: String => (String => String) = (s1: String) => (s2: String) => s1 + s2

f1("hello")("world")
// helloworld

f2("hello")("world")
// helloworld

// 커링은 함수를 특정 데이터에 특화시키는 경우 실용적으로 사용할 수 있다. 특별한 경우로 함수를 좁혀나갈 수 있다.
def multiplier(i: Int)(factor: Int) = i * factor
val byFive = multiplier(5) _
byFive(2)
// Int 는 이렇게 해도 뒤에 밑줄 써줘야 하네...
def multiplier2(i: Int) = (factor: Int) => i * factor
val byTen = multiplier(10) _
byTen(2)


val ff1 = Function.uncurried(f1)
val ff2 = Function.uncurried(f2)

ff1("hello", "world")
// helloworld

ff2("hello", "world")
// helloworld