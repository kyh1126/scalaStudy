// src/main/scala/progscala2/introscala/upper1.scala
package progscala2._1_introscala

// main 메서드를 제공하는 전통적인 JVM 애플리케이션.
object Upper {
  def main(args: Array[String]) = {
    args.map(_.toUpperCase()).foreach(printf("%s ",_))
    println("")
  }
}
