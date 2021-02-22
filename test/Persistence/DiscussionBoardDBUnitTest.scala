package Persistence

import Persistence.DAO.DiscussionBoardDAO
import Persistence.Domain.DiscussionBoardOBJ.DiscussionBoard
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.io.Source

class DiscussionBoardDBUnitTest extends AnyFlatSpec with BeforeAndAfter with Matchers {

  lazy val db = Database.forConfig("mysqlDB")

  behavior of "Discussion board table"

  before {
    val statement = Source.fromFile("resources/test-data.sql").mkString
    db.run(sqlu"#$statement")
  }
//
//  it should "create a discussion board object and pass into db" in {
//    val discBoard : DiscussionBoard = DiscussionBoard(3, "This is test content", 2, 6)
//  }

//  "DAO" should "return Right" in {
//    val dBoard: DiscussionBoard = new DiscussionBoard(3, "This is test data", "15/02/2021 14:28", 3, 8, false)
//    val result = Await.result(DiscussionBoardDAO.create(dBoard), Duration.Inf)
//    assert(result === "Discussion successfully added")
//  }

  it should "return the values when readAll is called" in {
    DiscussionBoardDAO.readAll() map { results =>
      if (results.length > 1) {
        val discBoard = results.iterator.next()
        assert(discBoard.mRating == 4)
      } else {
        assert(false)
      }
    }
  }

}
