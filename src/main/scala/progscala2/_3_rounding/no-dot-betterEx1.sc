// src/main/scala/progscala2/rounding/no-dot-better.sc

// 스칼라에서는 모든 연산자가 메서드이다.
// 매개변수가 없으면 괄호 없이 정의할 수 있는데, 메서드를 호출할 때 괄호를 사용하지 말아야 한다.

def isEven(n: Int) = (n % 2) == 0

List(1, 2, 3, 4).filter((i: Int) => isEven(i)).foreach((i: Int) => println(i))
List(1, 2, 3, 4).filter(i => isEven(i)).foreach(i => println(i))
List(1, 2, 3, 4).filter(isEven).foreach(println)

List(1, 2, 3, 4) filter isEven foreach println

// 이름이 콜론(:)으로 끝나는 메서드는 항상 오른쪽으로 묶인다. 예를 들어 List 의 :: 메서드를 사용해서 원소를 맨 앞에 넣을 수 있다.
val list = List('b', 'c', 'd')

// 아래의 두 연산자는 서로 같은 기능이다.
'a' :: list
list.::('e')