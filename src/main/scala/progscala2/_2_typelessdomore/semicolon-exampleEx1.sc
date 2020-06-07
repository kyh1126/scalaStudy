// src/main/scala/progscala2/typelessdomore/semicolon-example.sc

// 세미콜론: 예제를 구분하는 구분자(delimiter). 스칼라는 이를 추론한다. 스칼라는 한 줄의 끝에서 식을 다음 줄로 계속 이어가야 한다고 추론하지 않는 경우,
//         줄 끝을 식의 끝으롤 취급한다. 한 줄에 여러 식을 넣을 때는 세미콜론으로 구분할 수 있다.

// 줄이 등호로 끝나면 다음 줄에 이어지는 코드가 더 있다는 뜻이다.
def equalsign(s: String) =
  println("equalsign: " + s)

// 줄이 중괄호를 열고 끝나면 다음 줄에 이어지는 코드가 더 있다는 뜻이다.
def equalsign2(s: String) = {
  println("equalsign2: " + s)
}

// 줄이 쉼표, 마침표, 연산자로 끝나면 다음 줄에 이어지는 코드가 더 있다는 뜻이다.
def commas(s1: String,
           s2: String) = Console.
  println("comma: " + s1 + 
          ", " + s2)

equalsign("hello")