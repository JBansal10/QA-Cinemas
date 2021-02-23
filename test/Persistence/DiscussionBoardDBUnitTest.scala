package Persistence

import Schema.Schemas._
import Persistence.DAO.DiscussionBoardDAO
import Persistence.Domain.DiscussionBoardOBJ.DiscussionBoard
import akka.actor.Status.Success
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.io.Source

class DiscussionBoardDBUnitTest extends AnyFlatSpec with BeforeAndAfter with Matchers {

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

  "DAO" should "return Right" in {
    val dBoard: DiscussionBoard = new DiscussionBoard(3,"TestUser", "This is test data", "15/02/2021 14:28", 3, 8, false)
    val result = Await.result(DiscussionBoardDAO.create(dBoard), Duration.Inf)
    assert(result === "Discussion successfully added")
  }

  "readAll" should "return some values" in {
    val result = Await.result(DiscussionBoardDAO.readAll(), Duration.Inf)
    assert(result.iterator.next().username === "PCMBARBER")
  }

  "delete" should "remove an entry" in {
    val result = Await.result(DiscussionBoardDAO.delete(1), Duration.Inf)
    assert(result === 1)
  }

}
