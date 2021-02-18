import org.openqa.selenium.{WebDriver, WebElement}
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.selenium.WebBrowser

import java.util.concurrent.TimeUnit

class SearchTests extends AnyFlatSpec with WebBrowser with Matchers{

  val host = "http://localhost:9000/"
  implicit val webDriver: WebDriver = new HtmlUnitDriver()
  webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

  behavior of "Search function"

  it should "redirect to a search results page when clicking the search button" in {
    go to host
    click on id("searchButton")
    pageTitle should be ("Search results")
  }

  it should "direct us to the titanic page when searching for titanic and clicking on the first result" in {
    go to host
    textField(id("searchText")).value = "titanic"
    click on id("searchButton")
    click on xpath("/html/body/div/div/ul/li/a")
    pageTitle should be ("Titanic")
  }

  it should "have 2 elements in the search results when we search for the term a" in {
    go to host
    textField(id("searchText")).value = "a"
    click on id("searchButton")
    val titanic = find(xpath("/html/body/div/div/ul/li[1]/a")).getOrElse(null)
    val transformers = find(xpath("/html/body/div/div/ul/li[2]/a")).getOrElse(null)
    if (titanic == null || transformers == null) assert(false)
    else assert(titanic.text.contains("Titanic")
      && transformers.text.contains("Transformers"))
  }
}
