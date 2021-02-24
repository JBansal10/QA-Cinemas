package Persistence

import Persistence.DAO.ScreenTimeDAO
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AsyncFlatSpec
import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration.Duration
import scala.io.Source


class ScreenTimeDBUnitTest extends AsyncFlatSpec with BeforeAndAfter {


  behavior of "Screen times table"

  lazy val db = Database.forConfig("mysqlDB")

  before {
    val statement = Source.fromFile("resources/test-data.sql").mkString
    db.run(sqlu"#$statement")
  }

  it should "return a list of 12 times for Titanic" in {
    ScreenTimeDAO.readByMID(1) map { times =>
      if (times.length == 12) assert(times.headOption.get.showDay == "Monday")
      else assert(false)
    }
  }

  "readByMID" should "return specified id value" in{
    val result = Await.result(ScreenTimeDAO.readByMID(1), Duration.Inf)
    assert(result.iterator.next().showDay === "Monday")
  }


}
