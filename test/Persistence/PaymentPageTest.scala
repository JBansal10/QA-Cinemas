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

  "Loading the page" should "show payment page as the title" in {
    go to host
    assert(find(xpath("/html/body/div/h1")).get.text == "Payment page")
  }

  it should "redirect to booking successful page" in {
    go to host
    textField(xpath("/html/body/div/form/div/dl[1]/dd[1]/input")).value = "John Smith"
    textField(xpath("/html/body/div/form/div/dl[2]/dd[1]/input")).value = "1234 5678 1234 5678"
    textField(xpath("/html/body/div/form/div/dl[3]/dd[1]/input")).value = "12/21"
    textField(xpath("/html/body/div/form/div/dl[4]/dd[1]/input")).value = "789"
    textField(xpath("/html/body/div/form/div/dl[5]/dd[1]/input")).value = "1"
    click on xpath("/html/body/div/form/div/button")
    assert(find(xpath("/html/body/div/div/li[3]")).get.text.contains("This is test content"))
  }



}