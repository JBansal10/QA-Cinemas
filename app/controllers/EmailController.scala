package controllers


import Persistence.Domain.Emailobj.{Email, EmailForm}
import courier.{Envelope, Mailer, Text}
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject
import javax.mail.internet.InternetAddress
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}


class EmailController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {
  val receiveEmail = "teamfireqa@gmail.com"

  def contact = Action { implicit request =>
    Ok(views.html.contactUs(EmailForm.emailForm))
  }

  def sendEmail = Action { implicit request =>
    EmailForm.emailForm.bindFromRequest.fold({ formWithErrors =>
      BadRequest(views.html.contactUs(formWithErrors))
    }, {widget =>
      send(widget)
      Redirect("/")
    })
  }

  def send(email: Email) = {
    // To send email with gmail, if you have two-factor authentication enabled, you need to set up an app password in
    // your account settings, as courier doesn't support 2fa.
    val mailer = Mailer("smtp.gmail.com", 587)
      .auth(true)
      // removed email
      .as("teamfireqa@gmail.com", "A12345678B")
      .startTls(true)()

    mailer(Envelope
      .from(new InternetAddress(email.getSender))
      .to(new InternetAddress(receiveEmail))
      .replyTo(new InternetAddress(email.getSender))
      .subject(email.getSubject())
      .content(Text(email.getBody())))
      .onComplete {
        case Success(value) => println("message delivered")
        case Failure(exception) => exception.printStackTrace()
      }
  }

}
