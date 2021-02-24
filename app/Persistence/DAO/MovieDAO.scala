package Persistence.DAO


import Persistence.DAO.BookingDAO.bookingTable

import Persistence.Domain.BookingFormOBJ.Booking
import Persistence.Domain._
import slick.jdbc.MySQLProfile.backend.Database
import slick.lifted.TableQuery

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.MySQLProfile.api._

object MovieDAO {

  lazy val db = Database.forConfig("mysqlDB")
  lazy val movieTable: TableQuery[Movies] = TableQuery[Movies]

  def readAll(): Future[Seq[Movie]] = db.run(movieTable.result)

  def readById(id: Int): Future[Option[Movie]] = db.run(movieTable.filter(_.id === id).result.headOption)

  def search(term: String): Future[Seq[Movie]] = {
    val formedTerm = "%" + term + "%"
    // cant reduce the below statement
    val query = movieTable.filter(m => (m.mName like formedTerm) || (m.director like formedTerm) || (m.actors like formedTerm))

    db.run(query.result)
  }

  def totalPrice(booking: Booking): Future[BigDecimal] = {
    val bidDec: BigDecimal = 999.99
    db.run(movieTable.filter(_.id === booking.movieID).result.headOption).map{ movie =>
      movie.get.aPrice * booking.adults + movie.get.cPrice * booking.childs
    }recover {
      case exception: Exception => exception.printStackTrace();
        bidDec
    }
  }

  def getLastIndex(): Future[Option[Booking]] = db.run(bookingTable.sortBy(_.id.desc).result.headOption)
}


