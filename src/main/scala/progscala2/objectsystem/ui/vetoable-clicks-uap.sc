// src/main/scala/progscala2/objectsystem/ui/vetoable-clicks-uap.sc
import progscala2.objectsystem.ui.Button
import progscala2._9_traits.ui2.{ClickableEx8, ObservableClicksEx10, VetoableClicksEx12}
import progscala2._9_traits.observer._

trait VetoableClicksUAP extends ClickableEx8 {

  def maxAllowed: Int = 1                        // <1>

  private var count = 0

  abstract override def click() = {
    if (count < maxAllowed) {
      count += 1
      super.click()
    }
  }
}

val observableButton =
  new Button("Okay") with ObservableClicksEx10 with VetoableClicksUAP {
    override val maxAllowed: Int = 2             // <2>
  }

assert(observableButton.maxAllowed == 2,
  s"maxAllowed = ${observableButton.maxAllowed}")

class ClickCountObserver extends Observer[ClickableEx8] {
  var count = 0
  def receiveUpdate(state: ClickableEx8): Unit = count += 1
}

val clickCountObserver = new ClickCountObserver
observableButton.addObserver(clickCountObserver)

val n = 5
for (i <- 1 to n) observableButton.click()

assert(clickCountObserver.count == 2,
  s"count = ${clickCountObserver.count}. Should be != $n")
