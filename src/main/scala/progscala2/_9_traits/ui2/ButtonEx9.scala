// src/main/scala/progscala2/traits/ui2/Button.scala
package progscala2._9_traits.ui2
import progscala2._9_traits.ui.Widget

class ButtonEx9(val label: String) extends Widget with ClickableEx8 {

  protected def updateUI(): Unit = { /* GUI 모양을 변경하는 논리 */ }
}
