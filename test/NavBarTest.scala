import controllers.routes
import junit.framework.Test
import org.scalatest._
import org.scalatestplus.selenium._
import org.openqa.selenium._
import org.openqa.selenium.chrome._
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.matchers._
import org.specs2.specification.{AfterAll, BeforeAll}

class NavBarTest extends flatspec.AnyFlatSpec with BeforeAndAfter with should.Matchers with WebBrowser  {

  val host = "http://localhost:9000"

  val homeLink = "/html/body/nav/div/div/ul/li[1]/a"

  //  implicit val targ: WebElement
  implicit val webDriver: WebDriver = new ChromeDriver()

  before {
    System.setProperty("webDriver.chrome.driver", "/public/chrome/chromedriver.exe")
//    webDriver = new ChromeDriver()
  }

  "clicking on homepage in navbar" should "take you to homepage" in {
    webDriver.get(host)
    click on xpath(homeLink)
    pageTitle should be ("Welcome to Play")
  }

  after {
    webDriver.quit()
  }
}
