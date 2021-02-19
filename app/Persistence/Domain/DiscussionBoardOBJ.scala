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
import java.time.format.DateTimeFormatter

object DiscussionBoardOBJ{

case class DiscussionBoard(id: Int, content: String, datetime: String, movieID: Int, mRating: Int, postChecker: Boolean)

val movies = TableQuery[Movies]

case class DiscussionBoards(tag: Tag) extends Table[DiscussionBoard] (tag, "discussionboard") {
  def id = column[Int]("POST_ID", O.AutoInc, O.PrimaryKey)
  def content = column[String]("CONTENT")
  def datetime = column[String]("POST_DATETIME")
  def movieID = column[Int]("MOVIE_ID")
  def mRating = column[Int]("MOVIE_RATING")
  def postChecker = column[Boolean]("POST_CHECKER")

  def movie = foreignKey("fk_movie_id", movieID, movies)(_.id, onDelete = ForeignKeyAction.Cascade)
  def * = (id, content, datetime, movieID, mRating, postChecker) <> (DiscussionBoard.tupled, DiscussionBoard.unapply)
}


object boardForm {
  val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
  val submitForm =
    Form(
      mapping(
        "id" -> default(number, 0),
        "content" -> nonEmptyText,
        "datetime" -> default(nonEmptyText, LocalDateTime.now().format(formatter)),
        "movieID" -> number,
        "mRating" -> number,
        "postChecker" -> default(boolean, false)
      )(DiscussionBoard.apply)(DiscussionBoard.unapply)
    )
}
}
