package lovep.ie.paypal.api

import dispatch.{:/, url, Http}

sealed class Email(email: String)

class Paypal(var userId: String = "seller_1316894385_biz_api1.novoda.com", var password: String = "1316894455", var appId: String = "APP-80W284485P519543T", var sig: String = "AFcWxV21C7fd0v3bYYYRCpSSRl31ACgWD7e-CmJfzJZQHk1Xw6hTgBsH")

class Receiver(var amount: Int, var email: String, var invoiceId: String)

trait PaypalApi {
}

class ParallelPayment(u: Email, f: Receiver*) {

  implicit def paypalToMap(p: Paypal): Map[String, String] =
    Map("X-PAYPAL-SECURITY-USERID" -> p.userId, "X-PAYPAL-SECURITY-PASSWORD" -> p.password, "X-PAYPAL-SECURITY-SIGNATURE" -> p.sig, "X-PAYPAL-REQUEST-DATA-FORMAT" -> "NV", "X-PAYPAL-RESPONSE-DATA-FORMAT" -> "json", "X-PAYPAL-APPLICATION-ID" -> p.appId)

  implicit def receiversToMap(p: Receiver*): Map[String, String] = Map("actionType" -> "PAY",
    "cancelUrl" -> "http://love-pie.appspot.com/cancel",
    "currencyCode" -> "GBP",
    "feesPayer" -> "EACHRECEIVER",
    "memo" -> "Parallel-payment-example",
    "requestEnvelope.errorLanguage" -> "en_US",
    "returnUrl" -> "http://google.com",
    "reverseAllParallelPaymentsOnError" -> "false",
    "senderEmail" -> "test1_1316892066_per@novoda.com")

  val h = new Http

  def exectue = {
    val ppurl = url("https://svcs.sandbox.paypal.com/AdaptivePayments/Pay") <:< new Paypal <<? Map("actionType" -> "PAY",
      "cancelUrl" -> "http://love-pie.appspot.com/cancel",
      "currencyCode" -> "GBP",
      "feesPayer" -> "EACHRECEIVER",
      "memo" -> "Parallel-payment-example",
      "receiverList.receiver(0).amount" -> "100",
      "receiverList.receiver(0).email" -> "seller_1316894385_biz@novoda.com",
      "receiverList.receiver(0).primary" -> "false",
      "receiverList.receiver(1).amount" -> "50",
      "receiverList.receiver(1).email" -> "test2_1316896631_biz@novoda.com",
      "receiverList.receiver(1).primary" -> "false",
      "requestEnvelope.errorLanguage" -> "en_US",
      "returnUrl" -> "http://google.com",
      "reverseAllParallelPaymentsOnError" -> "false")

    val s = h(ppurl as_str)

  }
}