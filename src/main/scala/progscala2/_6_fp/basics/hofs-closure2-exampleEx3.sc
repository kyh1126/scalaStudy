// src/main/scala/progscala2/fp/basics/hofs-closure2-example.sc

def m1 (multiplier: Int => Int) = {
  (1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)
}

// m2 가 반환한 함수는 실제로는 factor 에 대한 참조까지 포함하는 클로저다.
def m2: Int => Int = {
  val factor = 2
  val multiplier = (i: Int) => i * factor
  multiplier
}

m1(m2)
