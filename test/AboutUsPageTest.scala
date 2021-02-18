import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.{BeforeAndAfter, flatspec}
import org.scalatest.matchers._
import org.scalatestplus.selenium.{HtmlUnit, WebBrowser}

import java.util.concurrent.TimeUnit

class AboutUsPageTest extends flatspec.AnyFlatSpec with BeforeAndAfter with should.Matchers with WebBrowser {

  val host = "http://localhost:9000/"
  implicit val webDriver: WebDriver = new HtmlUnitDriver()
  webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

  "clicking on About Us in footer" should "take you to About Us page" in {
    go to host
    click on id("aboutUsFooter")
    pageTitle should be ("About Us")
  }

  "clicking on About Us in navbar" should "take you to About Us page" in {
    go to host
    click on id("aboutusDropdown")
    click on xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/ul/li[1]/a")
    pageTitle should be ("About Us")
  }

}