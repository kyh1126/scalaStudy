// src/main/scala/progscala2/patternmatching/scoped-option-for.sc

// 패턴 매칭을 for 내포에서 사용한다.

val dogBreeds = Seq(Some("Doberman"), None, Some("Yorkshire Terrier"), 
                    Some("Dachshund"), None, Some("Scottish Terrier"),
                    None, Some("Great Dane"), Some("Portuguese Water Dog"))

println("second pass:")
for {
  Some(breed) <- dogBreeds
  upcasedBreed = breed.toUpperCase()
} println(upcasedBreed)
