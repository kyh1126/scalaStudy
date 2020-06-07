// src/main/scala/progscala2/implicits/java-database-api.scala

// 자바와 비슷한 데이터베이스 API. 편의상 스칼라로 만듦.
package progscala2.implicits {
  package database_api {

    case class InvalidColumnName(name: String)
      extends RuntimeException(s"Invalid column name $name")

    trait Row {
      def getInt   (colName: String): Int
      def getDouble(colName: String): Double
      def getText  (colName: String): String
    }
  }

  package javadb {
    import database_api._

    case class JRow(representation: Map[String,Any]) extends Row {
      private def get(colName: String): Any = 
        representation.getOrElse(colName, throw InvalidColumnName(colName))

      def getInt   (colName: String): Int    = get(colName).asInstanceOf[Int]
      def getDouble(colName: String): Double = get(colName).asInstanceOf[Double]
      def getText  (colName: String): String = get(colName).asInstanceOf[String]
    }

    object JRow {
      def apply(pairs: (String,Any)*) = new JRow(Map(pairs :_*))
    }
  } 
}
