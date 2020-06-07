// src/main/scala/progscala2/typelessdomore/person.sc

class Person(val name: String, var age: Int)

val p = new Person("Dean Wampler", 29)

p.name // 이름을 보여준다
p.age  // age 값을 보여준다

p.name = "Buck Trends" // 변경할 수 없다!
p.age = 30             // 변경할 수 있다!

p
