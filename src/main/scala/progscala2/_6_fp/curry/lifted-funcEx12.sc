// src/main/scala/progscala2/fp/curry/lifted-func.sc

// 함수 끌어올리기: 부분 함수가 있고, 그 함수에서 예외가 발생하는 것을 바라지 않는다면, 해당 함수를 끌어올려서 Option 을 반환하도록 만든다.
// 함수 끌어내리기: 옵션을 반환하는 함수를 부분 함수로 바꾸기.
val finicky: PartialFunction[String,String] = {
  case "finicky" => "FINICKY"
}

val finickyOption = finicky.lift

finicky("finicky")
try {
  finicky("other")
} catch {
  case e: scala.MatchError => e
}

finickyOption("finicky")  // Some(FINICKY) 반환.
finickyOption("other")    // None 반환.


val finicky2 = Function.unlift(finickyOption)

finicky2("finicky")
try {
  finicky2("other")
} catch {
  case e: scala.MatchError => e
}
