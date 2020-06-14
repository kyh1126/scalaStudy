// src/main/scala/progscala2/rounding/days-enumeration.sc

// Enumeration values: 모든 값을 컬렉션으로 취급.

// 스칼라에선 값의 열거가 필요한 경우 케이스 클래스를 사용. 2가지 이점.
// 1. 케이스 클래스에 메서드와 필드를 유연하게 추가할 수 있고 패턴 매칭을 사용할 수 있다.
// 2. 값을 미리 정해진 집합으로만 한정할 필요가 없다.(다른 케이스 클래스를 추가할 수 있다)

object WeekDay extends Enumeration {
  type WeekDay = Value
  val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
}
import WeekDay._  // 이거 안해주면 WeekDay.Sat 이런식으로 써줘야한다.

def isWorkingDay(d: WeekDay) = ! (d == Sat || d == Sun)

WeekDay.values filter isWorkingDay foreach println
