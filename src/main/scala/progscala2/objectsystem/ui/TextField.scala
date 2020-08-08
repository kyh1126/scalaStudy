// src/main/scala/progscala2/objectsystem/ui/TextField.scala
package progscala2.objectsystem.ui
import progscala2._9_traits.ui2.ClickableEx8

class TextField(var text: String) extends Widget with ClickableEx8 {

    def updateUI() = { /* Logic to update appearance of the text field. */ }

    def draw() = {
        // ... logic to draw the text field on the display, web page, etc.
    }

    override def toString() = "(textfield: text="+text+", "+super.toString()+")"
}

object TextField {
    def unapply(textField: TextField) = Some(textField.text)
}
