package Persistence

import Persistence.DAO.PaymentDAO
import Persistence.Domain.paymentObj.Payment
import Schema.Schemas.{createDrop, insertData}
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers
import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


class PaymentDBUnitTest extends AsyncFlatSpec with BeforeAndAfter with Matchers {

  lazy val db = Database.forConfig("mysqlDB")

  behavior of "Payment table"

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

  it should "create a payment when when create is called" in {
    val pay: Payment = new Payment(1,"john","1243131", "12/20", "123", 1)
    PaymentDAO.create(pay) map {result =>
      if (result.contains("payment details submitted successfully")) assert(true)
      else assert(false)
    }
  }
}
