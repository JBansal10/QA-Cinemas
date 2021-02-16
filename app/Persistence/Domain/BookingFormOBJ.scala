package Persistence.Domain

import Persistence.Domain.ScreenTimesOBJ.ScreenTimes
import play.api.data.Form
import play.api.data.Forms._
import slick.lifted.Tag
import slick.jdbc.MySQLProfile.api._

object BookingFormOBJ {

  case class Booking(id: Int, screenDate: String, cName: String, adults: Int, childs: Int, concession: String, screenID: Int)

  val screentime = TableQuery[ScreenTimes]

  case class Bookings(tag: Tag) extends Table[Booking] (tag, "booking") {
    def id = column[Int]("FORM_ID", O.AutoInc, O.PrimaryKey)

    def screenDate = column[String]("SCREEN_DATE")
//    def screenTime = column[String]("SCREEN_TIME")
    def cName = column[String]("CUSTOMER_NAME")
    def adults = column[Int]("ADULTS")
    def childs = column[Int]("CHILDS")
    def concession = column[String]("CONCESSION")
    def screenID = column[Int]("SCREEN_ID")

    def screentimes = foreignKey("fk_screentime_id", screenID, screentime)(_.id, onDelete = ForeignKeyAction.Cascade)

    def * = (id, screenDate, cName, adults, childs, concession, screenID) <> (Booking.tupled, Booking.unapply)
  }

  object bookingForm {
    val bookForm = Form (
      mapping (
        "id" -> number,
        "screenDate" -> nonEmptyText,
        "cName" -> nonEmptyText,
        "adults" -> number,
        "childs" -> number,
        "concession" -> nonEmptyText,
        "screenId" -> number
      )(Booking.apply)(Booking.unapply)
    )
  }

}
