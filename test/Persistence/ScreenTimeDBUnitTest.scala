package Persistence

import Persistence.DAO.ScreenTimeDAO
import Schema.Schemas.{createDrop, insertData}
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AsyncFlatSpec
import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


class ScreenTimeDBUnitTest extends AsyncFlatSpec with BeforeAndAfter {

  lazy val db = Database.forConfig("mysqlDB")

  behavior of "Screen times table"

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

  it should "return a list of 12 times for Titanic" in {
    ScreenTimeDAO.readByMID(1) map { times =>
      if (times.length == 12) assert(times.headOption.get.showDay == "Monday")
      else assert(false)
    }
  }


}
