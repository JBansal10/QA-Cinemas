import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.{BeforeAndAfter, flatspec}

import org.scalatest.matchers.should.Matchers
import org.scalatestplus.selenium.WebBrowser
import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import java.util.concurrent.TimeUnit
import scala.io.Source

class DiscussionBoardPageTest extends flatspec.AnyFlatSpec with WebBrowser with BeforeAndAfter with Matchers {

  lazy val db = Database.forConfig("mysqlDB")

  val host = "http://localhost:9000/"
  implicit val webDriver: WebDriver = new HtmlUnitDriver()
  webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

  behavior of "Discussions page"

  before {
    val statement = Source.fromFile("resources/test-data.sql").mkString
    db.run(sqlu"#$statement")
  }

  it should "be accessed from the homepage" in {
    go to host
    click on xpath("/html/body/nav/div/div/ul/li[5]/a")
    pageTitle should be("Discussion Board")
  }

  it should "create a discussion board" in {
    go to host + "discboard"
    textField(xpath("/html/body/div/div/form/div[1]/dl[1]/dd[1]/input")).value = "This is test content"
    textField(xpath("/html/body/div/div/form/div[1]/dl[2]/dd[1]/input")).value = "2"
    textField(xpath("/html/body/div/div/form/div[1]/dl[3]/dd[1]/input")).value = "8"
    click on xpath("/html/body/div/div/form/div[1]/button")

    go to host + "adminboard"
    assert(find(xpath("/html/body/div/div/li[3]")).get.text.contains("This is test content"))
  }

}
