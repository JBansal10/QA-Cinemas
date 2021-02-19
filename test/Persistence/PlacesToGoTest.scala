package Persistence

import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.{BeforeAndAfter, flatspec}
import org.scalatest.matchers.should
import org.scalatestplus.selenium.WebBrowser

import java.util.concurrent.TimeUnit

class PlacesToGoTest extends flatspec.AnyFlatSpec with BeforeAndAfter with should.Matchers with WebBrowser {

  val host = "http://localhost:9000/"
  implicit val webDriver: WebDriver = new HtmlUnitDriver()
  webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

  "clicking on Opening Times in navbar" should "take you to Opening Times page" in {
    go to host
    click on id("Surrounding Venues")
    pageTitle should be ("Places To Go")
  }
}