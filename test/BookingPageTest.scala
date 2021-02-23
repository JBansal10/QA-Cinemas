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

  lazy val db = Database.forConfig("mysqlDB")

  val host = "http://localhost:9000/"
  implicit val webDriver: WebDriver = new HtmlUnitDriver()
  webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

  org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)
    .asInstanceOf[ch.qos.logback.classic.Logger]
    .setLevel(ch.qos.logback.classic.Level.ERROR)

  behavior of "Disccusion board page"

  it should "create a booking" in {
    go to host + "booking"
    textField(xpath("/html/body/div/div/form/div/dl[1]/dd[1]/input")).value = "30/2/2021"
    textField(xpath("/html/body/div/div/form/div/dl[2]/dd[1]/input")).value = "Tester McTesting"
    textField(xpath("/html/body/div/div/form/div/dl[3]/dd[1]/input")).value = "2"
    textField(xpath("/html/body/div/div/form/div/dl[4]/dd[1]/input")).value = "0"
    textField(xpath("/html/body/div/div/form/div/dl[5]/dd[1]/input")).value = "Popcorn, Large Coke"
    textField(xpath("/html/body/div/div/form/div/dl[6]/dd[1]/input")).value = "2"

    click on xpath("/html/body/div/div/form/div/button")
    pageTitle should be("payment page")

  }

}
