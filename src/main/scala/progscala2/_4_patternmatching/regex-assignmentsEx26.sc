// src/main/scala/progscala2/patternmatching/regex-assignments.sc

// 정규 표현식에 대한 패턴 매칭을 사용해서 문자열을 분해할 수 있다.

val cols = """\*|[\w, ]+"""  // 컬럼
val table = """\w+"""        // 테이블 이름
val others = """.*"""        // 기타

// 인터폴레이션을 사용했기 때문에 \s 대신 \\s 넣었다.
val selectRE =
  s"""SELECT\\s*(DISTINCT)?\\s+($cols)\\s*FROM\\s+($table)\\s*($others)?;""".r

val selectRE(distinct1, cols1, table1, otherClauses) = 
  "SELECT DISTINCT * FROM atable;"
val selectRE(distinct2, cols2, table2, otherClauses) = 
  "SELECT col1, col2 FROM atable;"
val selectRE(distinct3, cols3, table3, otherClauses) = 
  "SELECT DISTINCT col1, col2 FROM atable;"
val selectRE(distinct4, cols4, table4, otherClauses) = 
  "SELECT DISTINCT col1, col2 FROM atable WHERE col1 = 'foo';"
