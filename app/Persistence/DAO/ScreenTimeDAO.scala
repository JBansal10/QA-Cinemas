package Persistence.DAO

import Persistence.Domain.ScreenTimesOBJ.{ScreenTime, ScreenTimes}
import slick.jdbc.MySQLProfile.backend.Database
import slick.lifted.TableQuery

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.MySQLProfile.api._

object ScreenTimeDAO {

  lazy val db = Database.forConfig("mysqlDB")
  lazy val screenTable: TableQuery[ScreenTimes] = TableQuery[ScreenTimes]

  def readByMID(mId: Int): Future[Seq[ScreenTime]] = db.run(screenTable.filter(_.mID === mId).result)



}