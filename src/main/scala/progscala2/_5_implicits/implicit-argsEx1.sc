// src/main/scala/progscala2/implicits/implicit-args.sc

// 암시(implicit): 준비 코드를 줄이고, 기존 타입에 새로운 메서드를 추가한 것과 같은 효과. 도메인 특화 언어(DSL)의 생성을 지원할 수 있다. 메서드 인자 중
//                사용자가 명시적으로 제공할 필요가 없는 인자를 표시하기 위해 그 키워드를 사용. 암시적 메서드도 사용할 수 있다.
// 1. 준비를 위한 코드를 없애는 용도.
// 2. 매개변수화한 타입을 받는 메서드에 사용해서 버그를 줄이거나 허용되는 타입을 제한하기 위한 제약 사항으로 사용.

// 암시적 메서드: 복잡한 경우에 사용. 암시적 인자를 받아서 필요한 데이터를 처리.

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
