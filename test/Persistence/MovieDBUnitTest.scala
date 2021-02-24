package Persistence

import Persistence.DAO.MovieDAO
import Schema.Schemas.{createDrop, insertData}
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers
import slick.jdbc.H2Profile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class MovieDBUnitTest extends AsyncFlatSpec with BeforeAndAfter with Matchers {

  lazy val db = Database.forConfig("mysqlDB")

  behavior of "Movie table"

  before {
    val futureFuncs: Future[_] = {
      val funcs: DBIO[Unit] = DBIO.seq(
        createDrop,
        insertData
      )
      db.run(funcs)
    }
    Await.result(futureFuncs, Duration.Inf)
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

  it should "return titanic when searching for titanic" in {
    MovieDAO.search("titanic") map { movies =>
      if (movies.nonEmpty) {
        movies.headOption.get.mName should be ("Titanic")
      } else assert(false)
    }
  }

  it should "return a few movies when searching for a" in {
    MovieDAO.search("a") map { movies => movies.length should be > 1 }
  }
}
