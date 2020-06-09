// src/main/scala/progscala2/typelessdomore/package-example3.scala

// 연속 패키지 문 관용구: 별도로 패키지 문을 사용하는 경우다.

// 패키지 선언 구문의 한 가지 제약 사항: 클래스나 객체 안에서는 패키지를 정의할 수 없다.

// 'example'에 있는 모든 패키지 수준의 선언을 영역으로 가져온다.
package com.example
// `mypkg`에 있는 모든 패키지 수준의 선언을 영역으로 가져온다.
package mypkg

class MyPkgClass {
  // ...
}
