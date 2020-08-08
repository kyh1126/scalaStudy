// src/main/scala/progscala2/traits/ui2/ObservableClicks.scala
package progscala2._9_traits.ui2
import progscala2._9_traits.observer._

// Clickable 을 관찰하는 것에만 집중하는 트레이트.
trait ObservableClicksEx10 extends ClickableEx8 with Subject[ClickableEx8] {
  abstract override def click(): Unit = {    // <1> super 는 현재에 실제 인스턴스와 연결될 수 없다는 뜻. 구체적 인스턴스에 혼합될 때 연결됨.
    super.click()
    notifyObservers(this)
  }
}
