// src/main/scala/progscala2/dynamic/clinq-example.sc
import progscala2.dynamic.CLINQ

def makeMap(name: String, capital: String, statehood: Int) =
  Map("name" -> name, "capital" -> capital, "statehood" -> statehood)

// 미국의 다섯 주에 대한 '레코드'
val states = CLINQ(
  List(
    makeMap("Alaska",     "Juneau",      1959),
    makeMap("California", "Sacramento",  1850),
    makeMap("Illinois",   "Springfield", 1818),
    makeMap("Virginia",   "Richmond",    1788),
    makeMap("Washington", "Olympia",     1889)))

// 필드 프로젝션 ("SELECT ...")
states.name
states.capital
states.statehood
states.name_and_capital
states.name_and_statehood
states.capital_and_statehood
states.name_capital_and_statehood
states.all

// "WHERE"절 추가
states.all.where("name").NE("Alaska")
states.all.where("statehood").EQ(1889)
states.name_and_statehood.where("statehood").NE(1850)
