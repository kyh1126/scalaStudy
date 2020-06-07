// src/main/scala/progscala2/introscala/shapes/ShapesActorDriver.scala
package progscala2._1_introscala.shapes
import akka.actor.{Props, Actor, ActorRef, ActorSystem}
import com.typesafe.config.ConfigFactory

// <1> 모든 동작을 시작하기 위해 이 파일에서만 사용할 메시지 private 정의. 특별한 시작 메시지 사용은 액터 프로그래밍에서 흔히 쓰는 관용적 표현.
// <2> 드라이버(driver) 액터 만든다.
// <3> ActorSystem 을 만들고 Ex2 액터와 5 액터를 만든다. Ex2 를 2 에 넘겨야 할 필요가 있다. 실제로는 실제 액터 인스턴스를 가리키는 액터 참조인
//     ActorRef 를 넘긴다.
// <4> Start 를 드라이버에 보내서 시작한다.
// <5> ShapesDrawingDriver 액터 정의
// <6> receive 핸들러가 Start 메시지 받으면, Ex2 에 5개의 비동기 메시지 보낸다. 따라서 이 액터 시스템은 단기간 작동.
// <7> Exit 의 응답으로 도착시 Actor 의 context 필드를 통해 접근해서 액터 시스템을 종료시킨다.
// <9> 예상치 못한 메시지 도착할 경우의 기본 case 절.

// 이 파일 안에서만 사용하는 메시지
private object Start                                                 // <1>

object ShapesDrawingDriver {                                         // <2>
  def main(args: Array[String]) {                                    // <3>
    val system = ActorSystem("DrawingActorSystem", ConfigFactory.load())
    val drawer = system.actorOf(
      Props(new ShapesDrawingActor), "drawingActor")
    val driver = system.actorOf(
       Props(new ShapesDrawingDriver(drawer)), "drawingService")
    driver ! Start                                                   // <4>
  }
}

class ShapesDrawingDriver(drawerActor: ActorRef) extends Actor {     // <5>
  import Messages._

  def receive = {
    case Start =>                                                    // <6>
      drawerActor ! Circle(Point(0.0,0.0), 1.0)
      drawerActor ! Rectangle(Point(0.0,0.0), 2, 5)
      drawerActor ! 3.14159
      drawerActor ! Triangle(Point(0.0,0.0), Point(2.0,0.0), Point(1.0,2.0))
      drawerActor ! Exit
    case Finished =>                                                 // <7>
      println(s"ShapesDrawingDriver: cleaning up...")
      context.system.shutdown()
    case response: Response =>                                       // <8>
      println("ShapesDrawingDriver: Response = " + response)
    case unexpected =>                                               // <9>
      println("ShapesDrawingDriver: ERROR: Received an unexpected message = "
        + unexpected)
  }
}
