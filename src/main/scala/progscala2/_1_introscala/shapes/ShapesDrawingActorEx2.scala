// src/main/scala/progscala2/introscala/shapes/ShapesDrawingActor.scala
package progscala2._1_introscala.shapes

// 우편함(mailbox)
// 대부분의 액터 시스템에는 액터가 메시지를 처리할 때까지 보관하는 우편함이 액터별로 있다. 메시지가 비동기이다.
// 아카는 메시지가 도착 순서대로 처리되도록 보장. 어떤 메시지 처리하는 동안 그 메시지를 처리하는 코드가 다른 스레드에 의해 선점되지 않도록 보장.
// 따라서 메시지를 처리하는 코드는 근본적으로 스레드 안전하다.

// 부분 함수(PartialFunction)
// 함수의 리터럴 구문. 인자 타입이 Any, Unit 을 반환한다.(Any: 스칼라 타입 계층의 최상위 클래스) 본문은 순전히 부수 효과를 내는 것이어야 한다.
// 그 함수에 전달된 메시지의 패턴 매칭을 수행하는 case 절로만 구성. 받은 메시지에 대한 함수 인자는 보이지 않는다. 컴파일러가 만들어내는 코드가
// 그런 인자를 내부적으로 처리. 패턴 중 하나가 일치하면, 화살표와 다음 case 사이의 식을 평가한다. 가장 먼저 일치하는 식이 실행된다.

// 중위 표기법(infix notation)
// 메서드가 인자를 하나만 취하는 경우, 객체 뒤에 있는 마침표를 생략하고 인자를 둘러싼 괄호도 생략할 수 있다.
// ex> sender ! Response(s"txt: $s drawn")  : infix notation!
//     sender.!(Response(s"txt: $s drawn"))

// <1> 액터 간 전송할 대부분의 메시지 정의한 객체 선언. 이들은 특정 동작을 일으키는 신호처럼 작용. 한 객체에 메시지 모아두는게 관례.
// <2> Exit, Finished 는 따로 상태가 없다. 이들은 신호를 보내는 flag 역할.
// <3> 메시지를 수신 후 그 메시지 송신한 액터에 임의의 문자열 메시지 응답할 때 사용.
// <4> Actor 타입 임포트. 액터를 정의하기 위해 이 추상 기반 클래스의 서브클래스를 만든다.
// <5> 모양 그릴 액터 정의
// <6> 1 에서 정의한 메시지들 임포트. 스칼라에서는 블록 안에 임포트를 내포시킬 수 있다. (장소 한정)
// <7> 반드시 구현해야 하는 추상 메서드 Actor.receive 구현. 들어오는 메시지 처리하는 방식 정의.
//     이 예시는 함수형 프로그래밍의 패턴 매칭 + 객체지향 프로그래밍의 다형적 호출의 조합 구조.

object Messages {                                                    // <1>
  object Exit                                                        // <2>
  object Finished
  case class Response(message: String)                               // <3>
}

import akka.actor.Actor                                              // <4>

class ShapesDrawingActor extends Actor {                             // <5>
  import Messages._                                                  // <6>

  def receive = {                        // <7>
    case s: Shape =>
      s.draw(str => println(s"ShapesDrawingActor: $str"))
      // 응답을 만들고 그 객체를 모양을 보냈던 송신 액터에 보낸다.
      // Actor.sender 는 현재 처리중인 메시지를 보낸 액터에 대한 참조 반환.
      // !: 비동기 메시지 보내는 메서드. 액터로 유명한 언어인 얼랭(Erlang)에서 가져온 관습.
      sender ! Response(s"ShapesDrawingActor: $s drawn")            // 송신 액터(sender)에 응답 메시지를 보낸다.
    case Exit =>
      println(s"ShapesDrawingActor: exiting...")
      sender ! Finished                                             // 송신 액터(sender)에 Finished 메시지를 보낸다.
    case unexpected =>  // default. Equivalent to "unexpected: Any"
      val response = Response(s"ERROR: Unknown message: $unexpected")
      println(s"ShapesDrawingActor: $response")
      sender ! response                                             // 송신 액터(sender)에 응답을 보낸다.
  }
}
