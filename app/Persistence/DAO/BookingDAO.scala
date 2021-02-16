package Persistence.DAO

import Persistence.Domain.BookingFormOBJ.Booking
import slick.jdbc.MySQLProfile.backend.Database
import slick.lifted.TableQuery

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.MySQLProfile.api._

object BookingDAO {

  lazy val db = Database.forConfig("mysqlDB")
  lazy val bookingTable = TableQuery[Booking]

}
