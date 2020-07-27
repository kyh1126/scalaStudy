# Chapter 7. for 내장


## 01. for 내장의 기본 요소


```scala
/**
 * 지정한 입력 파일에서 빈 줄을 제거.
 */
def apply(path: String, compressWhiteSpace: Boolean = false): Seq[String] =
  for {
    line <- scala.io.Source.fromFile(path).getLines.toSeq

    if line.matches("""^\s*$""") == false
    line2 = if (compressWhiteSpace) line replaceAll ("\\s+", " ")
            else line
  } yield line2

/**
 * 지정한 입력 파일에서 빈 줄 제거하고 남은 줄들을 표준 출력에 하나씩 보낸다.
 * @param args 파일 경로 목록. 파일명 앞에 '-'를 붙이면 파일에서 연속된 여러 공백을 하나로 압축.
 */
def main(args: Array[String]) = for {
	path2 <- args
	(compress, path) = if (path2 startsWith "-") (true, path2.substring(1))
                   else (false, path2)
	line <- apply(path, compress)  // 빈 줄 제거하는 function
} println(line)
```

- 하나 이상의 제너레이터 식
- 선택적으로 가드 식(for 걸러내기), 값 정의.
- 출력은 새 컬렉션을 만들거나, 매 단계마다 부수 효과 블록(출력 표시, ..)을 만들기 위해 yield 될 수 있다.

## 02. for 내장: 내부 동작


- for 내장 문법은 실제로는 다음 컬렉션 메서드 4가지를 호출하는 것에 대한 구문상 편의일 뿐이다.
    - foreach
        - for 내장 뒤에 yield가 없는 단일 제너레이터 식은 컬렉션에 대해 foreach 호출한것과 같다.

        ```scala
        val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

        for {s <- states} println(s)

        // 위와 같다.
        states foreach println
        ```

    - map
        - 제너레이터가 하나만 있고 yield 가 있는 식은 map 호출한것과 같다.

        ```scala
        val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

        for {s <- states} yield s.toUpperCase

        // 위와 같다.
        states map (_.toUpperCase)
        ```

    - flatMap
        - 제너레이터가 여럿 있고, 맨 마지막을 제외한 모든 제너레이터는 flatMap 호출로 바뀐다. 맨 마지막은 map 호출이다.

        ```scala
        val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

        for {
          s <- states
          c <- s
        } yield s"$c-${c.toUpper}"

        // 위와 같다.
        states flatMap (_.toSeq map (c => s"$c-${c.toUpper}"))
        ```

    - withFilter
        - 스칼라는 withFilter가 없는 경우 filter 를 사용한다.(경고 출력)
        - filter 메서드처럼 원소를 걸러내는 용도로만 사용.
        - filter 와 달리 컬렉션을 만들어내지 않는다.(더 나은 효율성)
        - 그 다음에 오는 map, filterMap, foreach, 다른 withFilter 호출에 넘겨질 원소의 정의역(domain)을 제한한다.
        - 가드를 넣으면 withFilter 호출이 마지막 map 호출 앞에 들어간다.

        ```scala
        val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

        for {
          s <- states
          c <- s
          if c.isLower
        } yield s"$c-${c.toUpper}"

        // 위와 같다.
        states flatMap (_.toSeq withFilter (_.isLower) map (c => s"$c-${c.toUpper}"))
        ```

## 03. for 내장: 변환 규칙


- 제너레이터 식: pat ← expr

    ```scala
    pat <- expr.withFilter { case pat => true; case _ => false }
    ```

- 제너레이터가 하나만 있고 맨 끝에 yield 가 있는 for 내장: for ( pat ← expr1 ) yield expr2

    ```scala
    expr1 map { case pat => expr2 }
    ```

- yield 가 없고 부수 효과만 수행하는 for 루프: for ( pat ← expr1 ) expr2

    ```scala
    expr1 foreach { case pat => expr2 }
    ```

- 제너레이터가 하나 이상 있는 for 내장: for ( pat1 ← expr1; pat2 ← expr2; ... ) yield exprN

    ```scala
    expr1 flatMap { case pat1 => for ( pat2 <- expr2 ... ) yield exprN }
    ```

    - 내포된 제너레이터는 내포된 for 내장으로 바뀐다.(위의 ... 식은 다른 제너레이터, 값 정의, 가드 중 하나)
