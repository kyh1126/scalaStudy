// src/main/scala/progscala2/rounding/enumeration.sc

// 열거형(enumerated) 타입: 이 안에 들어 있는 값을 열거값(enumeration)이라 한다. 여러 프로그래밍 언어에서는 열거값이 기본 구성 요소다.
//                       하지만 스칼라는 다른 경로를 택해서 열거값을 표준 라이브러리의 Enumeration 에 구현했다. 따라서 바이트 코드 수준에서는
//                       스칼라의 열거값과 자바의 enum 사이에는 아무런 연관이 없다.

// Value 타입과 메서드 사이에는 이름공간의 충돌이 없다. 컴파일러가 값과 메서드에 대해 별도의 이름공간을 유지한다.

// enum 으로 wine OrderType 같이 case class 로 많이 표현했다.
object Breed extends Enumeration {
  type Breed = Value                                      // Value 대신 Breed 를 참조할 수 있기 위한 alias.
  val doberman = Value("Doberman Pinscher")
  val yorkie   = Value("Yorkshire Terrier")
  val scottie  = Value("Scottish Terrier")
  val dane     = Value("Great Dane")
  val portie   = Value("Portuguese Water Dog")
}
import Breed._

// 품종과 품종의 ID 목록을 표시한다.
println("ID\tBreed")
for (breed <- Breed.values) println(s"${breed.id}\t$breed")

// 테리어 품종의 목록을 표시한다.
println("\nJust Terriers:")
Breed.values filter (_.toString.endsWith("Terrier")) foreach println

def isTerrier(b: Breed) = b.toString.endsWith("Terrier")  // 위에서 정의한 Breed 타입은 여기서만 쓰이긴 함.

println("\nTerriers Again??")
Breed.values filter isTerrier foreach println
