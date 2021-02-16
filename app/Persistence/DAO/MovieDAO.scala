package Persistence.DAO

import Persistence.Domain._
import slick.jdbc.MySQLProfile.backend.Database
import slick.lifted.TableQuery

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.MySQLProfile.api._

object MovieDAO {

  lazy val db = Database.forConfig("mysqlDB")
  lazy val movieTable: TableQuery[Movies] = TableQuery[Movies]

  def readAll():Future[Seq[Movie]] = db.run(movieTable.result)

  def readById(id: Int): Future[Option[Movie]] = db.run(movieTable.filter(_.id === id).result.headOption)

}
