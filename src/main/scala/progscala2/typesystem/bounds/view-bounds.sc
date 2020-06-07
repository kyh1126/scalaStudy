// src/main/scala/progscala2/typesystem/bounds/view-bounds.sc
import scala.language.implicitConversions

object Serialization {
  case class Writable(value: Any) {
    def serialized: String = s"-- $value --"                         // <1>
  }

  implicit def fromInt(i: Int) = Writable(i)                         // <2>
  implicit def fromFloat(f: Float) = Writable(f)
  implicit def fromString(s: String) = Writable(s)
}

import Serialization._

object RemoteConnection {                                            // <3>
  def write[T <% Writable](t: T): Unit =                             // <4>
    println(t.serialized)  // 표준 출력을 '원격 연결' 대신 사용
}

RemoteConnection.write(100)       // 출력: -- 100 --
RemoteConnection.write(3.14f)     // 출력: -- 3.14 --
RemoteConnection.write("hello!")  // 출력: -- hello! --
// RemoteConnection.write((1, 2))                                       <5>
