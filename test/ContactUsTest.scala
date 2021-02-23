
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

  org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)
    .asInstanceOf[ch.qos.logback.classic.Logger]
    .setLevel(ch.qos.logback.classic.Level.ERROR)

  "clicking on Contact Us in footer" should "take you to Contact Us page" in {
    go to host
    click on xpath("/html/body/footer/div[1]/div/div[3]/ul/li[3]/a")
    pageTitle should be ("Contact Us")
  }

  "clicking on Contact Us in navbar" should "take you to Contact Us page" in {
    go to host
    click on id("aboutusDropdown")
    click on id("contactUsHeader")
    pageTitle should be ("Contact Us")
  }


}