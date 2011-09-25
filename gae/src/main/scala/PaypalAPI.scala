package lovep.ie.paypal.api

import dispatch.{JdkLogging, url, Http}

sealed class Email(email: String)

class Paypal(var userId: String = "seller_1316894385_biz_api1.novoda.com", var password: String = "1316894455", var appId: String = "APP-80W284485P519543T", var sig: String = "AFcWxV21C7fd0v3bYYYRCpSSRl31ACgWD7e-CmJfzJZQHk1Xw6hTgBsH")

case class Receiver(var amount: Int, var email: String, var invoiceId: String)

trait PaypalApi {
}

class ParallelPayment(val amount: Int, val receivers: List[Receiver]) {

  implicit def paypalToMap(p: Paypal): Map[String, String] =
    Map("X-PAYPAL-SECURITY-USERID" -> p.userId, "X-PAYPAL-SECURITY-PASSWORD" -> p.password, "X-PAYPAL-SECURITY-SIGNATURE" -> p.sig, "X-PAYPAL-REQUEST-DATA-FORMAT" -> "NV", "X-PAYPAL-RESPONSE-DATA-FORMAT" -> "json", "X-PAYPAL-APPLICATION-ID" -> p.appId)

  val h = new dispatch.gae.Http

  def execute = {
    var count = 0
    var m: Map[String, String] = Map()
    for (i <- receivers.indices) {
      m += ("receiverList.receiver(%d).amount".format(i) -> receivers(i).amount.toString)
      m += ("receiverList.receiver(%d).email".format(i) -> receivers(i).email)
      m += ("receiverList.receiver(%d).primary".format(i) -> "false")
      m += ("receiverList.receiver(%d).invoiceId".format(i) -> receivers(i).invoiceId)
    }
    val e = Map[String, String]("actionType" -> "PAY",
      "cancelUrl" -> "http://love-pie.appspot.com/cancel",
      "currencyCode" -> "GBP",
      "feesPayer" -> "EACHRECEIVER",
      "memo" -> "Parallel-payment-example",
      "requestEnvelope.errorLanguage" -> "en_US",
      "returnUrl" -> "http://love-pie.appspot.com/success",
      "reverseAllParallelPaymentsOnError" -> "false")
    val p = m ++ e

    val ppurl = url("https://svcs.sandbox.paypal.com/AdaptivePayments/Pay") <:< new Paypal <<? p
    import dispatch.liftjson.Js._
    import net.liftweb.json.JsonAST._
    val s = h(ppurl ># {
      json =>
        for {
          JField("payKey", JString(payKey)) <- json
        } yield (payKey)
    })
    h.shutdown()
    Some(s.head)
  }

}