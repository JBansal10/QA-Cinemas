package Persistence.Domain

import play.api.data.Forms._
import play.api.data.Form
import slick.lifted.Tag
import slick.jdbc.MySQLProfile.api._

object paymentObj {

  case class Payment(id: Int, cardHolderName: String, cardNo: Int, expiryDate: String, securityCode: Int)

  case class Payments(tag: Tag) extends Table[Payment] (tag, "payment"){
    def id = column[Int]("PAYMENT_ID", O.AutoInc, O.PrimaryKey)
    def cardHolderName = column[String]("CARD_HOLDER_NAME")
    def cardNo = column[Int]("CARD_NO")
    def expiryDate = column[String]("EXPIRY_DATE")
    def securityCode = column[Int]("SECURITY_CODE")
    def * = (id, cardHolderName, cardNo, expiryDate, securityCode) <> (Payment.tupled, Payment.unapply)
  }

  object PaymentForm {
    val submitForm =
      Form(
        mapping(
          "id" -> number,
          "cardHolderName" -> nonEmptyText,
          "cardNo" -> number,
          "expiryDate" -> nonEmptyText,
          "securityCode" -> number
        )(Payment.apply)(Payment.unapply)
      )
  }
}