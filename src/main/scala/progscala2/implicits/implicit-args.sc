// src/main/scala/progscala2/implicits/implicit-args.sc

// 실제 통화 계산에서는 Float 등의 부동 소수점수를 사용하지 말라.
def calcTax(amount: Float)(implicit rate: Float): Float = amount * rate

object SimpleStateSalesTax {
  implicit val rate: Float = 0.05F
}

case class ComplicatedSalesTaxData(
  baseRate: Float,
  isTaxHoliday: Boolean,
  storeId: Int)

object ComplicatedSalesTax {
  private def extraTaxRateForStore(id: Int): Float = {
    // id로부터 위치를 정하고 추가 세금 등을 계산한다.
    0.0F
  }

  implicit def rate(implicit cstd: ComplicatedSalesTaxData): Float = 
    if (cstd.isTaxHoliday) 0.0F
    else cstd.baseRate + extraTaxRateForStore(cstd.storeId)
}

{
  import SimpleStateSalesTax.rate

  val amount = 100F
  println(s"Tax on $amount = ${calcTax(amount)}")
}

{
  import ComplicatedSalesTax.rate
  implicit val myStore = ComplicatedSalesTaxData(0.06F, false, 1010)

  val amount = 100F
  println(s"Tax on $amount = ${calcTax(amount)}")
}
