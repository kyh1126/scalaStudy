// src/main/scala/progscala2/rounding/while.sc
// 경고: 이 스크립트는 아주 긴 시간동안 루프를 돈다!
import java.util.Calendar

def isFridayThirteen(cal: Calendar): Boolean = {
  val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
  val dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)

  // 스칼라 메서드는 마지막 식의 결과를 반환한다.
  (dayOfWeek == Calendar.FRIDAY) && (dayOfMonth == 13)
}

while (!isFridayThirteen(Calendar.getInstance())) {
  println("Today isn't Friday the 13th. Lame.")
  // 하루 동안 아무 것도 안 한다. 
  Thread.sleep(86400000)
}
