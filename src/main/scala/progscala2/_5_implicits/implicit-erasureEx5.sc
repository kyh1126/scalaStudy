// src/main/scala/progscala2/implicits/implicit-erasure.sc

// def m(seq: Seq[Int]): Unit = xxx
// def m(seq: Seq[String]): Unit = xxx
// 두 메서드의 모호성을 제거하기 위해 암시적 인자를 추가할 수 있다.

object M {
  implicit object IntMarker                                      // <1> 타입 소거로 영향받을 두 메서드의 모호성을 제거하기 위해 사용할
  implicit object StringMarker                                   //      특별한 목적의 암시적 객체를 둘 정의한다.

  def m(seq: Seq[Int])(implicit i: IntMarker.type): Unit =       // <2> Seq[Int]를 받는 메서드 오버라이딩. 싱글턴 객체 타입을 참조할
    println(s"Seq[Int]: $seq")                                   //     때 xx.type 구문을 사용한다.

  def m(seq: Seq[String])(implicit s: StringMarker.type): Unit = // <3>
    println(s"Seq[String]: $seq")
}

import M._                                                       // <4> 암시적 값과 메서드를 임포트해서 사용한다.
m(List(1,2,3))
m(List("one", "two", "three"))

