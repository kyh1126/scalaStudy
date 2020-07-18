import com.sun.rowset.internal.Row
// src/main/scala/progscala2/implicits/java-database-api.scala

// 암시적 인자를 사용하는 시나리오
// 1. 실행 맥락 제공하기: ExecutionContext, transaction, 데이터베이스 연결, thread pool, user session 등에 암시적 인자를 사용하는 것 권장.
//    ex> import scala.concurrent.ExecutionContext.Implicits.global
// 2. 사용 가능한 기능 제어하기
//    def createMenu(implicit session: Session): Menu = {
//      val defaultItems = List(helpItem, searchItem)
//      val accountItems =
//        if(session.loggedin()) List(viewAccountItem, editAccountItem)
//        else List(loginItem)
//      Menu(defaultItems ++ accountItems)
//    }
// 3. 사용 가능한 인스턴스 제한하기 (Ex4)
// +A 는 A 타입에 대해 공변적. B가 A의 서브타입이면 TraversableLike[B]도 TraversableLike[A]의 A의 서브타입이다.
// def getInt 와 같이 다음에 나오는 def get[T] 에 암시적 인자를 넘기고 우리가 원하는 타입하고만 일치시킬 수 있는 암시적 값을 정의하는 방식을 통해
// 매개변수화한 메서드에 사용할 수 있는 타입을 제한했다.
// 4. 암시적 증거 제공하기
// 허용할 타입을 제한하되 각 타입이 공통 슈퍼타입을 따를 필요는 없는 암시적 증거. 타입 매개변수 제약조건인 <: (A <: B)와 같이 암시적 인자는 Predef 에
// 정의된 <:< 타입을 사용.(implicit ev <:< [A, (T,U)])
// 5. 타입 소거 우회하기 (Ex5)
// 암시적 객체가 증거 역할만을 하는 또 다른 예가 타입 소거로 인한 제약을 우회하는 기법.
// 6. 오류 메시지 개선하기
// @implicitNotFound(msg = "Error! to: ${To}, type: ${Elem}, from: ${From}")
// trait CanBuildFrom[-From, -Elem, +To]{...}
// 7. 유령 타입 (Ex6)
// 필요한 타입이 존재하는 경우 이런 암시적 인스턴스를 모두 없애버리는 것. 타입은 있는데 인스턴스 정의는 없는 타입. 타입의 존재 여부만 관심 대상이라는 의미.
// 표식 역할. 실제로는 그 타입의 인스턴스를 전혀 사용하지 않는다.
// 8. 암시적 인자를 처리하기 위한 규칙
// 1. 마지막 인자 목록에만(인자가 단 하나뿐인 메서드의 유일한 인자를 포함해서) 암시적 인자가 들어갈 수 있다.
// 2. implicit 키워드는 인자 목록의 맨 처음에 와야 하며, 오직 한 번만 나타날 수 있다. 인자 목록 안에서 암시적 인자 다음에 비암시적 인자가 따라올 수 없다.
// 3. 인자 목록이 implicit 키워드로 시작하면, 그 인자 목록 안의 모든 인자가 암시적 인자가 된다.

// That: 만들고자 하는 대상 컬렉션의 타입 매개변수. 타입 매개변수는 다를지라도, 입력과 같은 종류의 컬렉션을 만들고 싶을 때 사용.

// 자바와 비슷한 데이터베이스 API. 편의상 스칼라로 만듦.
package progscala2._5_implicits {
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

      // get[T]와 같은 매개변수화한 메서드로 변경 가능
      def getInt   (colName: String): Int    = get(colName).asInstanceOf[Int]
      def getDouble(colName: String): Double = get(colName).asInstanceOf[Double]
      def getText  (colName: String): String = get(colName).asInstanceOf[String]
    }

    object JRow {
      def apply(pairs: (String,Any)*) = new JRow(Map(pairs :_*))
    }
  }
}
