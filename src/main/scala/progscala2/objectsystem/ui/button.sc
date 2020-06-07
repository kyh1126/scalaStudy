// src/main/scala/progscala2/objectsystem/ui/button.sc
import progscala2.objectsystem.ui.Button

val b = new Button("Submit")
// 결과: b: oop.ui.Button = (button: label=Submit, (widget))

b.draw()
// 결과: Drawing: (button: label=Submit, (widget))
