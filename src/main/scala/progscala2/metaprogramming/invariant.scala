// src/main/scala/progscala2/metaprogramming/invariant.scala
package metaprogramming
import scala.language.experimental.macros                            // <1>
import scala.reflect.macros.blackbox.Context                         // <2>

/**
 * 현재의 매크로 문법과 의사인용을 활용해서 작성한 매크로.
 * 불변조건으로 사용할 각 식을 평가하기 전에 
 * 항상 참인지 검사할 술어가 필요하다.
 */
object invariant {                                                   // <3>
  case class InvariantFailure(msg: String) extends RuntimeException(msg)

  def apply[T](predicate: => Boolean)(block: => T): T = macro impl   // <4>

  def impl(c: Context)(predicate: c.Tree)(block: c.Tree) = {         // <5>
    import c.universe._                                              // <6>
    val predStr = showCode(predicate)                                // <7>
    val q"..$stmts" = block                                          // <8>
    val invariantStmts = stmts.flatMap { stmt =>                     // <9>
      val msg = s"FAILURE! $predStr == false, for statement: " + showCode(stmt)
      val tif = q"throw new metaprogramming.invariant.InvariantFailure($msg)"
      val predq2 = q"if (false == $predicate) $tif"
      List(q"{ val tmp = $stmt; $predq2; tmp };")
    }
    val tif = q"throw new metaprogramming.invariant.InvariantFailure($predStr)"
    val predq = q"if (false == $predicate) $tif"
    q"$predq; ..$invariantStmts"                                     // <10>
  }
}
