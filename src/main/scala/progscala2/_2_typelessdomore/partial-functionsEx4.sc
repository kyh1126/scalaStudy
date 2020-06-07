// src/main/scala/progscala2/typelessdomore/partial-functions.sc

// 부분 함수(PartialFunction)
// 부분이란 말은 함수가 모든 가능한 입력에 대해 결과를 정의하지는 않는다는 뜻. 지정한 케이스 절에서 어느 하나와 일치하는 입력에 대해서만 결과 정의.
// 케이스 절만 들어갈 수 있고 반드시 전체를 중괄호로 묶어야 한다. (일반적 함수 리터럴은 괄호로도 본문 식 묶을 수 있음)
// 케이스 절의 하나와 일치하지 않는 값이 부분 함수의 인자로 들어오면 MatchError 실행 시점 예외 발생.
// 어떤 입력과 일치하는지 isDefinedAt 메서드로 예외 방지 가능.

val pf1: PartialFunction[Any,String] = { case s:String => "YES" }    // <1>
val pf2: PartialFunction[Any,String] = { case d:Double => "YES" }    // <2>

val pf = pf1 orElse pf2                                              // <3> 두 함수를 묶어 새 부분 함수 만듬.

def tryPF(x: Any, f: PartialFunction[Any,String]): String =          // <4> 성공 여부와 관계 없이 문자열 반환.
  try { f(x).toString } catch { case _: MatchError => "ERROR!" }

def d(x: Any, f: PartialFunction[Any,String]) =              // <5> isDefinedAt 호출해서 문자열 결과 반환하는 함수.
  f.isDefinedAt(x).toString

println("`     |   pf1 - String  |   pf2 - Double  |    pf - All")   // <6>
println("x     | def?  |  pf1(x) | def?  |  pf2(x) | def?  |  pf(x)")
println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
List("str", 3.14, 10) foreach { x =>
  printf("%-5s | %-5s | %-6s  | %-5s | %-6s  | %-5s | %-6s\n", x.toString, 
    d(x,pf1), tryPF(x,pf1), d(x,pf2), tryPF(x,pf2), d(x,pf), tryPF(x,pf))
}

d("hello", pf)
