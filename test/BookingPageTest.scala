import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.{BeforeAndAfter, flatspec}

import org.scalatest.matchers.should.Matchers
import org.scalatestplus.selenium.WebBrowser
import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import java.util.concurrent.TimeUnit
import scala.io.Source

class BookingPageTest extends flatspec.AnyFlatSpec with WebBrowser with BeforeAndAfter with Matchers{

  val host = "http://localhost:9000/"
  implicit val webDriver: WebDriver = new HtmlUnitDriver()
  webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)

  behavior of "Booking page"

  it should "show booking form" in {
    go to host + "bookings"
    click on xpath("/html/body/div/div/table/tbody/tr[2]/td[4]/a")
    pageTitle should be("booking page")
  }

  it should "show Titanic title" in {
    go to host + "booking/1"
    assert(find(xpath("/html/body/div/div/h2")).get.text.contains("Titanic"))
  }

  it should "direct to payment form" in {
    go to host + "booking/1"
    textField(xpath("/html/body/div/div/form/div/dl[1]/dd/input")).value = "1/1/2021"
    textField(xpath("/html/body/div/div/form/div/dl[1]/dd/input")).value = "John Doe"
    textField(xpath("/html/body/div/div/form/div/dl[5]/dd/input")).value = "Burger"
    click on xpath("/html/body/div/div/form/div/button")
    assert(find(xpath("/html/body/div/div/h1")).get.text.contains("Payment Page"))
  }

}
