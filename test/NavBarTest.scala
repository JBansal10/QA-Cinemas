import org.scalatest._
import org.scalatestplus.selenium._
import org.scalatest.matchers._

import java.util.concurrent.TimeUnit


class NavBarTest extends flatspec.AnyFlatSpec with BeforeAndAfter with should.Matchers with HtmlUnit {

  val host = "http://localhost:9000/"
  webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

  "clicking on homepage in navbar" should "take you to homepage" in {
    go to host
    click on xpath("/html/body/nav/div/div/ul/li[1]/a")
    pageTitle should be ("Welcome to Play")
  }

}
