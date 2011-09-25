package lovep.ie.servlet

import org.scalatra.test.specs.ScalatraSpecification

object LovePieScalatraTest extends ScalatraSpecification {
  addServlet(classOf[Donate], "/*")

  "My Donation servlet when POSTing" should {
    "return a URL to paypal as redirect" in {
      post("/", ("test", "post")) {
        status mustEqual (200)
        body mustEqual ("post")
      }
    }
  }
}