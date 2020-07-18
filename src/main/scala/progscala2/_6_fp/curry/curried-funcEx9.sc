// src/main/scala/progscala2/fp/curry/curried-func.sc

def cat1(s1: String)(s2: String) = s1 + s2
// cat1 과 동일하지만, 이 방식 사용하면 커링한 함수 부분 적용하는 경우 뒤에 밑줄을 추가할 필요가 없다.
def cat2(s1: String) = (s2: String) => s1 + s2
def cat3(s1: String, s2: String) = s1 + s2

// 부분 적용: 인자 목록이 둘 이상 있는 함수의 경우, 필요한 인자 중 일부만 적용할 수 있다. 잠재적 모호성 제거하기 위해 뒤에 밑줄을 추가해서
//          컴파일러에 부분 적용 함수를 원한다는 사실을 알려줘야 한다.
val hello = cat1("Hello ") _
hello("World!")

val hello2 = cat2("Hello ")
hello2("World!")

// curried: 여러 인자를 취하는 메서드를 커링한 형태로 바꿀 수 있다.
val cat3Curried = (cat3 _).curried
// cat3Curried: String => (String => String) = <function1>

cat3Curried("hello")("world")
// helloworld

cat3("hello", "world")
// helloworld

val hello3 = cat3Curried("Hello ")
hello3("World!")


// uncurried: Function 객체에 들어 있는 메서드를 사용해서 함수를 언커링 할 수도 있다.
val cat3Uncurried = Function.uncurried(cat3Curried)
// cat3Uncurried: (String, String) => String = <function2>

cat3Uncurried("hello", "world")
// helloworld