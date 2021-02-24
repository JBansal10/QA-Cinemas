package Persistence

import Persistence.DAO.ScreenTimeDAO
import Schema.Schemas.{createDrop, insertData}
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers
import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class ScreenDBTest extends AsyncFlatSpec with Matchers with BeforeAndAfter {

  lazy val db = Database.forConfig("mysqlDB")

  behavior of "Screen Table"

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

  it should "read one insert when the readById is called" in {
    ScreenTimeDAO.readByMID(1) map { results =>
      if (results.length > 1) {
        val screen1 = results.iterator.next()
        assert(screen1.screenType == "Standard")
      } else
        assert(false)
    }

    }
}
