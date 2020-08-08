// src/main/scala/progscala2/traits/ui/button-count-observer2.sc
import progscala2._9_traits.ui._
import progscala2._9_traits.observer._

val button = new ButtonEx4("Click Me!") with Subject[ButtonEx4] {

  override def click(): Unit = {
    super.click()
    notifyObservers(this)
  }
}

class ButtonCountObserver extends Observer[ButtonEx4] {
  var count = 0
  def receiveUpdate(state: ButtonEx4): Unit = count += 1
}

val bco1   = new ButtonCountObserver
val bco2   = new ButtonCountObserver

button addObserver bco1
button addObserver bco2

(1 to 5) foreach (_ => button.click())

assert(bco1.count == 5)
assert(bco2.count == 5)
println("Success!")
