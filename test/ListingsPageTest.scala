import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.{BeforeAndAfter, flatspec}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers._
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.selenium.{HtmlUnit, WebBrowser}

import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import java.util.concurrent.TimeUnit
import scala.io.Source

class ListingsPageTest extends flatspec.AnyFlatSpec with WebBrowser with BeforeAndAfter with Matchers {

  lazy val db = Database.forConfig("mysqlDB")

  val host = "http://localhost:9000/"
  implicit val webDriver: WebDriver = new HtmlUnitDriver()
  webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

  org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)
    .asInstanceOf[ch.qos.logback.classic.Logger]
    .setLevel(ch.qos.logback.classic.Level.ERROR)

  behavior of "Listings page"

  it should "be accessed from the home page" in {
    go to host
    click on xpath("//*[@id=\"navbarDropdown\"]")
    click on xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[3]/ul/li[1]/a")
    pageTitle should be ("Listings gallery")
  }

  it should "show the default movies in cards" in {
    go to host + "listgallery"
    assert(find(xpath("/html/body/div/div/div/div/div[1]/div/div/p")).get.text == "Boat drowning"
      && find(xpath("/html/body/div/div/div/div/div[2]/div/div/h5")).get.text == "Nemo"
      && find(xpath("/html/body/div/div/div/div/div[3]/div/div/p")).get.text == "Toys running away from their owner and doing other stuff"
      && find(xpath("/html/body/div/div/div/div/div[4]/div/div/h5")).get.text == "Transformers")
  }

  it should "lead to a movie page when clicking on a poster" in {
    go to host + "listgallery"
    click on id("Toy Story poster")
    pageTitle should be ("Toy Story")
  }
}
