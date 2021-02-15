package controllers

import play.api.data.Form
import play.api.i18n.I18nSupport
import play.api.mvc.Results.{BadRequest, Redirect}
import Persistence.DAO.MovieDAO
import scala.concurrent.ExecutionContext.Implicits.global
import javax.inject._
import play.api.mvc._
import play.mvc.Action

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


  def tempToDo = TODO

  def listingsGallery = Action {
    Ok(views.html.listingsgallery("This is the Listings Gallery!"))
  }

  def readMovies = Action.async { implicit request => MovieDAO.readAll() map(idunnoYeah => Ok(views.html.listingsgallery(idunnoYeah))) }

  def readID(id: Int) = Action.async( implicit request =>
    MovieDAO.readById(id) map(id => Ok(views.html.moviedesc(id)))
  )
}
