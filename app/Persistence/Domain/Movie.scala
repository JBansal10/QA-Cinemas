package Persistence.Domain
import play.api.data.Form
import play.api.data.Forms._
import slick.jdbc.MySQLProfile.api._

import scala.collection.mutable.ArrayBuffer

case class Movie(id: Int, mName: String, year: Int, genre: String, aRating: String, actors: String, director: String, imageURL: String, desc: String, aPrice: BigDecimal, cPrice: BigDecimal)

case class Movies(tag: Tag) extends Table[Movie] (tag, "movie") {
  def id = column[Int]("MOVIE_ID", O.PrimaryKey, O.AutoInc)
  def mName = column[String]("MOVIE_NAME")
  def year = column[Int]("YEAR")
  def genre = column[String]("GENRE")
  def aRating = column[String]("AGE_RATING")
  def actors = column[String]("ACTORS")
  def director = column[String]("DIRECTOR")
  def imageURL = column[String]("IMAGE_URL")
  def desc = column[String]("DESC")
  def aPrice = column[BigDecimal]("ADULT_PRICE")
  def cPrice = column[BigDecimal]("CHILD_PRICE")

  def * = (id, mName, year, genre, aRating, actors, director, imageURL, desc, aPrice, cPrice) <> (Movie.tupled, Movie.unapply)
}
