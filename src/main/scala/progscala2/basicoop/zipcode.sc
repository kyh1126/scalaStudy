// src/main/scala/progscala2/basicoop/Zipcode.sc
import progscala2.basicoop.ZipCode

ZipCode(12345)
// 결과: ZipCode = 12345

ZipCode(12345, Some(6789))
// 결과: ZipCode = 12345-6789

ZipCode(12345, 6789)
// 결과: ZipCode = 12345-6789

try {
  ZipCode(0, 6789)  // 잘못된 Zip+4 형식의 우편번호: 0-6789
} catch {
  case e: java.lang.IllegalArgumentException => e
}

try {
  ZipCode(12345, 0)  // 잘못된 Zip+4 형식의 우편번호: 12345-0
} catch {
  case e: java.lang.IllegalArgumentException => e
}
