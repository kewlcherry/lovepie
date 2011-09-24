package lovep.ie

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}

class Landing extends HttpServlet {
  override def doGet(req:HttpServletRequest, resp: HttpServletResponse) {
    resp.setContentType("text/plain");
    resp.getWriter().println("Hello, world");
  }
}

class CharityServlet extends HttpServlet {
  override def doGet(req:HttpServletRequest, resp: HttpServletResponse) {
    resp.setContentType("text/plain");
    resp.getWriter().println("Hello, world");
  }
}
