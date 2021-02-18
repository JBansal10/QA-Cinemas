package Persistence.Domain
import courier._, Defaults._


object Email {


  val mailer = Mailer("smtp.gmail.com", 587)
      .auth(true)
      .as("teamfireqa@gmail.com", "A12345678B!")
      .startTls(true)()


    def emailing {
      mailer(Envelope.from("you" `@` "gmail.com"))
        .to("teamfire@gmail.com")
        .cc("dad@gmail.com")
        .subject("miss you")
        .content(Text("hi mom")).onSuccess {
        case _ => println("message delivered")
      }

      mailer(Envelope.from("you" `@` "work.com")
        .to("boss" `@` "work.com")
        .subject("tps report")
        .content(Multipart()
          .attach(new java.io.File("tps.xls"))
          .html("<html><body><h1>IT'S IMPORTANT</h1></body></html>")))
        .onSuccess {
          case _ => println("delivered report")
        }
    }
}
