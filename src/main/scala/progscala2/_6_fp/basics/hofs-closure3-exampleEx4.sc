// src/main/scala/progscala2/fp/basics/hofs-closure3-example.sc

// Multiplier 가 메서드지만, 그냥 함수와 마찬가지로 사용할 수 있다. 이 메서드가 this 를 참조하지 않기 때문.
// 끌어올렸다: 어떤 메서드를 함수가 필요한 곳에 사용할 수 있는 경우, 스칼라가 메서드를 함수로 끌어올렸다고 한다.

object Multiplier {
  var factor = 2
  // Compare: val multiplier = (i: Int) => i * factor
  def multiplier(i: Int) = i * factor
}

(1 to 10) filter (_ % 2 == 0) map Multiplier.multiplier reduce (_ * _)

Multiplier.factor = 3
(1 to 10) filter (_ % 2 == 0) map Multiplier.multiplier reduce (_ * _)

