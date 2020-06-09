// src/main/scala/progscala2/typelessdomore/state-capitals-subset.sc

// Option: 어떤 값이 존재하거나 존재하지 않는 경우의 추상 클래스.
// 1. Some: 값이 있는 상황을 표현하는 구체적 서브클래스.
// 2. None: 값이 없는 상황을 표현하는 구체적 서브클래스.

// Map.get 메서드는 Option[T]를 반환. 여기서 get / getOrElse 로 값을 꺼낸다.

// sealed 키워드: 모든 서브클래스가 같은 소스 파일 안에 선언되어야 한다고 컴파일러에 알려준다. (ex> Option)

// 그밖에 사용자가 서브타입을 만들지 못하도록 막고 싶다면, 특정 타입을 final 로 정의.

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  // ...
  "Wyoming" -> "Cheyenne")

println( "Get the capitals wrapped in Options:" )
println( "Alabama: " + stateCapitals.get("Alabama") )
println( "Wyoming: " + stateCapitals.get("Wyoming") )
println( "Unknown: " + stateCapitals.get("Unknown") )

println( "Get the capitals themselves out of the Options:" )
println( "Alabama: " + stateCapitals.get("Alabama").get )
println( "Wyoming: " + stateCapitals.get("Wyoming").getOrElse("Oops!") )
println( "Unknown: " + stateCapitals.get("Unknown").getOrElse("Oops2!") )
