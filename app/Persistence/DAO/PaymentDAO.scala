package Persistence.DAO

import Persistence.Domain.paymentObj.{Payment, Payments}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.MySQLProfile.backend.Database
import slick.lifted.TableQuery

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object PaymentDAO {

  lazy val db = Database.forConfig("mysqlDB")
  lazy val paymentTable: TableQuery[Payments] = TableQuery[Payments]

  def create(pay: Payment): Future[String] = {
    db.run(paymentTable += pay).map(res => "payment details submitted successfully").recover{
      case ex: Exception =>
        ex.getCause.getMessage
    }
  }

  def getLastIndex(): Future[Int] = db.run(paymentTable.size.result)



}