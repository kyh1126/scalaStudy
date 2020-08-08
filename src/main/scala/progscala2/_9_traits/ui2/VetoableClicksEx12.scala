// src/main/scala/progscala2/traits/ui2/VetoableClicks.scala
package progscala2._9_traits.ui2
import progscala2._9_traits.observer._

trait VetoableClicksEx12 extends ClickableEx8 { // <1>
  // 허용 가능한 최대 눌림 횟수의 기본값
  val maxAllowed = 1                            // <2> 허용하는 눌림 횟수의 최댓값
  private var count = 0

  abstract override def click() = {
    if (count < maxAllowed) {                   // <3> 최대 횟수를 넘어서면 더 이상의 이벤트를 super 에 보내지 않는다.
      count += 1
      super.click()
    }
  }
}
