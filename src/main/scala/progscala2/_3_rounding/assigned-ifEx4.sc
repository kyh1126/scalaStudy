// src/main/scala/progscala2/rounding/assigned-if.sc

// 스칼라 if문: 스칼라 if 문은 자바와 달리 값을 결과로 돌려준다. 리턴 값의 타입은 최소 상위 바운드(least upper bound) - 각 절(가지)의 모든 잠재적인 값에 대응하는 가장 가까운 부모 타입.
//            if 의 결과값을 다른 변수에 저장할 수 있다. 식이므로 3항 조건식은 중복 가능이므로 지원하지 않는다.

val configFile = new java.io.File("somefile.txt")

val configFilePath = if (configFile.exists()) {
  configFile.getAbsolutePath()
} else {
  configFile.createNewFile()
  configFile.getAbsolutePath()
}
