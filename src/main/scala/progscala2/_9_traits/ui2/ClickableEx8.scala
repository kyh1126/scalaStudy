// src/main/scala/progscala2/traits/ui2/Clickable.scala
package progscala2._9_traits.ui2  // <1> traits.ui 에 있는 타입을 재구현하고 있기 때문에 새로운 패키지를 선언한다.

trait ClickableEx8 {
  def click(): Unit = updateUI()  // <2> 공개 메서드 click 은 구체적 메서드다.

  protected def updateUI(): Unit  // <3> 보호 메서드며 추상 메서드다. 구현 클래스에서는 이를 구현해야함.
}
