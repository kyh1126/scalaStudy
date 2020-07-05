// src/main/scala/progscala2/patternmatching/infix.sc

// case class 의 unapply 메소드
// 객체 생성 시 apply 팩토리 메서드가 들어있는 동반 객체가 만들어지고, unapply 라는 메서드도 자동으로 생긴다.
// Option 리턴인 이유는, 특별한 이유가 있으면 매치에 대해 거부권을 행사해서 None 반환을 허용하기 때문이다.

case class With[A,B](a: A, b: B)

// 타입 매개변수를 둘 받는 타입은 중위 표기법으로 적을 수 있기 때문에 타입 시그니쳐를 아래와 같이 적을수 있다.(with1, with2)
// 하지만 아래와 같이 초기화는 불가능하다.
//val fw1 = "Foo" With 1       // Doesn't work

// But notice the following type annotations:
val with1: With[String,Int] = With("Foo", 1)
val with2: String With Int  = With("Bar", 2)

List(with1, with2) foreach { w =>
  w match {
    case s With i => println(s"$s with $i")
    case _ => println(s"Unknown: $w")
  }
}
