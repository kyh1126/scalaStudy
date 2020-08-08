// src/main/scala/progscala2/traits/ui/ObservableButton.scala
package progscala2._9_traits.ui
import progscala2._9_traits.observer._

class ObservableButtonEx5(name: String)               // <1> 관찰 가능성을 혼합한 Button 의 서브클래스.
    extends ButtonEx4(name) with Subject[ButtonEx4] { // <2> Button 을 확장하고 Subject 를 혼합한다. 이때 Subject 선언에 있는 State
                                                      //     라는 이름의 타입 매개변수로 Button 을 지정한다.
  override def click(): Unit = {                      // <3> 관찰자에게 통지하기 위해 click 메서드 오버라이딩.
    super.click()                                     // <4> 먼저 부모 클래스의 click 호출해서 일반적인 GUI 갱신을 실시한다.
    notifyObservers(this)                      // <5> this 를 State 로 넘기면서 관찰자에게 통지.
  }
}
