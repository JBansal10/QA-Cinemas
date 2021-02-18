package Persistence.Domain
import courier._, Defaults._
import play.api.data.Form
import play.api.data.Forms._

object Emailobj {

  case class Email(sender: String, subject: String, body: String) {
    def getSender() = sender
    def getSubject() = subject
    def getBody() = body
  }

  case class EmailForm(sender:String,subject:String,body:String )
  object EmailForm {
    val emailForm = Form(mapping(
      "sender" -> nonEmptyText,
      "subject" -> nonEmptyText,
      "body" -> nonEmptyText
    )(Email.apply)(Email.unapply))
  }
}
