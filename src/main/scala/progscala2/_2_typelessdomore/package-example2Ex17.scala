// src/main/scala/progscala2/typelessdomore/package-example2.scala

// 스칼라는 패키지 영역을 선언하기 위해 블록 구조의 구문을 사용하도록 지원한다.

package com {
  package example {
    package pkg1 {
      class Class11 {
        def m = "m11"
      }
      class Class12 {
        def m = "m12"
      }
    }

    package pkg2 {
      class Class21 {
        def m = "m21"
        def makeClass11 = {
          new pkg1.Class11
        }
        def makeClass12 = {
          new pkg1.Class12
        }
      }
    }

    package pkg3.pkg31.pkg311 {
      class Class311 {
        def m = "m21"
      }
    }
  }
}

