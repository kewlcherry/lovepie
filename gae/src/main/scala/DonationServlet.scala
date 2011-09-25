package lovep.ie.servlet

import org.scalatra.ScalatraServlet

class Donate extends ScalatraServlet {
  get("/") {
    <h1>Hello, world!</h1>
  }

  post("/") {
//    implicit def
    params("test")
  }
}