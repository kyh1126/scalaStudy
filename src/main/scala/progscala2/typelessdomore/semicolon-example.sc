// src/main/scala/progscala2/typelessdomore/semicolon-example.sc

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
