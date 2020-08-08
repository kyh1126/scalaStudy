// src/main/scala/progscala2/traits/ui/Button.scala
package progscala2._9_traits.ui

class ButtonEx4(val label: String) extends Widget {

  def click(): Unit = updateUI()

  def updateUI(): Unit = { /* GUI 모양을 변경하는 논리 */ }
}
