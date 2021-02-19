
import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.{BeforeAndAfter, flatspec}
import org.scalatest.matchers._
import org.scalatestplus.selenium.{HtmlUnit, WebBrowser}

import java.util.concurrent.TimeUnit

class ContactUsTest extends flatspec.AnyFlatSpec with BeforeAndAfter with should.Matchers with WebBrowser {

  val host = "http://localhost:9000/"
  val ContactUs = "http://localhost:9000/ContactUs"
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

  "Typing on Contact Us form and Submitting" should "Send email to specified email address" in{
    go to host
    go to ContactUs
    click on xpath("/html/body/div/div/div/div/form/div[1]/div/input")
    enter ("Jake")
    click on id("Email")
    enter ("Jakereid@@hotmail.co.uk")
    click on id ("Message")
    enter ("Hello")
    submit()
  }

}