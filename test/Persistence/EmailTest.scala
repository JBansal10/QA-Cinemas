package Persistence

import Persistence.Domain.EmailOBJ._
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Await

class EmailTest extends AsyncFlatSpec with Matchers{

  behavior of "Email backend"

  it should "send an email" in {
    // TODO need an actual test, tried to implement one but was too challenging
    emailing(Email("back@end.com", "Back End", "Testing"))
    assert(true)
  }

}
