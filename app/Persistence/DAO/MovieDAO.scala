package Persistence.DAO

import Persistence.Domain.ScreenTimesOBJ.{ScreenTime, ScreenTimes}
import Persistence.Domain._
import slick.jdbc.MySQLProfile.backend.Database
import slick.lifted.TableQuery

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.MySQLProfile.api._

import scala.io.Source

object MovieDAO {

  lazy val db = Database.forConfig("mysqlDB")
  lazy val movieTable: TableQuery[Movies] = TableQuery[Movies]
  lazy val screenTimeTable = TableQuery[ScreenTimes]

  def readAll(): Future[Seq[Movie]] = db.run(movieTable.result)

  def readById(id: Int): Future[Option[Movie]] = db.run(movieTable.filter(_.id === id).result.headOption)

  def resetAll = {
    val statement = Source.fromFile("resources/test-data.sql").mkString
    db.run(sqlu"#$statement")
    println(statement)
  }

}
