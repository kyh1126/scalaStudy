// src/main/scala/progscala2/typelessdomore/person.sc

// val: 변경 불가능한 변수. 값 객체(value object). 선언 시 반드시 초기화해야 한다.
// var: 변경 가능한 변수. 나중에 바꿀 수 있음에도 선언 시 초기화해야 한다.
// -> 어떤 참조가 다른 객체를 참조하도록 변경될 수 있는지 없는지 여부만 지정한다. 참조가 가리키는 대상 객체의 내부 상태를 변경 가능한지 여부는 지정하지 않는다.

// Ex>
// val array: Array[String] = new Array(5)
// array = new Array(2)
// array(0) = "Hello"
// array

// stockPrice 자체의 값을 바꿨다. Double 이 불변이라 가리키는 객체를 변경할 수는 없다. 스칼라는 이런 타입도 참조 타입과 마찬가지로 실제 메서드가 있는 객체다.
// (자바의 기본타입은 객체도 참조도 존재하지 않는다)
// var stockPrice: Double = 100.0
// stockPrice = 200.0

// val, var 선언 시 반드시 초기화 예외: 타입의 생성자 매개변수에 val, var 을 사용할 수 있다. 그러면 매개변수가 그 타입의 필드가 된다.

class Person(val name: String, var age: Int)

val p = new Person("Dean Wampler", 29)

p.name // 이름을 보여준다
p.age // age 값을 보여준다

p.name = "Buck Trends" // 변경할 수 없다!
p.age = 30 // 변경할 수 있다!

p
