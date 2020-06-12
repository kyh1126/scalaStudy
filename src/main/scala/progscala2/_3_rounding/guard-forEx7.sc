// src/main/scala/progscala2/rounding/guard-for.sc

// 가드(guard): if 식을 추가해서 원소를 걸러내고 원하는 원소만 남길 수 있다.

val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
                     "Scottish Terrier", "Great Dane", "Portuguese Water Dog")
for (breed <- dogBreeds
  if breed.contains("Terrier")
) println(breed)
