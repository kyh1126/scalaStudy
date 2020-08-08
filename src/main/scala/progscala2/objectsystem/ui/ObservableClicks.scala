// src/main/scala/progscala2/objectsystem/ui/ObservableClicks.scala
package progscala2.objectsystem.ui
import progscala2._9_traits.ui2.ClickableEx8
import progscala2._9_traits.observer.Subject

trait ObservableClicks extends ClickableEx8 with Subject[ClickableEx8] {
    abstract override def click(): Unit = {
        super.click()
        notifyObservers(this)
    }
}
