package Persistence.Domain
import play.api.data.Form
import play.api.data.Forms._
import slick.jdbc.MySQLProfile.api._

import scala.collection.mutable.ArrayBuffer

case class Movie(id: Int, mName: String, year: Int, genre: String, desc: String, aRating: String, actors: String, director: String, imageURL: String) {

}

case class Movies(tag: Tag) extends Table[Movie] (tag, "movies") {
  def id = column[Int]("MOVIE_ID", O.PrimaryKey, O.AutoInc)
  def mName = column[String]("MOVIE_NAME")
  def year = column[Int]("YEAR")
  def genre = column[String]("GENRE")
  def aRating = column[String]("AGE_RATING")
  def actors = column[String]("MOVIE_ACTORS")
  def director = column[String]("MOVIE_DIRECTOR")
  def imageURL = column[String]("IMAGE_URL")
  def desc = column[String]("DESC")
  def * = (id, mName, year, genre, aRating, actors, director, imageURL, desc) <> (Movie.tupled, Movie.unapply)
}
