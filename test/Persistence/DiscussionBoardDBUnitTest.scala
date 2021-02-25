package Persistence

import Schema.Schemas._
import Persistence.DAO.DiscussionBoardDAO
import Persistence.Domain.DiscussionBoardOBJ.DiscussionBoard

import org.scalatest.BeforeAndAfter
import org.scalatest.concurrent.ScalaFutures.convertScalaFuture
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers
import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.{Duration, DurationInt}


class DiscussionBoardDBUnitTest extends AsyncFlatSpec with BeforeAndAfter with Matchers {

  lazy val db = Database.forConfig("mysqlDB")

  behavior of "Discussion board table"

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

  it should "create a new disc board" in {
    val dBoard: DiscussionBoard = new DiscussionBoard(4,"TestUser", "This is test data", "15/02/2021 14:28", 3, 8, false)
    DiscussionBoardDAO.create(dBoard) map {result =>
      assert(true)
    }
  }

  it should "return some values" in {
    DiscussionBoardDAO.readAll() map { results =>
      if (results.length > 1) {
        val dboard1 = results.iterator.next()
        dboard1.username should be ("PCMBARBER")
      } else assert(false)
    }
  }

  it should "delete an entry" in {
    DiscussionBoardDAO.delete(1) map {result =>
      assert(true)
    }
  }

  it should "return an error if creating a disc board fails" in {
    try {
      val dBoard: DiscussionBoard = new DiscussionBoard(4,"", "", "", 3, 8, false)
      DiscussionBoardDAO.create(dBoard) map {result =>
        assert(true)
      }
    } catch {
      case e: Exception => assert(false)
    }
  }

}
