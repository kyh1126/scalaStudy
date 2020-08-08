// src/main/scala/progscala2/objectsystem/ui/Button.scala
package progscala2.objectsystem.ui
import progscala2._9_traits.ui2.ClickableEx8

class Button(val label: String) extends Widget with ClickableEx8 {

  // 보여주기 위해 간단하게 구현
  def draw(): Unit = println(s"Drawing: $this")

  // Clickable에서 가져옴
  protected def updateUI(): Unit = println(s"$this clicked; updating UI")

  override def toString() = s"(button: label=$label, ${super.toString()})"
}

