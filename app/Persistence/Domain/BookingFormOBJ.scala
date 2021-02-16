package Persistence.Domain

import play.api.data.Form
import play.api.data.Forms._
import slick.lifted.Tag
import slick.jdbc.MySQLProfile.api._

object BookingFormOBJ {

  case class Booking(id: Int, mID: Int, screenDate: String, screenTime: String, cName: String, nSeats: Int, adults: Int, childs: Int, concession: String)

  case class Bookings(tag: Tag) extends Table[Booking] (tag, "booking") {
    def id = column[Int]("FORM_ID", O.AutoInc, O.PrimaryKey)
    def mID = column[Int]("MOVIE_DESC")
    def screenDate = column[String]("SCREEN_DATE")
    def screenTime = column[String]("SCREEN_TIME")
    def cName = column[String]("CUSTOMER_NAME")
    def nSeats = column[Int]("NUMBER_OF_SEATS")
    def adults = column[Int]("ADULTS")
    def childs = column[Int]("CHILDS")
    def concession = column[String]("Concession")

    def * = (id, mID,screenDate, screenTime, cName, nSeats, adults, childs, concession) <> (Booking.tupled, Booking.unapply)
  }

  object bookingForm {
    val bookForm = Form (
      mapping (
        "id" -> number,
        "mID" -> number,
        "screenDate" -> nonEmptyText,
        "screenTime" -> nonEmptyText,
        "cName" -> nonEmptyText,
        "nSeats" -> number,
        "adults" -> number,
        "childs" -> number,
        "concession" -> nonEmptyText
      )(Booking.apply)(Booking.unapply)
    )
  }

}
