// src/main/scala/progscala2/introscala/upper2.scala
package progscala2._1_introscala

object Upper2 {
  def main(args: Array[String]) = {
    val output = args.map(_.toUpperCase()).mkString(" ")
    println(output)
  }
}
