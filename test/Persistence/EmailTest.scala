package Persistence

import Persistence.Domain.EmailOBJ._
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Await
import scala.concurrent.duration.Duration


class EmailTest extends AsyncFlatSpec with Matchers{

  behavior of "Email backend"

  it should "send an email" in {
    val email: Email = new Email("back@end.com", "Back End", "Testing")
    emailing(email) map {result =>
      if (result.contains("Email successfully sent")) assert(true)
      else assert(false)
    }
  }

}
