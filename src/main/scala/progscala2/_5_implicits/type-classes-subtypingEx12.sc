// src/main/scala/progscala2/implicits/type-classes-subtyping.sc

// 암시의 기술적 문제
// 1. 암시 정의 관련 코드를 추가 작업해야 하며, 컴파일러도 암시 처리를 위해 더 많은 작업을 수행해야 한다.(빌드에 오랜 시간이 걸린다)
// 2. 래퍼 타입으로 인한 간접 계층을 한 번 더 거쳐야 하기 때문에 실행 시점에 추가 비용이 든다.
//    일부 메서드를 컴파일러가 인라인할 수 있고, JVM 이 추가 최적화를 할 수 있지만, 정당화할 수 있는 경우에만 그런 추가 비용을 지불하는게 맞다.

// 암시 도움말
// 1. 언제나 암시적 변환 메서드의 반환 타입을 명시하라.
// 2. 컴파일러가 편의를 제공하기 위해 몇 가지 변환을 수행할 수도 있다.
// 3. 컴파일러는 필요한 경우 메서드에 넘기는 인자를 자동 튜플화 한다.

// 암시 해결 규칙
// 1. 경로 접두사가 필요하지 않은, 타입이 호환되는 모든 암시적 값을 검색한다. 다른 말로 하면, 동일한 범위 안, 즉 같은 코드 블록이나 같은 타입 안에 있거나,
//    동반 객체에 있거나, 부모 타입 안에 정의된 값을 검색한다.
// 2. 현재 범위로 임포트한 암시적 값을 검색한다.(이 경우에도 역시 경로 접두사를 사용할 필요가 없다)
//    임포트한 값이 이미 범위 안에 있던 값보다 우선순위가 높다.

// @inline: 컴파일러는 해당 메서드 호출을 인라이닝 해서(메서드 호출 코드 대신 그 메서드 본문을 삽입하는 방식으로) 스택 프레임 낭비 방지한다.
// @noinline: 컴파일러가 메서드 호출을 인라이닝하지 못하게 한다.

trait Stringizer[+T] {
  def stringize: String
}

implicit class AnyStringizer(a: Any) extends Stringizer[Any] {
  def stringize: String = a match {
    case s: String => s
    case i: Int => (i*10).toString
    case f: Float => (f*10.1).toString
    case other => 
      throw new UnsupportedOperationException(s"Can't stringize $other")
  }
}

val list: List[Any] = List(1, 2.2F, "three", 'symbol)

list foreach { (x:Any) => 
  try {
    println(s"$x: ${x.stringize}")
  } catch {
    case e: java.lang.UnsupportedOperationException => println(e)
  }
}
