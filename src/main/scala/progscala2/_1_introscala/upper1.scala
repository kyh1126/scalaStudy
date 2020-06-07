// src/main/scala/progscala2/introscala/upper1.scala
package progscala2._1_introscala

// 스칼라의 main: 스칼라에서 main 은 object 에 정의한 메서드여야 한다.(자바에서 main 은 class 에 정의한 static 메서드여야 한다)
//              이 애플리케이션의 명령행 인자는 문자열 배열로 main 에 전달된다.(ex> args: Array[String])

// main 메서드를 제공하는 전통적인 JVM 애플리케이션.
// object 의 main 메서드는 자바 클래스의 static main 과 같다. 이 메서드는 Upper 애플리케이션의 진입 지점이다.
object Upper {
  def main(args: Array[String]) = {
    // map 이 반환한 새 컬렉션을 foreach 를 사용해서 반복한다. 이 안에 넘길 다른 함수 리터럴 안에서도 _ 위치지정자 사용되어
    // 컬렉션에 속한 모든 문자열이 printf 인자로 전달된다.
    args.map(_.toUpperCase()).foreach(printf("%s ",_))
    println("")
  }
}

// run 으로 컴파일해서 실행해도 되지만, sbt shell 에서
// run-main progscala2._1_introscala.Upper Hello world! 해도 된다. (SBT 빌드 명령이 대신 컴파일해줌)

// scala 명령을 사용하는 경우, SBT 가 클래스 파일을 저장하는 디렉터리를 클래스 경로에 넣어야 한다.
// scala -cp target/scala-2.11/classes progscala2._1_introscala.Upper Hello world!

// 현재 intelliJ Scala 스크립트 환경설정이 JDK 11 로 되어있어서 1.8로 셋팅시 에러난다.
// 근데 run 할 땐 dependency 때문에 1.8로 해줘야함.. 나중에 환경좀 맞추자.
