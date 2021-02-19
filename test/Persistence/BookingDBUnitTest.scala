package Persistence

import Persistence.DAO.BookingDAO
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec

import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source

class BookingDBUnitTest extends AnyFlatSpec with BeforeAndAfter{

  lazy val db = Database.forConfig("mysqlDB")

  behavior of "Booking table"
  before { // runs before each test
    val statement = Source.fromFile("resources/test-data.sql").mkString
    db.run(sqlu"#$statement")
  }

  it should "return the first booking in the table" in {
    BookingDAO.readById(1) map {booking =>
      if (booking.isDefined) assert(booking.get.cName == "Piers")
      assert(false)
    }
  }

}