- 제너레이터가 하나 이상 있는 for 루프: for ( pat1 ← expr1; pat2 ← expr2; ... ) exprN

    ```scala
    expr1 foreach { case pat1 => for (pat2 <- expr2 ... ) yield exprN }
    ```

- 제너레이터 다음 가드가 오는 경우: pat1 ← expr1 if guard

    ```scala
    pat1 <- expr1 withFilter ((arg1, arg2, ...) => guard)
    ```

- 제너레이터 뒤에 변수 정의가 오는 경우: pat1 ← expr1; pat2 = expr2
    - x1 @ pat1 : 변수 x1 에 pat1 이 일치하는 전체 식에 대한 값을 대입하라는 의미.

    ```scala
    (pat1, pat2) <- for {
    	x1 @ pat1 <- expr1
    } yield {
    	val x2 @ pat2 = expr2
    	(x1, x2)
    }
    ```

    - 다른 예제를 살펴보자

    ```scala
    val map = Map("one" -> 1, "two" -> 2)

    val list1 = for {
      (key, value) <- map   // 여기부터 어떻게 바뀌나 보자!
      i10 = value + 10
    } yield (i10)

    // 변환 ver:
    val list2 = for {
      (i, i10) <- for {
        x1 @ (key, value) <- map
      } yield {
        val x2 @ i10 = value + 10
        (x1, x2)
      } 
    } yield (i10)
    ```

## 04. Option 과 다른 컨테이너 타입


- Option
- 이진 컨테이너. 어떤 원소를 포함하고 있거나, 그렇지 않거나.
- for 내장에 필요한 4가지 메서드(foreach, map, flatMap, withFilter) 모두 제공.

    ```scala
    val results: Seq[Option[Int]] = Vector(Some(10), None, Some(20))

    val results2a = for {
      Some(i) <- results
    } yield (2 * i)
    ```

    - withFilter 를 써보자.

        ```scala
        val results2b = for {
          Some(i) <- results withFilter { 
            case Some(i) => true
            case None => false 
          }
        } yield (2 * i)
        ```

    - map 도 써보자.

        ```scala
        val results2c = results withFilter { 
          case Some(i) => true
          case None => false 
        } map {
          case Some(i) => (2 * i)  // withFilter 에서 None 제거하여 예외 발생 x
        }
        ```

- None 은 다음 단계를 실행하지 않는다.(no-op) 대신, 어떤 단계가 중단된 이유에 대한 정보를 얻을 수 없다.
- 예제

    ```scala
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
      i2 <- positive(-1 * i1)  // <1> 실패!
      i3 <- positive(25 * i2)
      i4 <- positive(-2 * i3) 
    } yield (i1 + i2 + i3 + i4)
    // 반환: Option[Int] = None
    ```

- Either
- Option 의 논리적 확장.
- Option 은 원소가 있거나 없는 경우, Either 는 한 원소 또는 다른 한 원소를 표현한다.(두 가지 중 한 가지만 담을 수 있는 컨테이너**)**
- Either는 매개변수를 둘 받는 매개변수화한 타입인 Either[+A, +B]다.
    - +A 는 Either가 타입 매개변수 A에 대해 공변적이라는 뜻.
    - Either[Any, Any] 타입의 값이 필요한 경우 Either[String, Int] 타입의 인스턴스를 사용할 수 있다. String, Int가 Any의 서브타입이기 때문에 Either[String, Int]도 Either[Any, Any]의 서브타입이다.
- 봉인된 추상 클래스, Left[A], Right[B] 두 서브클래스가 있다. 이 두 서브클래스는 2가지 가능한 원소를 구별해준다.
    - 관습에 따르면 Left 값에 오류를 표현하는 값을 저장한다.
    - 어떤 객체나 다른 객체 중 하나를 담고 싶을 때 사용 가능하며, 두 객체의 타입은 다를 수 있다.
