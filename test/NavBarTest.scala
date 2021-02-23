import org.scalatest._
import org.scalatestplus.selenium._
import org.scalatest.matchers._


class NavBarTest extends flatspec.AnyFlatSpec with BeforeAndAfter with should.Matchers with HtmlUnit {

  val host = "http://localhost:9000/"

  "clicking on homepage in navbar" should "take you to homepage" in {
    org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)
      .asInstanceOf[ch.qos.logback.classic.Logger]
      .setLevel(ch.qos.logback.classic.Level.ERROR)

    go to host
    click on xpath("/html/body/nav/div/div/ul/li[1]/a")
    pageTitle should be ("QA Cinema")
  }

}
