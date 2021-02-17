package Persistence

import Persistence.DAO.PaymentDAO
import Persistence.Domain.paymentObj.Payment
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec
import slick.jdbc.MySQLProfile.backend.Database
import slick.jdbc.H2Profile.api._

import scala.io.Source

class PaymentDBUnitTest extends AnyFlatSpec with BeforeAndAfter{

  lazy val db = Database.forConfig("mysqlDB")

  behavior of "Payment table"

  before {
    val statement = Source.fromFile("resources/test-data.sql").mkString
    db.run(sqlu"#$statement")
  }
// -------------NOT FINISHED----------------
//  it should "create a payment when when create is called" in {
//    val pay: Payment = new Payment(1,"john",1243131, "12/20", 123, 1)
//    PaymentDAO.create(pay) map { results =>
//      if (results.length > 1) {
//        val movie1 = results.iterator.next()
//        assert(movie1.imageURL == "titanic.png")
//      } else {
//        assert(false)
//      }
//    }
//  }


}
