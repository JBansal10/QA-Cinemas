package Persistence.Domain

import play.api.data.Form
import play.api.data.Forms._
import slick.lifted.Tag
import slick.jdbc.MySQLProfile.backend.Database
import slick.lifted.TableQuery

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.MySQLProfile.api._

import java.time.LocalDateTime

object DiscussionBoardOBJ{

case class DiscussionBoard(id: Int, content: String, datetime: LocalDateTime, movieID: Int, mRating: Int, postChecker: Boolean)

val movies = TableQuery[Movies]

case class DiscussionBoards(tag: Tag) extends Table[DiscussionBoard] (tag, "discussionboard") {
  def id = column[Int]("POST_ID", O.AutoInc, O.PrimaryKey)
  def content = column[String]("CONTENT")
  def datetime = column[LocalDateTime]("POST_DATETIME")
  def movieID = column[Int]("MOVIE_ID")
  def mRating = column[Int]("MOVIE_RATING")
  def postChecker = column[Boolean]("POST_CHECKER")

  def movie = foreignKey("fk_movie_id", movieID, movies)(_.id, onDelete = ForeignKeyAction.Cascade)
  def * = (id, content, datetime, movieID, mRating, postChecker) <> (DiscussionBoard.tupled, DiscussionBoard.unapply)
}


object boardForm {
  val submitForm =
    Form(
      mapping(
        "id" -> number,
        "content" -> nonEmptyText,
        "datetime" -> localDateTime,
        "movieID" -> number,
        "mRating" -> number,
        "postChecker" -> boolean
      )(DiscussionBoard.apply)(DiscussionBoard.unapply)
    )
}
}
