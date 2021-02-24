import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.selenium.WebBrowser

import java.util.concurrent.TimeUnit

class MoviePageTest extends AnyFlatSpec with WebBrowser with BeforeAndAfter with Matchers{

  val host = "http://localhost:9000/"

  implicit val webDriver: WebDriver = new HtmlUnitDriver()
  webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

  org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)
    .asInstanceOf[ch.qos.logback.classic.Logger]
    .setLevel(ch.qos.logback.classic.Level.ERROR)

  behavior of "Movie page"

  it should "be able to be navigated to from the listings page" in {
      go to host + "/listgallery"
      click on id("Titanic poster")
      pageTitle should be ("Titanic")
  }

  it should "show the correct movie picture and screen times" in {
    go to host + "/movie/1"
    assert(find(xpath("/html/body/div/div/div[1]/img")).get.attribute("src") == Some(host + "assets/images/titanic.png")
      && find(xpath("/html/body/div/div/div[2]/div/div/table/tbody/tr[2]/td")).get.text.contains("20:15 (Deluxe)"))
  }
}
