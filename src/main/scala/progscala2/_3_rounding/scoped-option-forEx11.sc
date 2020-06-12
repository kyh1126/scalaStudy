// src/main/scala/progscala2/patternmatching/scoped-option-for.sc

// Option: 원소가 없거나 하나밖에 없는 특별한 컬렉션으로 생각하면 유용하다.

// 왼쪽 화살표(<-): 다른 컨테이너를 대상으로 반복하면서 값을 가져와야 하는 경우 사용.
// 등호(=): 반복과 관계없이 값을 대입해야 하는 경우 사용.

// for 내장의 제약 사항: 첫번째 식은 반드시 화살표를 사용한 추출, 반복이어야 한다. break, continue 가 없다.

val dogBreeds = List(Some("Doberman"), None, Some("Yorkshire Terrier"), 
                     Some("Dachshund"), None, Some("Scottish Terrier"),
                     None, Some("Great Dane"), Some("Portuguese Water Dog"))
println("first pass:")
for {
  breedOption <- dogBreeds            // 뽑아낸 각 원소는 Option 이다.
  breed <- breedOption                // Option 에 들어있는 값을 빼낸다. for 내장에서 None 처리는 건너뛴다.
                                      // if breedOption != None 이 앞에 추가된 것과 같다.
  upcasedBreed = breed.toUpperCase()
} println(upcasedBreed)

println("second pass:")
for {
  Some(breed) <- dogBreeds            // breedOption 이 Some 인 경우에만 성공.
  upcasedBreed = breed.toUpperCase()
} println(upcasedBreed)
