package Persistence

import Persistence.DAO.ScreenTimeDAO
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec
import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source

class ScreenDBTest extends AnyFlatSpec with BeforeAndAfter {

  lazy val db = Database.forConfig("mysqlDB")

  behavior of "Screen Table"

  before {
    val statement = Source.fromFile("resources/test-data.sql").mkString
    db.run(sqlu"#$statement")
  }

  it should "read one insert when the readById is called" in {
    ScreenTimeDAO.readByMID(1) map { results =>
      if (results.length == 12) {
        val screen1 = results.iterator.next()
        assert(screen1.screenType == "Standard")
      } else
        assert(false)
    }

    }
}