- 예외 던지기와 Either 값 반환하기
    - Either 를 사용하면 다양한 오류 상황에서도 호출 스택의 제어를 보장할 수 있다.
    - 또한, API를 사용하는 사람들에게 그런 동작을 더 명확하게 알려줄 수 있다.

    ```scala
    def addInts2(s1: String, s2: String): Either[NumberFormatException,Int] = 
      try { 
        Right(s1.toInt + s2.toInt)
      } catch { 
        case nfe: NumberFormatException => Left(nfe)
      }

    println(addInts2("1", "2"))  // Right(3)
    println(addInts2("1", "x"))  // Left(java.lang.NumberFormatException: For input string: "x")
    ```

    - 위의 타입 시그니처는 실패 '모드'가 존재할 수 있음을 보여준다.
    - 예외를 던져서 addInts2 로부터 호출 스택의 제어를 가져오는 대신, 예외를 호출 스택을 통해 넘길 수 있는 값으로 만들어서 실체화 했다.
- 예제

    ```scala
    def positive(i: Int): Either[String,Int] = 
      if (i > 0) Right(i) else Left(s"nonpositive number $i")

    for {
      i1 <- positive(5).right
      i2 <- positive(10 * i1).right
      i3 <- positive(25 * i2).right
      i4 <- positive(2  * i3).right
    } yield (i1 + i2 + i3 + i4)
    // 반환: scala.util.Either[String,Int] = Right(3805)

    for {
      i1 <- positive(5).right
      i2 <- positive(-1 * i1).right   // EPIC FAIL!
      i3 <- positive(25 * i2).right
      i4 <- positive(-2 * i3).right   // EPIC FAIL!
    } yield (i1 + i2 + i3 + i4)
    // 반환: scala.util.Either[String,Int] = Left(nonpositive number -5)
    ```

- Try
- 할 수 있는 일이 없을 때
- 봉인된 추상 클래스, Success, Failure 두 서브클래스가 있다.
    - Success는 Right와 비슷하다.(일반적인 반환값)
    - Failure는 Left와 비슷하지만, 항상 Throwable을 포함한다.
- Try[+T]는 타입 매개변수가 하나뿐이다.
    - Left 타입에 해당하는 것이 Throwable 이기 때문.
- Try가 실제로는 Success 경우에만 T값에 대해 적용 가능한 map 같은 콤비네이터 메서드를 정의한다.
- 예제

    ```scala
    def positive(i: Int): Try[Int] = Try {
      assert (i > 0, s"nonpositive number $i")
      i
    }

    for {
      i1 <- positive(5)
      i2 <- positive(10 * i1)
      i3 <- positive(25 * i2)
      i4 <- positive(2  * i3)
    } yield (i1 + i2 + i3 + i4)
    // 반환: scala.util.Try[Int] = Success(3805)

    for {
      i1 <- positive(5)
      i2 <- positive(-1 * i1)              // 엄청난 실패! 
      i3 <- positive(25 * i2)
      i4 <- positive(-2 * i3)
    } yield (i1 + i2 + i3 + i4)
    // 반환: scala.util.Try[Int] = Failure(java.lang.AssertionError: assertion failed: nonpositive number -5)
    ```

- 스칼라제드 의 Validation
- 사용자 입력을 검증하고 발생하는 모든 오류를 사용자에게 반환하고 싶을 때 사용하는 외부 라이브러리.
- 오류가 있다면 모든 오류를 반환하고, 오류가 없으면 모은 데이터를 적절한 데이터 구조로 변환한다.
- 예제

    ```scala
    import scalaz._, std.AllInstances._

    def positive(i: Int): Validation[List[String], Int] = {
      if (i > 0) Success(i)    // <1> scalaz.Validation 의 서브클래스 Success, Failure
      else Failure(List(s"Nonpositive integer $i"))
    }

    positive(5) +++ positive(10) +++ positive(25)    // <3> positive에 대한 모든 호출을 평가한다. 그 후 결과를 더하거나 오류를 모은다.
    // 반환: scalaz.Validation[String,Int] = Success(40)

    positive(5) +++ positive(-10) +++ positive(25) +++ positive(-30) // <4> 두 오류를 모두 받는다.
    // 반환: scalaz.Validation[String,Int] = Failure(Nonpositive integer -10, Nonpositive integer -30)
    ```

https://www.notion.so/Chapter-7-for-e423c37175644b0889b9fc640cfdd468