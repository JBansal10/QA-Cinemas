package Persistence.Domain

import play.api.data.Form
import play.api.data.Forms._
import slick.lifted.Tag
import slick.jdbc.MySQLProfile.backend.Database
import slick.lifted.TableQuery

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.MySQLProfile.api._
import java.time.{LocalDate, LocalTime}

case class DiscussionBoard(id: Int, content: String, date: LocalDate, time: LocalTime, postChecker: Boolean)

case class DiscussionBoards(tag: Tag) extends Table[DiscussionBoard] (tag, "discussionboard") {
  def id = column[Int]("POST_ID", O.AutoInc, O.PrimaryKey)
  def content = column[String]("CONTENT")
  def date = column[LocalDate]("POST_DATE")
  def time = column[LocalTime]("POST_TIME")
  def postChecker = column[Boolean]("POST_CHECKER")
  def * = (id, content, date, time, postChecker) <> (DiscussionBoard.tupled, DiscussionBoard.unapply)
}

object boardForm {
  val submitForm =
    Form(
      mapping(
        "id" -> number,
        "content" -> nonEmptyText,
        "date" -> localDate,
        "time" -> localTime,
        "postChecker" -> boolean
      )(DiscussionBoard.apply)(DiscussionBoard.unapply)
    )
}
