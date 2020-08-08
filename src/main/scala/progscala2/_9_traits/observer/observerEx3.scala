// src/main/scala/progscala2/traits/observer/Observer.scala
package progscala2._9_traits.observer

trait Observer[-State] {                             // <1> 상태 변경을 전달받고 싶은 고객을 위한 트레이트. 고객들은 receiverUpdate
  def receiveUpdate(state: State): Unit              //     메서드를 구현해야 한다.
}

trait Subject[State] {                               // <2> 통지를 관찰자에게 보낼 주체를 위한 트레이트.
  private var observers: List[Observer[State]] = Nil // <3> 통지를 받을 관찰자의 목록. 이 목록은 변경 가능하지만 스레드 안전하지 않다.

  def addObserver(observer:Observer[State]): Unit =  // <4> 관찰자를 추가하기 위한 메서드.
    observers ::= observer                           // <5> observer 를 observers 앞에 넣고, 그 결과를 observers 에 대입하라
                                                     //     다음과 같다. observers = observer :: observers
  def notifyObservers(state: State): Unit =          // <6> 상태 변경을 관찰자에게 통지하는 메서드.
    observers foreach (_.receiveUpdate(state))
}
