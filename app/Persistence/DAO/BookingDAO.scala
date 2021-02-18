package Persistence.DAO

import Persistence.Domain.BookingFormOBJ.{Booking, Bookings}
import slick.jdbc.MySQLProfile.backend.Database
import slick.lifted.TableQuery

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.MySQLProfile.api._

object BookingDAO {

  lazy val db = Database.forConfig("mysqlDB")
  lazy val bookingTable = TableQuery[Bookings]

  def create (bookForm: Booking): Future[String] ={
    db.run(bookingTable += bookForm).map(res => "Booking succesfully added").recover {
      case ex: Exception =>
        ex.getCause.getMessage
    }
  }
  def readById (id: Int): Future[Option[Booking]] = db.run(bookingTable.filter(_.id === id).result.headOption)

  def getLastIndex(): Future[Int] = db.run(bookingTable.size.result)

}
