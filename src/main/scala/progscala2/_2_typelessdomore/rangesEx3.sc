// src/main/scala/progscala2/typelessdomore/ranges.sc

// Range 리터럴: 어떤 시작 값부터 마지막 값에 이르는 수열이 필요한 경우.
//             Range 객체가 지원하는 Int, Long, Float, Double, Char, BigInt, BigDecimal.


1 to 10                   // Int range inclusive, interval of 1, (1 to 10)

1 until 10                // Int range exclusive, interval of 1, (1 to 9)

1 to 10 by 3              // Int range inclusive, every third.

1L to 10L by 3            // Long

1.1f to 10.3f by 3.1f     // Float with an interval != 1

1.1f to 10.3f by 0.5f     // Float with an interval < 1

1.1 to 10.3 by 3.1        // Double

'a' to 'g' by 3            // Char

BigInt(1) to BigInt(10) by 3

BigDecimal(1.1) to BigDecimal(10.3) by 3.1 



