package Persistence.Domain
import courier._
import Defaults._
import play.api.data.Form
import play.api.data.Forms._

import javax.mail.internet.InternetAddress
import scala.util.{Failure, Success}


object EmailOBJ {

  case class Email(from: String, name: String, content: String)

  val mailer = Mailer("smtp.gmail.com", 587)
      .auth(true)
      .as("teamfireqa@gmail.com", "A12345678B!")
      .startTls(true)()


  def emailing(email: Email): Unit = {
    val text = new StringBuilder()
    text.append("From: ").append(email.name).append(" <").append(email.from).append(">\n").append(email.content)
    mailer(Envelope.from("teamfireqa" `@` "gmail.com")
      .to("teamfireqa" `@` "gmail.com") // can change destination
      .subject("QACinemas website from " + email.name)
      .content(Text(text.mkString))).onComplete {
      case Success(value) => println("Sent email")
      case Failure(exception) => exception.printStackTrace()
    }
  }

  object emailContactForm {
    val submitForm = Form(
      mapping(
        "from" -> email,
        "name" -> nonEmptyText,
        "content" -> nonEmptyText
      )(Email.apply)(Email.unapply)
    )
  }

}
