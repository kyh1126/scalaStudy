// src/main/scala/progscala2/forcomps/for-validations-good.sc
import scalaz._, std.AllInstances._

def positive(i: Int): Validation[List[String], Int] = {
  if (i > 0) Success(i)                              // <1> scalaz.Validation 의 서브클래스 Success, Failure
  else Failure(List(s"Nonpositive integer $i"))
}

for {
  i1 <- positive(5)
  i2 <- positive(10 * i1)
  i3 <- positive(25 * i2)
  i4 <- positive(2  * i3)
} yield (i1 + i2 + i3 + i4)
// 반환: scalaz.Validation[List[String],Int] = Success(3805)

for {
  i1 <- positive(5)
  i2 <- positive(-1 * i1)              // 엄청난 실패! 
  i3 <- positive(25 * i2)
  i4 <- positive(-2 * i3)              // 엄청난 실패!
} yield (i1 + i2 + i3 + i4)
// 반환: scalaz.Validation[List[String],Int] =
//   Failure(List(Nonpositive integer -5))       // <2> for 내장을 쓰기 때문에 여전히 쇼트서킷으로 평가가 이뤄진다.
                                                 //     따라서 i4에 대한 마지막 오류는 보이지 않는다.

positive(5) +++ positive(10) +++ positive(25)    // <3> positive에 대한 모든 호출을 평가한다. 그 후 결과를 더하거나 오류를 모은다.
// 반환: scalaz.Validation[String,Int] = Success(40)

positive(5) +++ positive(-10) +++ positive(25) +++ positive(-30) // <4> 두 오류를 모두 받는다.
// 반환: scalaz.Validation[String,Int] =
//   Failure(Nonpositive integer -10, Nonpositive integer -30)
