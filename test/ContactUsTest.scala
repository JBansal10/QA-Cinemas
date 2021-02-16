
import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.{BeforeAndAfter, flatspec}
import org.scalatest.matchers._
import org.scalatestplus.selenium.{HtmlUnit, WebBrowser}

import java.util.concurrent.TimeUnit

class ContactUsTest extends flatspec.AnyFlatSpec with BeforeAndAfter with should.Matchers with WebBrowser {

  val host = "http://localhost:9000/"
  implicit val webDriver: WebDriver = new HtmlUnitDriver()
  webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

  "clicking on Contact Us in footer" should "take you to Contact Us page" in {
    go to host
    click on id("contactUsFooter")
    pageTitle should be ("Welcome to Play")
  }

  "clicking on Contact Us in navbar" should "take you to Contact Us page" in {
    go to host
    click on id("aboutusDropdown")
    click on id("contactUsHeader")
    pageTitle should be ("Welcome to Play")
  }

}