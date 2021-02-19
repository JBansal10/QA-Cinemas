package Persistence.Domain

import Persistence.Domain.BookingFormOBJ.Bookings
import play.api.data.Forms._
import play.api.data.Form
import slick.lifted.Tag
import slick.jdbc.MySQLProfile.api._

object paymentObj {

  case class Payment(id: Int = 0, cardHolderName: String, cardNo: Int, expiryDate: String, securityCode: Int, var bookingID: Int)

  val bookings = TableQuery[Bookings]

  case class Payments(tag: Tag) extends Table[Payment] (tag, "payment"){
    def id = column[Int]("PAYMENT_ID", O.AutoInc, O.PrimaryKey)
    def cardHolderName = column[String]("CARD_HOLDER_NAME")
    def cardNo = column[Int]("CARD_NO")
    def expiryDate = column[String]("EXPIRY_DATE")
    def securityCode = column[Int]("SECURITY_CODE")
    def bookingID = column[Int]("BOOKING_ID")

    def booking = foreignKey("fk_booking_id", bookingID, bookings)(_.id, onDelete = ForeignKeyAction.Cascade)

    def * = (id, cardHolderName, cardNo, expiryDate, securityCode, bookingID) <> (Payment.tupled, Payment.unapply)
  }

  object PaymentForm {
    val submitForm =
      Form(
        mapping(
          "id" -> default(number, 0),
          "cardHolderName" -> nonEmptyText,
          "cardNo" -> number,
          "expiryDate" -> nonEmptyText,
          "securityCode" -> number,
          "bookingID" -> number
        )(Payment.apply)(Payment.unapply)
      )
  }
}