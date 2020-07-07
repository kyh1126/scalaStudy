// src/main/scala/progscala2/patternmatching/http.sc

// 봉인된 클래스 계층과 매치의 완전성
// sealed: 변경될 가능성이 높은 경우, 타입 계층을 봉인하지 않아야 한다.
sealed abstract class HttpMethod() {                     // <1> 봉인된 클래스이므로 모든 서브타입은 같은 파일 안에서 정의해야 한다.
    def body: String                                     // <2> 추상 부모 타입에서는 val 대신 인자가 없는 메서드를 정의해서 서브타입
    def bodyLength = body.length                         //     구현자가 이를 메서드로 구현할지 val 로 구현할지 선택하도록 하자!
}

// 패턴 매칭이 필요한 곳에 열거형을 사용하지 말라. 매치가 완전한지 컴파일러가 알려줄 수 없다.
case class Connect(body: String) extends HttpMethod      // <3> body 인수는 val. 이 val 은 추상 def 메서드를 구현.
case class Delete (body: String) extends HttpMethod
case class Get    (body: String) extends HttpMethod
case class Head   (body: String) extends HttpMethod
case class Options(body: String) extends HttpMethod
case class Post   (body: String) extends HttpMethod
case class Put    (body: String) extends HttpMethod
case class Trace  (body: String) extends HttpMethod

def handle (method: HttpMethod) = method match {     // <4> 기본 절은 없지만 완전한 패턴 매치식.
  case Connect (body) => s"connect: (length: ${method.bodyLength}) $body"
  case Delete  (body) => s"delete:  (length: ${method.bodyLength}) $body"
  case Get     (body) => s"get:     (length: ${method.bodyLength}) $body"
  case Head    (body) => s"head:    (length: ${method.bodyLength}) $body"
  case Options (body) => s"options: (length: ${method.bodyLength}) $body"
  case Post    (body) => s"post:    (length: ${method.bodyLength}) $body"
  case Put     (body) => s"put:     (length: ${method.bodyLength}) $body"
  case Trace   (body) => s"trace:   (length: ${method.bodyLength}) $body"
}

val methods = Seq(
  Connect("connect body..."),
  Delete ("delete body..."),
  Get    ("get body..."),
  Head   ("head body..."),
  Options("options body..."),
  Post   ("post body..."),
  Put    ("put body..."),
  Trace  ("trace body..."))

methods foreach (method => println(handle(method)))
