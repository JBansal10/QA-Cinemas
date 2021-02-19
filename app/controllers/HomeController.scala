package controllers

import play.api.data.Form
import play.api.i18n.I18nSupport
import play.api.mvc.Results.{BadRequest, Redirect}
import Persistence.DAO.{BookingDAO, DiscussionBoardDAO, MovieDAO, PaymentDAO, ScreenTimeDAO}
import Persistence.Domain.paymentObj.{Payment, PaymentForm}
import Persistence.Domain.BookingFormOBJ.{Booking, bookingForm}
import Persistence.Domain.DiscussionBoardOBJ.{DiscussionBoard, boardForm}
import Persistence.Domain.{Movie, SearchOBJ}
import Persistence.DAO.{MovieDAO, ScreenTimeDAO}
import Persistence.Domain.{EmailOBJ, Movie}
import Persistence.Domain.ScreenTimesOBJ.ScreenTime

import scala.concurrent.ExecutionContext.Implicits.global
import javax.inject._
import play.api.mvc._
import play.mvc.Action

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future, TimeoutException}
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
    MovieDAO.readAll() map(movies => Ok(views.html.listingsgallery(movies)))
  }

  def readID(id: Int) = Action.async(implicit request =>
    // Used nested? futures instead of using a join
    ScreenTimeDAO.readByMID(id).flatMap { times =>
      MovieDAO.readById(id).map {
        case Some(movie) => Ok(views.html.movie(movie, times))
        case None => Ok(views.html.error("Error 404", "Could not find the movie."))
      }
    }
  )

  def discBoardRead() = Action.async {implicit request => DiscussionBoardDAO.readAll() map (working => Ok(views.html.AdminDiscBoard(working)))}

  def createDiscBoard() = Action.async {implicit request =>
    DiscussionBoardDAO.readAll() map { discussions =>
      boardForm.submitForm.bindFromRequest().fold({ formsWithError =>
          BadRequest(views.html.discboard(formsWithError, discussions))
      }, {
        creator => createFunc(creator)
          Redirect("/discboard")
      }
    )}
  }

  def createFunc(discBoard: DiscussionBoard): Unit = {
    DiscussionBoardDAO.create(discBoard).onComplete {
      case Success(value) =>
        print(value)
      case Failure(exception) =>
        exception.printStackTrace()
    }
  }
  def homepage = Action {
    Ok(views.html.homepage("Welcome to QA Cinemas!"))
  }

  def aboutUs = Action {
    Ok(views.html.aboutUs())
  }


  def contactUs = Action { implicit request =>
    EmailOBJ.emailContactForm.submitForm.bindFromRequest().fold({ contactFormWithErrors =>
      BadRequest(views.html.contactUs(contactFormWithErrors))
    }, { widget =>
      EmailOBJ.emailing(widget)
      Ok(views.html.contactUs(null))
    })


  }

  def screens = Action {
    Ok(views.html.screens())
  }

  def gettingThere = Action {
    Ok(views.html.gettingThere())
  }

  def openingTimes = Action {
    Ok(views.html.openingTimes())
  }

  def createPayment() = Action { implicit request =>
    PaymentForm.submitForm.bindFromRequest().fold({ formWithErrors =>
      BadRequest(views.html.payment(formWithErrors))
    }, { widget =>
      createP(widget)
      Redirect("/bookingcomplete")
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


  def createBooking() = Action.async { implicit request =>
    bookingForm.bookForm.bindFromRequest().fold({ bookingFormWithErrors =>
      Future {BadRequest(views.html.booking(bookingFormWithErrors))}
    }, { widget =>
      createB(widget)
      BookingDAO.getLastIndex() map { id => Redirect("/payment/" + id) }
    })
  }

  def createB(book: Booking): Unit = {
    BookingDAO.create(book).onComplete {
      case Success(value) =>
        println(value)
      case Failure(exception) =>
        exception.printStackTrace()
    }
  }

  def bookingComplete(id: Int) = Action.async { implicit request =>
    BookingDAO.readById(id).map {
      case Some(thing) => Ok(views.html.bookingcomplete(thing))
      case None => NotFound(views.html.error("Error 404", "Could not find the booking."))
    }
  }


  def search = Action.async { implicit request =>
    SearchOBJ.searchForm.bindFromRequest.fold(
      formWithErrors => Future { Ok(views.html.searchresults(Seq[Movie]())) },
      search => MovieDAO.search(search.term) map { results =>
        Ok(views.html.searchresults(results))
      }
    )

  }

  def tempToDo = TODO
}

