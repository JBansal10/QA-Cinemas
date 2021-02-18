package Persistence

import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.{BeforeAndAfter, flatspec}
import org.scalatest.matchers.should
import org.scalatestplus.selenium.WebBrowser

import java.util.concurrent.TimeUnit

class PaymentPageTest extends flatspec.AnyFlatSpec with BeforeAndAfter with should.Matchers with WebBrowser{

  val host = "http://localhost:9000/payment"
  implicit val webDriver: WebDriver = new HtmlUnitDriver()
  webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

  "clicking on About Us in footer" should "take you to About Us page" in {
    go to host
    click on id("aboutUsFooter")
    pageTitle should be ("About Us")
  }



}
