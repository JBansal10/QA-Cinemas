import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.matchers.should
import org.scalatest.{BeforeAndAfter, flatspec}
import org.scalatestplus.selenium.WebBrowser

import java.util.concurrent.TimeUnit

class PaymentPageTest extends flatspec.AnyFlatSpec with BeforeAndAfter with should.Matchers with WebBrowser{

  val host = "http://localhost:9000/"
  implicit val webDriver: WebDriver = new HtmlUnitDriver()
  webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

  org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)
    .asInstanceOf[ch.qos.logback.classic.Logger]
    .setLevel(ch.qos.logback.classic.Level.ERROR)

  "Loading the page" should "show payment page as the title" in {
    go to host + "payment"
    pageTitle should be ("payment page")
  }

  it should "redirect to booking successful page" in {
    go to host + "payment" // TODO form doesn't fill in right
    textField(xpath("//*[@id=\"cardHolderName\"]")).value = "John Smith"
    println(textField(xpath("//*[@id=\"cardHolderName\"]")).value)
    textField(xpath("//*[@id=\"cardNo\"]")).value = "1234 5678 1234"
    textField(xpath("//*[@id=\"expiryDate\"]")).value = "12/21"
    textField(xpath("//*[@id=\"securityCode\"]")).value = "789"
    click on id("submit")
    pageTitle should be ("Booking Complete")
  }



}