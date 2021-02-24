import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatestplus.selenium.WebBrowser
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


import java.util.concurrent.TimeUnit
import scala.io.Source

class UpcomingReleasesPageTest extends AnyFlatSpec with WebBrowser with BeforeAndAfter with Matchers {


  val host = "http://localhost:9000/"
  implicit val webDriver: WebDriver = new HtmlUnitDriver()
  webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

  org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)
    .asInstanceOf[ch.qos.logback.classic.Logger]
    .setLevel(ch.qos.logback.classic.Level.ERROR)


  behavior of "Upcoming releases"

  it should "be accessed from the home page" in {
    go to host
    click on xpath("//*[@id=\"navbarDropdown\"]")
    click on xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[3]/ul/li[2]/a")
    pageTitle should be ("Upcoming releases")
  }

  it should "show the default movies in cards" in {
    go to host + "newreleases"
    assert(find(xpath("/html/body/div/div/div/div/div[1]/div/div/p")).get.text == "Fast Hedgehog beating up an egg"
      && find(xpath("/html/body/div/div/div/div/div[2]/div/div/h5")).get.text == "Minions")
  }

  it should "lead to a movie page when clicking on a poster" in {
    go to host + "newreleases"
    click on id("Sonic the movie poster")
    pageTitle should be ("Sonic the movie")
  }
}
