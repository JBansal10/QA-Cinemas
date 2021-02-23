import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.selenium.WebBrowser

import java.util.concurrent.TimeUnit

class EmailPageTest extends AnyFlatSpec with Matchers with WebBrowser {
  val host = "http://localhost:9000/"
  implicit val webDriver: WebDriver = new HtmlUnitDriver(true)
  webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

  org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)
    .asInstanceOf[ch.qos.logback.classic.Logger]
    .setLevel(ch.qos.logback.classic.Level.ERROR)

  "Typing on Contact Us form and submitting" should "Send email to specified email address" in {
    go to host + "contactus"
    textField(id("name")).value = "Front End"
    println(textField(id("name")).value)
    emailField(id("from")).value = "test@email.com"
    textArea(id("content")).value = "Words, words words words test words."
    click on id("fcf-button")
    pageTitle should be ("Email sent")
  }
}