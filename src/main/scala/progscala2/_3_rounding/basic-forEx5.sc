// src/main/scala/progscala2/rounding/basic-for.sc

// 스칼라 for 내장: 내장(comprehension)이라는 용어는 함수형 프로그래밍에서 온 것. 하나 이상의 컬렉션을 순회하면서, 그 안에서 찾아낸 것을
//               이해(comprehending)하고, 그로부터 새로운 무언가를 계산한다는 생각을 표현한다.

val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
                     "Scottish Terrier", "Great Dane", "Portuguese Water Dog")

for (breed <- dogBreeds)
  println(breed)
