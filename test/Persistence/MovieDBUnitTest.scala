package Persistence

import Persistence.DAO.MovieDAO
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.{AnyFlatSpec, AsyncFlatSpec}
import org.scalatest.matchers.should.Matchers
import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.ExecutionContext
import scala.io.Source

class MovieDBUnitTest extends AsyncFlatSpec with BeforeAndAfter with Matchers {


  behavior of "Movie table"

  before { // runs before each test
    // should set up test data
  }

  it should "return a few values when readAll is called" in {
    MovieDAO.readAll() map { results =>
      if (results.length > 1) {
        val movie1 = results.iterator.next()
        movie1.imageURL should be ("titanic.png")
      } else assert(false)
    }
  }

  it should "return the first movie in the table" in {
    MovieDAO.readById(1) map { movie =>
      if (movie.isDefined) movie.get.mName should be ("Titanic")
      else assert(false)
    }
  }

  // vvv Search tests vvv
  
  it should "return titanic when searching for titanic" in {
    MovieDAO.search("titanic") map { movies =>
      if (movies.length > 0) {
        movies.headOption.get.mName should be ("Titanic")
      } else assert(false)
    }
  }

  it should "return a few movies when searching for a" in {
    MovieDAO.search("a") map { movies => movies.length should be > 1 }
  }
}
