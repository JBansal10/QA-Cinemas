package Persistence

import Persistence.DAO.BookingDAO
import Schema.Schemas.{createDrop, insertData}
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec
import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.io.Source

class BookingDBUnitTest extends AnyFlatSpec with BeforeAndAfter{

  lazy val db = Database.forConfig("mysqlDB")

  behavior of "Booking table"
  before {
    val futureFuncs: Future[_] = {
      val funcs: DBIO[Unit] = DBIO.seq(
        createDrop,
        insertData
      )
      db.run(funcs)
    }
    Await.result(futureFuncs, Duration.Inf)
  }

  it should "return the first booking in the table" in {
    BookingDAO.readById(1) map {booking =>
      if (booking.isDefined) assert(booking.get.cName == "Piers")
      assert(false)
    }
  }

}
