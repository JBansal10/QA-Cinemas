import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.matchers._
import org.scalatest.{BeforeAndAfter, flatspec}
import org.scalatestplus.selenium.WebBrowser

import java.util.concurrent.TimeUnit

class OpeningTimesPageTest extends flatspec.AnyFlatSpec with BeforeAndAfter with should.Matchers with WebBrowser {

  val host = "http://localhost:9000/"
  implicit val webDriver: WebDriver = new HtmlUnitDriver()
  webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

  org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)
    .asInstanceOf[ch.qos.logback.classic.Logger]
    .setLevel(ch.qos.logback.classic.Level.ERROR)


  "clicking on Opening Times in navbar" should "take you to Opening Times page" in {
    go to host
    click on xpath("/html/body/nav/div/div/ul/li[2]/a")
    click on xpath("/html/body/nav/div/div/ul/li[2]/ul/li[3]/a")
    pageTitle should be ("Opening Times")
  }
}