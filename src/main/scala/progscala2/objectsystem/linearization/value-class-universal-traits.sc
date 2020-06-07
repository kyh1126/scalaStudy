// src/main/scala/progscala2/objectsystem/linearization/value-class-universal-traits.sc

trait M extends Any {
  def m = print("M ")
}

trait Digitizer extends Any with M {
  override def m = { print("Digitizer "); super.m }

  def digits(s: String): String = s.replaceAll("""\D""", "")
}

trait Formatter extends Any with M {   
  override def m = { print("Formatter "); super.m }

  def format(areaCode: String, exchange: String, subnumber: String): String =
    s"($areaCode) $exchange-$subnumber"
}

// 식이 2줄에 걸쳐 있음을 REPL에 알려주기 위해 'extends AnyVal'을 2줄로 나눠서 썼다.
class USPhoneNumber(val s: String) extends 
    AnyVal with Digitizer with Formatter{
  override def m = { print("USPhoneNumber "); super.m }
  
  override def toString = {
    val digs = digits(s)
    val areaCode = digs.substring(0,3)
    val exchange = digs.substring(3,6)
    val subnumber  = digs.substring(6,10)
    format(areaCode, exchange, subnumber)
  }
}

val number = new USPhoneNumber("987-654-3210")
println("Call m:")
number.m
