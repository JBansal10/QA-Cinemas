package Persistence

import Persistence.DAO.ScreenTimeDAO
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AsyncFlatSpec
import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.ExecutionContext
import scala.io.Source


class ScreenTimeDBUnitTest extends AsyncFlatSpec with BeforeAndAfter {


  behavior of "Screen times table"

  before { // runs before each test
    // should set up test data
  }

  it should "return a list of 12 times for Titanic" in {
    ScreenTimeDAO.readByMID(1) map { times =>
      if (times.length == 12) assert(times.headOption.get.showDay == "Monday")
      else assert(false)
    }
  }


}
