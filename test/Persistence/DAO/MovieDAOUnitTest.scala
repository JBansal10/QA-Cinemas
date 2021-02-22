package Persistence.DAO

import org.mockito.Mockito._
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar
import Persistence.DAO.MovieDAO
import Persistence.Domain.{Movie, Movies}
import org.mockito.Mockito
import slick.jdbc.JdbcBackend.Database
import slick.lifted.TableQuery

import scala.concurrent.Future



class MovieDAOUnitTest extends AsyncFlatSpec with BeforeAndAfter with Matchers with MockitoSugar {

  behavior of "MovieDAO"

  it should "return a sequence of movies on read all" in {
    val db = mock[Database]
    when(db.run(AnyRef)).thenReturn(Future {
      Seq(Movie(1, "Titanic", 123, "Sad", "3", "Fish probably", "Jeff Bezos", "titanic.png", "Boat drowning", 4.95, 10.95, true))
    })

    mockDAO.readAll map { result =>
      result should be (Seq(Movie(1, "Titanic", 123, "Sad", "3", "Fish probably", "Jeff Bezos", "titanic.png", "Boat drowning", 4.95, 10.95, true)))
    }
  }
}
