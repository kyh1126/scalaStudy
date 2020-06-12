// src/main/scala/progscala2/rounding/scoped-for.sc

// for 내장에 for 식의 앞부분에서 값을 정의하고 뒷부분에서 그 값을 참조할 수 있다.

val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
                     "Scottish Terrier", "Great Dane", "Portuguese Water Dog")
for {
  breed <- dogBreeds
  upcasedBreed = breed.toUpperCase()  // 변경 불가능한 upcasedBreed 지만, val 키워드를 추가할 필요가 없다.
} println(upcasedBreed)
