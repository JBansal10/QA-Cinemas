package Persistence

import Persistence.DAO.BookingDAO
import Persistence.Domain.BookingFormOBJ.Booking
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

  "Create" should "create a new booking" in {
    val booking: Booking = new Booking(1, "20/2/2021", "Tester", 2, 1, "Test food", 1, 1)
    val result = Await.result(BookingDAO.create(booking), Duration.Inf)
    assert(result === "Booking succesfully added")
  }

  "Read by ID" should "return a value of the specified ID" in {
    val result = Await.result(BookingDAO.readById(1), Duration.Inf)
    assert(result.get.cName === "Piers")
  }

  "Get lad index" should "return the last index" in {
    val result = Await.result(BookingDAO.getLastIndex(), Duration.Inf)
    assert(result.get.cName === "Iqra")
  }

}
