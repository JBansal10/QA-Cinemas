package Persistence

import Persistence.DAO.MovieDAO
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.{AnyFlatSpec, AsyncFlatSpec}
import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.ExecutionContext
import scala.io.Source

class MovieDBUnitTest extends AsyncFlatSpec with BeforeAndAfter {


  behavior of "Movie table"

  before { // runs before each test
    // should set up test data
  }

  it should "return a few values when readAll is called" in {
    MovieDAO.readAll() map { results =>
      if (results.length > 1) {
        val movie1 = results.iterator.next()
        assert(movie1.imageURL == "titanic.png")
      } else assert(false)
    }
  }

  it should "return the first movie in the table" in {
    MovieDAO.readById(1) map { movie =>
      if (movie.isDefined) assert(movie.get.mName == "Titanic")
      else assert(false)
    }
  }
}
