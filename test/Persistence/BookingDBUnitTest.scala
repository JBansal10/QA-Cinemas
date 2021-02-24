package Persistence

import Persistence.DAO.BookingDAO
import Persistence.Domain.BookingFormOBJ.Booking
import Schema.Schemas.{booking, createDrop, insertData}
import org.scalatest.BeforeAndAfter
import org.scalatest.concurrent.ScalaFutures.convertScalaFuture
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers
import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.duration.{Duration, DurationInt}
import scala.concurrent.{Await, Future}


class BookingDBUnitTest extends AsyncFlatSpec with BeforeAndAfter with Matchers {

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

  it should "create a booking" in {
    val booking: Booking = new Booking(1, "20/2/2021", "Tester", 2, 1, "Test food", 1, 1)
    BookingDAO.create(booking).isReadyWithin(2.second) should be (true)
  }

  it should "return the first entry in the table" in {
    BookingDAO.readById(1) map { booking =>
      if(booking.isDefined) booking.get.cName should be ("Piers")
      else assert(false)
    }
  }

  it should "return the last entry in the table" in {
    BookingDAO.getLastIndex() map {booking =>
      if(booking.isDefined) booking.get.cName should be ("Iqra")
      else assert(false)
    }
  }


}
