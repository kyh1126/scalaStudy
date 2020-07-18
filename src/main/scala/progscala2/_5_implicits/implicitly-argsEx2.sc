// src/main/scala/progscala2/implicits/implicitly-args.sc

// implicitly: Predef 에 이 메서드 정의가 있다. 이 메서드와 타입 시그니처를 조합하면, 매개변수화한 타입인 암시적 인자를 단 하나만 사용하는 메서드의
//             시그니처를 정의할 때 아주 유용하다. 다만 타입 시그니처를 유의해야 한다.

// List.sortBy: 다양한 컬렉션에 존재하는 정렬 메서드 중 하나. '인자를 math.Ordering 을 만족하는 대상으로 변환하는 함수'를 인자로 받는다.

// math.Ordering: 자바의 Comparable 추상화와 비슷하다.
import math.Ordering

// MyList: sortBy 와 비슷한 메서드를 작성하는 두 가지 다른 방법을 보여준다.
case class MyList[A](list: List[A]) {
  // Ordering[B]: 범위 안에 원하는 B 타입의 인스턴스 사이의 '순서'를 정하는 방법을 아는 객체, B 타입이 '맥락'에 의해 바운드 된다. 인스턴스의 순서를
  //              정하는 능력이 타입을 바운드한다.
  def sortBy1[B](f: A => B)(implicit ord: Ordering[B]): List[A] =
    list.sortBy(f)(ord)

  // B : Ordering: 이 타입 매개변수를 맥락 바운드(context bound)라고 한다. Ordering[B] 인스턴스를 취하는 암시적인 2번째 인자 목록이 있음을 의미.
  def sortBy2[B : Ordering](f: A => B): List[A] =
    list.sortBy(f)(implicitly[Ordering[B]])
}

val list = MyList(List(1,3,5,2,4))

list sortBy1 (i => -i)
list sortBy2 (i => -i)
list sortBy2 (i => i)
