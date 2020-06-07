// src/main/scala/progscala2/traits/ui2/VetoableClicks.scala
package progscala2.traits.ui2
import progscala2.traits.observer._

trait VetoableClicks extends Clickable {                             // <1>
  // 허용 가능한 최대 눌림 횟수의 기본값
  val maxAllowed = 1                                                 // <2>
  private var count = 0

  abstract override def click() = {
    if (count < maxAllowed) {                                        // <3>
      count += 1
      super.click()
    }
  }
}
