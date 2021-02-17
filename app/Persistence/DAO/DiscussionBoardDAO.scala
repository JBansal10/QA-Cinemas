package Persistence.DAO

import Persistence.Domain.DiscussionBoardOBJ.{DiscussionBoard, DiscussionBoards}
import slick.jdbc.MySQLProfile.backend.Database
import slick.lifted.TableQuery

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.MySQLProfile.api._

object DiscussionBoardDAO {

  lazy val db = Database.forConfig("mysqlDB")
  lazy val boardTable: TableQuery[DiscussionBoards] = TableQuery[DiscussionBoards]

  def create(discBoard: DiscussionBoard): Future[String] = {
    db.run(boardTable += discBoard).map(res => "Discussion successfully added").recover {
      case ex: Exception =>
        ex.getCause.getMessage
    }
  }

    def readAll(): Future[Seq[DiscussionBoard]] = db.run(boardTable.result)

}
