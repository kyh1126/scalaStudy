// src/main/scala/progscala2/rounding/traits.sc

// 트레이트(trait): 메서드를 선언하면서 원하면 정의까지 할 수 있는 인터페이스. 트레이트에 인스턴스 필드를 선언하고 원한다면 정의까지 할 수 있다.
//                (정적 필드만 가능한 자바 인터페이스와는 다르다) 또한, 원한다면 타입값을 정의할 수도 있다. 트레이트를 혼합하기 위해 with 키워드 사용.

// trait 를 쓸 때 wine BuyResponseStatus

// BEGIN SERVICE
class ServiceImportante(name: String) {
  def work(i: Int): Int = {
    println(s"ServiceImportante: Doing important work! $i")
    i + 1
  }
}

val service1 = new ServiceImportante("uno")
(1 to 3) foreach (i => println(s"Result: ${service1.work(i)}"))
// END SERVICE

// BEGIN LOGGING
trait Logging {
  def info   (message: String): Unit
  def warning(message: String): Unit
  def error  (message: String): Unit
}

trait StdoutLogging extends Logging {
  def info   (message: String) = println(s"INFO:    $message")
  def warning(message: String) = println(s"WARNING: $message")
  def error  (message: String) = println(s"ERROR:   $message")
}
// END LOGGING

// BEGIN MIXED
val service2 = new ServiceImportante("dos") with StdoutLogging {
  override def work(i: Int): Int = {
    info(s"Starting work: i = $i")
    val result = super.work(i)
    info(s"Ending work: i = $i, result = $result")
    result
  }
}
(1 to 3) foreach (i => println(s"Result: ${service2.work(i)}"))
// END MIXED
