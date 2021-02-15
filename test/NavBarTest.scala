import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.scalatest._
import org.scalatestplus.selenium._
import org.scalatest.matchers._


class NavBarTest extends flatspec.AnyFlatSpec with BeforeAndAfter with should.Matchers with HtmlUnit {

  val host = "http://localhost:9000/"

  "clicking on homepage in navbar" should "take you to homepage" in {
    go to host
    click on xpath("/html/body/nav/div/div/ul/li[1]/a")
    pageTitle should be ("Welcome to Play")
  }

}
