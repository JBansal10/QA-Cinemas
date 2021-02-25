package services

import Persistence.Domain.BookingFormOBJ.Bookings
import Persistence.Domain.DiscussionBoardOBJ.DiscussionBoards
import Persistence.Domain.Movies
import Persistence.Domain.ScreenTimesOBJ.ScreenTimes
import Persistence.Domain.paymentObj.{Payment, Payments}
import play.api.inject.ApplicationLifecycle
import slick.jdbc.MySQLProfile.api._

import javax.inject.Inject

class StartUpService @Inject()(lifecycle: ApplicationLifecycle)  {


  lazy val db = Database.forConfig("mysqlDB")
  val payment = TableQuery[Payments]
  val booking = TableQuery[Bookings]
  val discBoard = TableQuery[DiscussionBoards]
  val screenTime = TableQuery[ScreenTimes]
  val movie = TableQuery[Movies]

  val schema = payment.schema ++ booking.schema ++ discBoard.schema ++ screenTime.schema ++ movie.schema
  def aaa = DBIO.seq(schema.createIfNotExists)
  db.run(aaa)
}