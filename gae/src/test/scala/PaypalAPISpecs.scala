package lovep.ie.paypal

import org.specs.Specification

object PaypalParrallelPaymentSpec extends Specification {

  "A call to the Paypal API" should {
    "return a JSON if called correctly" in {
      import dispatch._
      val h = new Http
      val s = h(url("http://www.scala-lang.org/") as_str)
      s must be_== ("test")
    }
  }
}