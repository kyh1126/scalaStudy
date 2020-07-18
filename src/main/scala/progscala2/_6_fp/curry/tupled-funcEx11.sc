// src/main/scala/progscala2/fp/curry/tupled-func.sc

// Function 객체의 tupled: 3원소 튜플을 단일 원자로 받는 메소드.
// untupled: 위의 반대.

def mult(d1: Double, d2: Double, d3: Double) = d1 * d2 * d3
val d3 = (2.2, 3.3, 4.4)
mult(d3._1, d3._2, d3._3)

val multTupled = Function.tupled(mult _)
// multTupled: ((Double, Double, Double)) => Double = <function1>
multTupled(d3)


val multUntupled = Function.untupled(multTupled)
multUntupled(d3._1, d3._2, d3._3)
