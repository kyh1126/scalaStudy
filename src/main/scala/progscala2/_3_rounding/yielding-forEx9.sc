// src/main/scala/progscala2/rounding/yielding-for.sc

// yield 키워드: for 식에서 새로운 컬렉션을 만든다.

// for 내장에 식이 하나만 들어가면 괄호, 여러 식이 들어가면 중괄호 사용. 괄호를 사용할 경우 중간에 세미콜론(;)을 추가해야 한다.

val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
                     "Scottish Terrier", "Great Dane", "Portuguese Water Dog")
// for 식을 매번 반복하면서 걸러낸 결과를 breed 라는 값으로 만들어낸다. 매 루프마다 누적되며, 결과 컬렉션은 filteredBreeds 라는 값에 저장된다.
val filteredBreeds = for {
  breed <- dogBreeds
  if breed.contains("Terrier") && !breed.startsWith("Yorkshire")
} yield breed

print(filteredBreeds)