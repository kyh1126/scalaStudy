// src/main/scala/progscala2/basicoop/Complex.sc

// 단항 연산자 구현하기

case class Complex(real: Double, imag: Double) {
  def unary_- : Complex = Complex(-real, imag)               // <1> 단항 연산자를 인스턴스 앞에 위치시킬 수 있다.
  def -(other: Complex) = Complex(real - other.real, imag - other.imag)
}

val c1 = Complex(1.1, 2.2)
val c2 = -c1                           // Complex(-1.1, 2.2)
val c3 = c1.unary_-                    // Complex(-1.1, 2.2)
val c4 = c1 - Complex(0.5, 1.0)        // Complex(0.6, 1.2)
