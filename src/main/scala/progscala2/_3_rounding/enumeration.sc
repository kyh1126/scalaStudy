// src/main/scala/progscala2/rounding/enumeration.sc

object Breed extends Enumeration {
  type Breed = Value
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

def isTerrier(b: Breed) = b.toString.endsWith("Terrier")

println("\nTerriers Again??")
Breed.values filter isTerrier foreach println
