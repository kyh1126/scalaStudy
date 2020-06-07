// src/main/scala/progscala2/typesystem/abstracttypes/abstract-types-ex.sc

trait exampleTrait {
  type t1               // t1은 제약이 없음
  type t2 >: t3 <: t1   // t2는 t3의 상위 타입이면서 t1의 하위 타입이어야 함
  type t3 <: t1         // t3는 t1의 하위 타입이어야 함
  type t4 <: Seq[t1]    // t4는 t1의 Seq(원소가 t1 타입인 Seq)의 하위 타입이어야 함
  // type t5 = +AnyRef  // 오류: 변성 표기를 사용할 수 없음

  val v1: t1            // t1이 정의될 때까지 초기화할 수 없음
  val v2: t2            // 마찬가지...
  val v3: t3            // ...
  val v4: t4            // ...
}

trait T1 { val name1: String }
trait T2 extends T1 { val name2: String }
case class C(name1: String, name2: String) extends T2

object example extends exampleTrait {
  type t1 = T1
  type t2 = T2
  type t3 = C
  type t4 = Vector[T1]

  val v1 = new T1 { val name1 = "T1"}
  val v2 = new T2 { val name1 = "T1"; val name2 = "T2" }
  val v3 = C("1", "2")
  val v4 = Vector(C("3", "4"))
}
