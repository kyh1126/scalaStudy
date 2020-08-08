// src/main/scala/progscala2/traits/ui/button-count-observer.sc
import progscala2._9_traits.ui._
import progscala2._9_traits.observer._

class ButtonCountObserver extends Observer[ButtonEx4] {
  var count = 0
  def receiveUpdate(state: ButtonEx4): Unit = count += 1
}

val button = new ObservableButtonEx5("Click Me!")
val bco1   = new ButtonCountObserver
val bco2   = new ButtonCountObserver

button addObserver bco1
button addObserver bco2

(1 to 5) foreach (_ => button.click())

assert(bco1.count == 5)
assert(bco2.count == 5)
