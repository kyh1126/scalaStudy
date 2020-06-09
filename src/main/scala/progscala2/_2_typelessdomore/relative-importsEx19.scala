// src/main/scala/progscala2/typelessdomore/relative-imports.scala

// 자바 임포트와 스칼라 임포트의 차이점 중 하나: 스칼라 임포트가 상대적이다.

import scala.collection.mutable._
import collection.immutable._              // 'scala'는 항상 임포트된 상태다.
import _root_.scala.collection.parallel._  // 실제 최상위 패키지로부터의 전체 경로다.

