// src/main/scala/progscala2/forcomps/for-options-good.sc

def positive(i: Int): Option[Int] = 
  if (i > 0) Some(i) else None

for {
  i1 <- positive(5)
  i2 <- positive(10 * i1)
  i3 <- positive(25 * i2)
  i4 <- positive(2  * i3)
} yield (i1 + i2 + i3 + i4)
// 반환: Option[Int] = Some(3805)

for {
  i1 <- positive(5)
  i2 <- positive(-1 * i1)              // <1> 엄청난 실패!
  i3 <- positive(25 * i2)              // <2>
  i4 <- positive(-2 * i3)              // 엄청난 실패! 
} yield (i1 + i2 + i3 + i4)
// 반환: Option[Int] = None
