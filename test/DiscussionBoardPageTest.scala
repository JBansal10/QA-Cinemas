import Schema.Schemas.{createDrop, insertData}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.{BeforeAndAfter, flatspec}
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.selenium.WebBrowser
import slick.jdbc.H2Profile.api._

import java.util.concurrent.TimeUnit
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration


class DiscussionBoardPageTest extends flatspec.AnyFlatSpec with WebBrowser with BeforeAndAfter with Matchers {

  lazy val db = Database.forConfig("mysqlDB")

  val host = "http://localhost:9000/"
  implicit val webDriver: WebDriver = new HtmlUnitDriver()
  webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)


  behavior of "Discussions page"

  before {
    val futureFuncs: Future[_] = {
      val funcs: DBIO[Unit] = DBIO.seq(
        createDrop,
        insertData
      )
      db.run(funcs)
    }
    Await.result(futureFuncs, Duration.Inf)
  }

  org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)
    .asInstanceOf[ch.qos.logback.classic.Logger]
    .setLevel(ch.qos.logback.classic.Level.ERROR)

  it should "be accessed from the homepage" in {
    go to host
    click on xpath("/html/body/nav/div/div/ul/li[6]/a")
    pageTitle should be("Discussion Board")
  }

  it should "create a discussion board" in {
    go to host + "discboard"
    textField(xpath("/html/body/div/div/div/div[1]/form/div/dl[1]/dd[1]/input")).value = "Test user"
    textField(xpath("/html/body/div/div/div/div[1]/form/div/dl[2]/dd[1]/input")).value = "This is test content"
    textField(xpath("/html/body/div/div/div/div[1]/form/div/dl[3]/dd[1]/input")).value = "2"
    textField(xpath("/html/body/div/div/div/div[1]/form/div/dl[4]/dd[1]/input")).value = "8"
    click on xpath("/html/body/div/div/div/div[1]/form/div/button")

    go to host + "adminboard"
    assert(find(xpath("/html/body/div/div/ul[3]/li[1]")).get.text.contains("This is test content"))
  }

}
