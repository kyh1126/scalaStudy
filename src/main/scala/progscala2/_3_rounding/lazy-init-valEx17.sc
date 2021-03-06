// src/main/scala/progscala2/rounding/lazy-init-val.sc

// lazy 키워드: 값이 실제 필요할 때까지 계산을 유예. 초기화 본문은 해당 값을 처음으로 사용하는 순간 오직 한번만 평가된다.
//            한 번만 평가하는 것은 변경 가능 필드의 경우 그리 의미가 없다. 따라서 lazy 키워드를 var 에 사용할 수 없다.
// 메서드 호출: 매번 본문(body)을 실행한다.

// 모든 객체의 필드를 lazy 로 만들지 않는 것은 진짜로 비용이 비싼 경우가 아니라면 속도가 더 빠르지 않기 때문.
// 지연값은 가드(guard)로 구현한다. 지연값을 참조하는 경우, 해당 참조를 가드가 가로채서 초기화가 필요한지 검사한다.(이후 호출에서 이런 검사를 없앨 수 없다)
// 따라서 지연값은 미리 계산한 값에는 없는 부가 비용이 든다. 가드를 사용하는 데 따른 비용이 초기화 비용에 비해 무시할 만하거나,
// 값을 초기화할 때 상호 의존관계가 복잡해서 지연값으로 이를 구현하는 것이 가장 쉬운 경우에만 지연값을 사용해야 한다.
object ExpensiveResource {
  // lazy val == def 같다.
  lazy val resource: Int = init()
//  def resource: Int = init()
  def init(): Int = { 
    // do something expensive
    0
  }
}
