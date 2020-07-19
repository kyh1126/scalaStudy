// src/main/scala/progscala2/fp/datastructs/package.scala
// 패키지당 하나의 패키지 객체만 있을 수 있다.

package progscala2._6_fp
package object datastructs {
  type Seq[+A] = scala.collection.immutable.Seq[A]  // Seq 가 immutable.Seq 를 가리키게 만든다.
  val Seq = scala.collection.immutable.Seq
}
