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

  it should "direct to payment form" in {
    go to host + "booking/1"
    textField(xpath("/html/body/div/div/form/div/dl[1]/dd/input")).value = "1/1/2021"
    textField(xpath("/html/body/div/div/form/div/dl[2]/dd/input")).value = "John Doe"
    textField(xpath("/html/body/div/div/form/div/dl[5]/dd/input")).value = "Burger"
    click on xpath("/html/body/div/div/form/div/button")
    assert(find(xpath("/html/body/div/div/h1")).get.text.contains("Payment Page"))

    textField(xpath("/html/body/div/div/form/div/dl[1]/dd/input")).value = "John Doe"
    textField(xpath("/html/body/div/div/form/div/dl[2]/dd/input")).value = "1234"
    textField(xpath("/html/body/div/div/form/div/dl[3]/dd/input")).value = "12/21"
    textField(xpath("/html/body/div/div/form/div/dl[4]/dd/input")).value = "789"
    click on xpath("/html/body/div/div/form/div/button")

    pageTitle should be ("Booking Complete")
  }




}