// src/main/scala/progscala2/typelessdomore/futures.sc
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
// 자바의 ForkJoinPool 기능을 사용해서 자바 스레드를 관리하는 기본 ExecutionContext 를 가져온다.
// Execution 명시하지 않고 ExecutionContext 가 필요한 메서드(apply, onSuccess, onFailure)호출 시 컴파일러는 이를 사용.

// Future: 스칼라가 제공하는 또 다른 동시성 도구. 아카가 Future 사용하지만, 액터의 모든 기능이 필요하지 않은 경우 Future 만 별도 사용 가능.
//         수행할 작업 중 일부를 Future 로 감싸면 그 작업 비동기적으로 수행. 결과가 준비된 경우 콜백 호출해주는 등 결과를 처리할 수 있는 다양한 방법 제공.
//         동시 연산을 어떻게 실행할지 ExecutionContext 를 지정해서 설정할 수 있다.

// implicit: 컴파일러는 implicit 로 선언되고 현재 영역에서 볼 수 있는 객체만 사용한다. 또한, 함수 인자 중 implicit 선언된 것을 생략한 경우만 이런 변환 수행.

def sleep(millis: Long) = {
  Thread.sleep(millis)
}

// 쓸모는 없는데 바쁜 일 ;)
def doWork(index: Int) = {
  sleep((math.random * 1000).toLong)
  index
}

// Wine, inboxDetailService 참고
(1 to 5) foreach { index =>
  val future = Future {   // Future.apply(Future 싱글턴 객체에 있는 팩토리 메서드) 호출.
    doWork(index)         // 익명 함수 전달. 인자가 없는 익명 함수 전달하기 위해 괄호 대신 중괄호 사용.
  }                       // 새로운 Future 객체를 반환하며, 그 객체는 doWork 본문을 새로운 다른 스레드에서 실행한다.(제어는 즉시 루프로 돌아온다)
  future onSuccess {      // Future 가 성공적으로 완료하는 경우 호출될 콜백 등록. 콜백 함수는 PartialFunction
    case answer: Int => println(s"Success! returned: $answer")
  }
  future onFailure {      // 실패 시 처리할 콜백 등록. Throwable 에 캡슐화된다.
    case th: Throwable => println(s"FAILURE! returned: $th")
  }
}

sleep(1000)  // '작업' 이 끝날 때까지 충분히 기다린다.
println("Finito!")