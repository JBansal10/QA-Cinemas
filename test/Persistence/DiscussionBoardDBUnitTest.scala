package Persistence

import Persistence.DAO.DiscussionBoardDAO
import Persistence.Domain.DiscussionBoardOBJ.DiscussionBoard
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec
import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source

class DiscussionBoardDBUnitTest extends AnyFlatSpec with BeforeAndAfter {

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
