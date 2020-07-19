// src/main/scala/progscala2/fp/datastructs/map.sc

// 맵: 키와 값의 쌍을 저장하기 위해 사용.

// + : 하나 이상의 키-값 쌍을 추가할 수 있다(새로운 Map 생김)

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")

val lengths = stateCapitals map { 
  kv => (kv._1, kv._2.length) 
}
println(lengths)

val caps = stateCapitals map { 
  case (k, v) => (k, v.toUpperCase)
}
println(caps)

// 괄호 꼭 해줘야 한다. 아니면 맵Virginia -> Richmond 같은 형태가 된다.
val stateCapitals2 = stateCapitals + ("Virginia" -> "Richmond")
println(stateCapitals2)

val stateCapitals3 = stateCapitals2 + (
  "New York" -> "Albany", "Illinois" -> "Springfield")
println(stateCapitals3)
