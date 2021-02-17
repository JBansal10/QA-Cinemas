package controllers

import play.api.data.Form
import play.api.i18n.I18nSupport
import play.api.mvc.Results.{BadRequest, Redirect}
import Persistence.DAO.{MovieDAO, PaymentDAO}
import Persistence.Domain.paymentObj.{Payment, PaymentForm}
import Persistence.DAO.{MovieDAO, ScreenTimeDAO}
import Persistence.Domain.Movie
import Persistence.Domain.ScreenTimesOBJ.ScreenTime

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
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport{

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
    MovieDAO.readAll() map(movies => Ok(views.html.listingsgallery(movies)))
  }

  def readID(id: Int) = Action.async(implicit request =>
    // Used nested? futures instead of using a join
    ScreenTimeDAO.readByMID(id).flatMap { times =>
      MovieDAO.readById(id).map {
        case Some(movie) =>
          Ok(views.html.movie(movie, times))
        case None => Ok(views.html.error("Error 404", "Could not find the movie."))
      }
    }
  )

  def homepage = Action {
    Ok(views.html.homepage("Welcome to QA Cinemas!"))
  }

  def aboutUs = Action {
    Ok(views.html.aboutUs())
  }


   def contactUs = Action {
   Ok(views.html.contactUs())
   }

  def screens = Action {
    Ok(views.html.screens())
  }

  def gettingThere = Action {
    Ok(views.html.gettingThere())
  }

  def createPayment() = Action { implicit request =>
    PaymentForm.submitForm.bindFromRequest.fold({ formWithErrors =>
      BadRequest(views.html.payment(formWithErrors))
    }, { widget =>
      createP(widget)
      Redirect("/payment")
    })
  }

  def createP(pay: Payment): Unit = {
    PaymentDAO.create(pay).onComplete {
      case Success(value) =>
        println(value)
      case Failure(exception) =>
        exception.printStackTrace()
    }
  }
 
  def tempToDo = TODO
}

