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
    val result = Await.result(emailing(email), Duration.Inf)
    assert(result === "Email successfully sent!")

  }

}
