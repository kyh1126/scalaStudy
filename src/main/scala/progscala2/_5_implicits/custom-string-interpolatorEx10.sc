// src/main/scala/progscala2/implicits/custom-string-interpolator.sc
import scala.util.parsing.json._

// zip: 컬렉션의 zip 메서드는 두 컬렉션의 값을 줄세워 쌍으로 묶는 손쉬운 방법 제공. 여분의 원소는 버려짐.

object Interpolators {
  implicit class jsonForStringContext(val sc: StringContext) {   // <1> 암시적 클래스는 반드시 객체 안에 선언해서 범위 제한.(안전조치)
                                                                 //     이 암시적 클래스 임포트해서 코드 범위 안으로 클래스 구현 가져옴.
    def json(values: Any*): JSONObject = {                       // <2>
      val keyRE = """^[\s{,]*(\S+):\s*""".r                      // <3> 문자열의 일부분에서 키의 이름을 뽑아내기 위한 정규 표현식.
      val keys = sc.parts map {                                  // <4> parts(문자열 일부분의 모음)에서 키 이름을 뽑아낸다.
        case keyRE(key) => key
        case str => str
      }
      val kvs = keys zip values                                  // <5> 키와 값을 zip해서 kvs 쌍 컬렉션에 넣는다.
      JSONObject(kvs.toMap)                                      // <6> 키-값 쌍을 사용해서 Map 만들고 이로 JSONObject 만든다.
    }
  }
}

import Interpolators._

val name = "Dean Wampler"
val book = "Programming Scala, Second Edition"

val jsonobj = json"{name: $name, book: $book}"                   // <7> 우리가 만든 문자열 인터폴레이션을 사용한다.
println(jsonobj)
