import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.{BeforeAndAfter, flatspec}
import org.scalatest.matchers._
import org.scalatestplus.selenium.{HtmlUnit, WebBrowser}

import java.util.concurrent.TimeUnit

class ContactUsTest extends flatspec.AnyFlatSpec with BeforeAndAfter with should.Matchers with WebBrowser {

  val host = "http://localhost:9000/"
  implicit val webDriver: WebDriver = new HtmlUnitDriver(true)
  webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

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

  "Typing on Contact Us form and Submitting" should "Send email to specified email address" in{
    go to host + "contactus"
    textField(id("name")).value = "Test Person"
    emailField(id("from")).value = "test@email.com"
    textArea(id("content")).value = "Words, words words words test words."
    submit()
    pageTitle should be ("Email sent")
  }

}