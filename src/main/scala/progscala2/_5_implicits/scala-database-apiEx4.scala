// src/main/scala/progscala2/implicits/scala-database-api.scala

// implicit SRow 에 jrow 인스턴스를 참조할 수 있는 것은 클래스 본문의 범위 안에 들어있기 때문이다.

// 자바와 비슷한 데이터베이스 API에 대한 스칼라 레퍼
package progscala2._5_implicits {
    package scaladb {
    object implicits {
      import javadb.JRow

      // 암시적 객체를 사용해서 공통 슈퍼타입이 없는 경우에도 허용하는 타입을 제한할 수 있다. API 의 작동을 돕기위해 사용할 수 있다.
      implicit class SRow(jrow: JRow) {
        def get[T](colName: String)(implicit toT: (JRow,String) => T): T =
          toT(jrow, colName)
      }

      implicit val jrowToInt: (JRow,String) => Int = 
        (jrow: JRow, colName: String) => jrow.getInt(colName)
      implicit val jrowToDouble: (JRow,String) => Double = 
        (jrow: JRow, colName: String) => jrow.getDouble(colName)
      implicit val jrowToString: (JRow,String) => String = 
        (jrow: JRow, colName: String) => jrow.getText(colName)
    }

    object DB {
      import implicits._

      def main(args: Array[String]) = {
        val row = javadb.JRow("one" -> 1, "two" -> 2.2, "three" -> "THREE!")

        val oneValue1: Int      = row.get("one")
        val twoValue1: Double   = row.get("two")
        val threeValue1: String = row.get("three")
        // val fourValue1: Byte    = row.get("four")  // 컴파일 안됨

        println(s"one1   -> $oneValue1")
        println(s"two1   -> $twoValue1")
        println(s"three1 -> $threeValue1")

        val oneValue2   = row.get[Int]("one")
        val twoValue2   = row.get[Double]("two")
        val threeValue2 = row.get[String]("three")
        // val fourValue2    = row.get[Byte]("four")  // 컴파일 안됨

        println(s"one2   -> $oneValue2")
        println(s"two2   -> $twoValue2")
        println(s"three2 -> $threeValue2")
      }
    }
  }
}
