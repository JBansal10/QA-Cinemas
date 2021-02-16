package Persistence

import Persistence.DAO.MovieDAO
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec

import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.backend.Database

import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source

class MovieDBUnitTest extends AnyFlatSpec with BeforeAndAfter {


  lazy val db = Database.forConfig("mysqlDB")

  behavior of "Movie table"

  before { // runs before each test
    val statement = Source.fromFile("resources/test-data.sql").mkString
    db.run(sqlu"#$statement")
  }

  it should "return a few values when readAll is called" in {
    MovieDAO.readAll() map { results =>
      if (results.length > 1) {
        val movie1 = results.iterator.next()
        assert(movie1.imageURL == "titanic.png")
      } else {
        assert(false)
      }
    }
  }

  it should "return the first movie in the table" in {
    MovieDAO.readById(1) map {movie =>
      if (movie.isDefined) assert(movie.get.mName == "Titanic")
      assert(false)
    }
  }

}
