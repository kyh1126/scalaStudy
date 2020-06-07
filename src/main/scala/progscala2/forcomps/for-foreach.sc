// src/main/scala/progscala2/forcomps/for-foreach.sc

val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

for {
  s <- states
} println(s)
// 결과: 
// Alabama
// Alaska
// Virginia
// Wyoming

states foreach println
// 결과는 이전과 같음
