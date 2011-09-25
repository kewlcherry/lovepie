package lovep.ie

import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}
import org.scalatra.ScalatraServlet

class Landing extends HttpServlet {

  def json = """
  [
    {
        "nonprofit_name": "Barnardo's",
        "statement": "Barnardo's vision is that the lives of all children and young people should be free from poverty, abuse and discrimination. Our purpose is to help the most vulnerable children and young people transform their lives and fulfil their potential. We have over 380 shops across the UK selling donated goods, new goods and even furniture. For details of your local shop visit our website www.barnardos.org.uk           Barnardo's Registered Charity Nos 216250 and SC037605",
        "logo_path": "http://donationsstatic.ebay.com/extend/logos/MF12660.jpg",
        "receiver_email": "INVALID-TEST-ACCOUNT@barnardos.org.uk",
        "web_url": "www.barnardos.org.uk",
        "token_for_invoice_id": "M123456|12660"
    },
    {
        "nonprofit_name": "BBC Children in Need",
        "statement": "BBC Children in Need's mission is to positively change the lives of disadvantaged children and young people in the UK.  We provide this support in the form of grants to organisations working with children who have mental, physical or sensory disabilities; behavioural or psychological disorders; are living in poverty or situations of deprivation; or suffering through distress, abuse or neglect.",
        "logo_path": "http://donationsstatic.ebay.com/extend/logos/MF1.gif",
        "receiver_email": "INVALID-TEST-ACCOUNT@bbc.co.uk",
        "web_url": "www.bbc.co.uk/pudsey",
        "token_for_invoice_id": "M123456|1"
    },
    {
        "nonprofit_name": "Cancer Research UK",
        "statement": "Cancer Research UK is the world's leading charity dedicated to research on the causes, treatment and prevention of cancer. Thanks to research, more people are surviving cancer than ever before.",
        "logo_path": "http://donationsstatic.ebay.com/extend/logos/MF10650.jpg",
        "receiver_email": "INVALID-TEST-ACCOUNT@cancer.org.uk",
        "web_url": "www.cancerresearchuk.org",
        "token_for_invoice_id": "M123456|10650"
    },
    {
        "nonprofit_name": "Comic Relief - Red Nose Day",
        "statement": "Comic Relief spends all the money raised through Red Nose Day to help  vulnerable, poor and disadvantaged people in the UK and Africa turn their lives around. On their behalf, thank you.   Comic Relief, registered charity 326568 (England/Wales); SC039730 (Scotland)",
        "logo_path": "http://donationsstatic.ebay.com/extend/logos/MF18549.jpg",
        "receiver_email": "INVALID-TEST-ACCOUNT@comicrelief.com",
        "web_url": "www.rednoseday.com",
        "token_for_invoice_id": "M123456|18549"
    },
    {
        "nonprofit_name": "Great Ormond Street Hospital Children's Charity",
        "statement": "We need to raise &#xA3;50 million every year to help rebuild and refurbish Great Ormond Street Hospital, buy vital equipment and fund pioneering research. Amazing things happen at Great Ormond Street Hospital every day. With your help we can keep the magic alive for our very ill children and their families. For more information please visit www.gosh.org",
        "logo_path": "http://donationsstatic.ebay.com/extend/logos/MF10726.gif",
        "receiver_email": "INVALID-TEST-ACCOUNT@gosh.org",
        "web_url": "www.gosh.org",
        "token_for_invoice_id": "M123456|10726"
    },
    {
        "nonprofit_name": "Cancer Research UK",
        "statement": "Cancer Research UK is the world's leading charity dedicated to research on the causes, treatment and prevention of cancer. Thanks to research, more people are surviving cancer than ever before.",
        "logo_path": "http://donationsstatic.ebay.com/extend/logos/MF10650.jpg",
        "receiver_email": "INVALID-TEST-ACCOUNT@cancer.org.uk",
        "web_url": "www.cancerresearchuk.org",
        "token_for_invoice_id": "M123456|10650"
    },
    {
        "nonprofit_name": "Marie Curie Cancer Care",
        "statement": "Marie Curie Cancer Care provides high quality nursing, totally free to give people with terminal cancer and other illnesses the choice of dying at home, supported by their families.",
        "logo_path": "http://donationsstatic.ebay.com/extend/logos/MF11871.gif",
        "receiver_email": "INVALID-TEST-ACCOUNT@mariecurie.org.uk",
        "web_url": "www.mariecurie.org.uk",
        "token_for_invoice_id": "M123456|11871"
    },
    {
        "nonprofit_name": "NSPCC: cruelty to children must stop",
        "statement": "Our aim is to create a society where children can grow up in a loving environment, free from sexual, physical, emotional abuse or neglect and to help children who have been abused.  NSPCC, registered charity numbers 216401 and SC037717",
        "logo_path": "http://donationsstatic.ebay.com/extend/logos/MF13424.gif",
        "receiver_email": "INVALID-TEST-ACCOUNT@mariecurie.org.uk",
        "web_url": "www.nspcc.org.uk",
        "token_for_invoice_id": "M123456|13424"
    },
    {
        "nonprofit_name": "Oxfam GB",
        "statement": "Oxfam GB is a development, relief, and campaigning organisation working with others to find lasting solutions to poverty and suffering around the world.  Oxfam is currently responding to the food shotages and drought in East Africa. Please visit www.Oxfam.org.uk for more information.",
        "logo_path": "http://donationsstatic.ebay.com/extend/logos/MF10013.jpg",
        "receiver_email": "INVALID-TEST-ACCOUNT@oxfam.org.uk",
        "web_url": "www.oxfam.org.uk",
        "token_for_invoice_id": "M123456|10013"
    },
    {
        "nonprofit_name": "RSPCA",
        "statement": "The charitable objects of the RSPCA are to promote kindness and to prevent or suppress cruelty to animals and to do all such lawful acts as the Society may consider to be conducive or incidental to the attainment of those objects.",
        "logo_path": "http://donationsstatic.ebay.com/extend/logos/MF10484.jpg",
        "receiver_email": "INVALID-TEST-ACCOUNT@rspca.org.uk",
        "web_url": "www.rspca.org.uk",
        "token_for_invoice_id": "M123456|10484"
    }
]
  """

  override def doGet(req:HttpServletRequest, resp: HttpServletResponse) {
    resp.setContentType("application/json");
    resp.getWriter().println(json);
  }
}

class CharityServlet extends ScalatraServlet {
  get("/") {
    <h1>Hello, world!</h1>
  }
}
