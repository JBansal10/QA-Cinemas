package controllers

import play.api.data.Form
import play.api.i18n.I18nSupport
import play.api.mvc.Results.{BadRequest, Redirect}
import Persistence.DAO.MovieDAO
import Persistence.Domain.Movie

import scala.concurrent.ExecutionContext.Implicits.global
import javax.inject._
import play.api.mvc._
import play.mvc.Action

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.{Future, TimeoutException}
import scala.util.{Failure, Success}
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Welcome to QA Cinemas!"))
  }

  def listingsGallery = Action.async { implicit request =>
    MovieDAO.readAll() map(movies => {
      var counter = 0
      val nested = new ArrayBuffer[Seq[Movie]]
      val row = new ArrayBuffer[Movie]
      val it = movies.iterator
      while (it.hasNext) {
        row addOne it.next
        counter += 1
        if (!it.hasNext) nested addOne row.toSeq
        if (counter == 3) { counter = 0; nested addOne row.toSeq; row.clear()}
      }
      Ok(views.html.listingsgallery(nested.toSeq))
    })
  }

  def readID(id: Int) = Action.async ( implicit request =>
    MovieDAO.readById(id) map(movie =>
        if (movie.isDefined) Ok(views.html.movie(movie.get))
        else Ok(views.html.index("ERROR")) // TODO needs to send to an error page for movie not found
      )
  )

  def homepage = Action{
   Ok(views.html.homepage("Welcome to QA Cinemas!"))
  }

  def aboutUs = Action {
    Ok(views.html.aboutUs())
  }

  def tempToDo = TODO
}
