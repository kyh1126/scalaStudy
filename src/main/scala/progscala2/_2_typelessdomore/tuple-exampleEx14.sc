// src/main/scala/progscala2/typelessdomore/tuple-example.sc

// 쌍(pair): 원소가 둘인 튜플. 여러 정의 방법이 있다.
//          (1, "one")
//          1 -> "one"      : 화살표 연산자는 원소가 둘인 튜플에서만 쓸 수 있다.
//          Tuple2(1, "one")

// 진짜 유용하다. wine inboxdeetalservicev2

val t = ("Hello", 1, 2.3)                                            // <1>
println( "Print the whole tuple: " + t )   
println( "Print the first item:  " + t._1 )                          // <2>
println( "Print the second item: " + t._2 )
println( "Print the third item:  " + t._3 )

val (t1, t2, t3) = ("World", '!', 0x22)                              // <3>
println( t1 + ", " + t2 + ", " + t3 )   

val (t4, t5, t6) = Tuple3("World", '!', 0x22)                        // <4> 팩토리 메서드
println( t4 + ", " + t5 + ", " + t6 )   
