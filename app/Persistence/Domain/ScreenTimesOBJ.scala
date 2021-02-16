package Persistence.Domain

import play.api.data.Form
import play.api.data.Forms._
import slick.lifted.Tag
import slick.jdbc.MySQLProfile.backend.Database
import slick.lifted.TableQuery

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.MySQLProfile.api._

object ScreenTimesOBJ {

  case class ScreenTime(id: Int, mID: Int, showDay: String, showTime: String, screenType: String)

  val movies = TableQuery[Movies]

  case class ScreenTimes(tag: Tag) extends Table[ScreenTime] (tag, "screentime") {
    def id = column[Int]("SCREENTIME_ID", O.PrimaryKey, O.AutoInc)
    def mID = column[Int]("SCREENTIME_MOVIE")
    def showDay = column[String]("SCREENTIME_DAY")
    def showTime = column[String]("SCREENTIME_TIME")
    def screenType = column[String]("SCREEN_TYPE")

    def movie = foreignKey("fk_movie_id", mID, movies)(_.id, onDelete = ForeignKeyAction.Cascade)

    def * = (id, mID, showDay, showTime, screenType) <> (ScreenTime.tupled, ScreenTime.unapply)
  }
}
