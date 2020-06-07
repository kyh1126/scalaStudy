// src/main/scala/progscala2/introscala/upper2.scala
package progscala2._1_introscala

object Upper2 {
  def main(args: Array[String]) = {
    // 인자 하나만 받는 경우, 컬렉션 원소 사이에 들어갈 구분자로 그 인자를 사용.
//    val output = args.map(_.toUpperCase()).mkString(" ")
    // 인자 3개일 때는 접두사, 구분자, 접미사.
    val output = args.map(_.toUpperCase()).mkString("["," ","]")
    println(output)
  }
}
