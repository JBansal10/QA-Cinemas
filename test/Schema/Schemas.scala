package Schema

import Persistence.Domain.BookingFormOBJ.Bookings
import Persistence.Domain.DiscussionBoardOBJ.DiscussionBoards
import Persistence.Domain.Movies
import Persistence.Domain.ScreenTimesOBJ.ScreenTimes
import Persistence.Domain.paymentObj.{Payment, Payments}
import slick.jdbc.MySQLProfile.api._

object Schemas {

  lazy val db = Database.forConfig("mysqlDB")
  val payment = TableQuery[Payments]
  val booking = TableQuery[Bookings]
  val discBoard = TableQuery[DiscussionBoards]
  val screenTime = TableQuery[ScreenTimes]
  val movie = TableQuery[Movies]

  val schema = payment.schema ++ booking.schema ++ discBoard.schema ++ screenTime.schema ++ movie.schema
  def createDrop = DBIO.seq (
    schema.drop,
    schema.create
  )

  def insertData: DBIO[Unit] = DBIO.seq(
    sqlu"INSERT INTO movie values (1, 'Titanic', 123, 'Sad', 3, 'Fish probably', 'Jeff Bezos', 'titanic.png', 'Boat drowning', 4.95, 10.95, true)",
    sqlu"INSERT INTO movie values (2, 'Nemo', 432, 'Scary', 5, 'Fish', 'Steve Erwin', 'Nemo.png', 'Fish looking for fish', 8.95, 6.95, true)",
    sqlu"INSERT INTO movie values (3, 'Toy Story', 2000, 'Happy', 6, 'Toys', 'Guy Fieri', 'ToyStory.png', 'Toys running away from their owner and doing other stuff', 9.95, 7.95, true)",
    sqlu"INSERT INTO movie values (4, 'Transformers', 2002, 'Epic', 7, 'Robots', 'Human', 'Transformers.png', 'Robots fighting other robots and humans are present', 8.95, 7.95, true)",
    sqlu"INSERT INTO movie values (5, 'Sonic the movie', 2031, 'Fast', 4, 'The Pope', 'Some guys', 'sonic.jpg', 'Fast Hedgehog beating up an egg', 12.95, 8.45, false)",
    sqlu"INSERT INTO movie values (6, 'Minions', 2021, 'Witty', 3, 'Someone who directs movies', 'Not humans', 'minions.jpg', 'I dont know, havent seen it yet', 11.45, 9.60, false)",
    sqlu"INSERT INTO movie values (7, 'The Matrix 4', 2021, 'Action', 12, 'Lana Wachowski', 'Keanus Reeves', 'matrix-4.png', 'More matrix businesss', 12.15, 9.35, false)",
    sqlu"INSERT INTO movie values (8, 'Space Jam: A New Legacy', 2021, 'Family/Comedy', 2, 'Malcolm D. Lee', 'LeBron James and others', 'space-jam.jpg', 'Basketball moments', 12.15, 9.25, false)",

    sqlu"INSERT INTO screentime values(1, 1, 'Monday', '19:30', 'Standard')",
    sqlu"INSERT INTO screentime values(2, 1, 'Monday', '21:00', 'Standard')",
    sqlu"INSERT INTO screentime values(3, 1, 'Tuesday', '20:15', 'Deluxe')",
    sqlu"INSERT INTO screentime values(4, 1, 'Wednesday', '18:00', 'Standard')",
    sqlu"INSERT INTO screentime values(5, 1, 'Wednesday', '20:20', 'Deluxe')",
    sqlu"INSERT INTO screentime values(6, 1, 'Thursday', '21:00', 'Standard')",
    sqlu"INSERT INTO screentime values(7, 1, 'Friday', '18:00', 'Standard')",
    sqlu"INSERT INTO screentime values(8, 1, 'Friday', '20:00', 'Standard')",
    sqlu"INSERT INTO screentime values(9, 1, 'Friday', '21:45', 'Deluxe')",
    sqlu"INSERT INTO screentime values(10, 1, 'Saturday', '19:30', 'Deluxe')",
    sqlu"INSERT INTO screentime values(11, 1, 'Sunday', '18:15', 'Standard')",
    sqlu"INSERT INTO screentime values(12, 1, 'Sunday', '20:30', 'Standard')",

    sqlu"INSERT INTO screentime values(13, 2, 'Monday', '19:30', 'Standard')",
    sqlu"INSERT INTO screentime values(14, 2, 'Monday', '21:00', 'Standard')",
    sqlu"INSERT INTO screentime values(15, 2, 'Tuesday', '20:15', 'Deluxe')",
    sqlu"INSERT INTO screentime values(16, 2, 'Wednesday', '18:00', 'Standard')",
    sqlu"INSERT INTO screentime values(17, 2, 'Wednesday', '20:20', 'Deluxe')",
    sqlu"INSERT INTO screentime values(18, 2, 'Thursday', '21:00', 'Standard')",
    sqlu"INSERT INTO screentime values(19, 2, 'Friday', '18:00', 'Standard')",
    sqlu"INSERT INTO screentime values(20, 2, 'Friday', '20:00', 'Standard')",
    sqlu"INSERT INTO screentime values(21, 2, 'Friday', '21:45', 'Deluxe')",
    sqlu"INSERT INTO screentime values(22, 2, 'Saturday', '19:30', 'Deluxe')",
    sqlu"INSERT INTO screentime values(23, 2, 'Sunday', '18:15', 'Standard')",
    sqlu"INSERT INTO screentime values(24, 2, 'Sunday', '20:30', 'Standard')",

    sqlu"INSERT INTO screentime values(25, 3, 'Monday', '19:30', 'Standard')",
    sqlu"INSERT INTO screentime values(26, 3, 'Monday', '21:00', 'Standard')",
    sqlu"INSERT INTO screentime values(27, 3, 'Tuesday', '20:15', 'Deluxe')",
    sqlu"INSERT INTO screentime values(28, 3, 'Wednesday', '18:00', 'Standard')",
    sqlu"INSERT INTO screentime values(29, 3, 'Wednesday', '20:20', 'Deluxe')",
    sqlu"INSERT INTO screentime values(30, 3, 'Thursday', '21:00', 'Standard')",
    sqlu"INSERT INTO screentime values(31, 3, 'Friday', '18:00', 'Standard')",
    sqlu"INSERT INTO screentime values(32, 3, 'Friday', '20:00', 'Standard')",
    sqlu"INSERT INTO screentime values(33, 3, 'Friday', '21:45', 'Deluxe')",
    sqlu"INSERT INTO screentime values(34, 3, 'Saturday', '19:30', 'Deluxe')",
    sqlu"INSERT INTO screentime values(35, 3, 'Sunday', '18:15', 'Standard')",
    sqlu"INSERT INTO screentime values(36, 3, 'Sunday', '20:30', 'Standard')",

    sqlu"INSERT INTO screentime values(37, 4, 'Monday', '19:30', 'Standard')",
    sqlu"INSERT INTO screentime values(38, 4, 'Monday', '21:00', 'Standard')",
    sqlu"INSERT INTO screentime values(39, 4, 'Tuesday', '20:15', 'Deluxe')",
    sqlu"INSERT INTO screentime values(40, 4, 'Wednesday', '18:00', 'Standard')",
    sqlu"INSERT INTO screentime values(41, 4, 'Wednesday', '20:20', 'Deluxe')",
    sqlu"INSERT INTO screentime values(42, 4, 'Thursday', '21:00', 'Standard')",
    sqlu"INSERT INTO screentime values(43, 4, 'Friday', '18:00', 'Standard')",
    sqlu"INSERT INTO screentime values(44, 4, 'Friday', '20:00', 'Standard')",
    sqlu"INSERT INTO screentime values(45, 4, 'Friday', '21:45', 'Deluxe')",
    sqlu"INSERT INTO screentime values(46, 4, 'Saturday', '19:30', 'Deluxe')",
    sqlu"INSERT INTO screentime values(47, 4, 'Sunday', '18:15', 'Standard')",
    sqlu"INSERT INTO screentime values(48, 4, 'Sunday', '20:30', 'Standard')",

    sqlu"INSERT INTO booking values (1, '30/2/2021', 'Piers', 1, 1, 'Popcorn, HotDog', 1, 1)",
    sqlu"INSERT INTO booking values (2, '30/2/2021', 'Ayub', 1, 1, 'None', 1, 1)",
    sqlu"INSERT INTO booking values (3, '30/2/2021', 'Jake', 1, 1, 'Large Coke, Medium Popcorn', 1, 1)",
    sqlu"INSERT INTO booking values (4, '30/2/2021', 'Jas', 1, 1, 'Tango Ice Blast', 1, 1)",
    sqlu"INSERT INTO booking values (5, '30/2/2021', 'Simon', 1, 1, 'M&Ms', 1, 1)",
    sqlu"INSERT INTO booking values (6, '30/2/2021', 'Iqra', 1, 1, 'Minstrels', 1, 1)",

    sqlu"INSERT INTO discussionboard values (1, 'PCMBARBER', 'Oh dear oh dear oh dear... Talks of Leonardo getting an oscar for this are poor. Why dont we not just just leave this were we found it... At the bottom of the ocean!',  '17/02/2021 12:34', 1, 9, false)",
    sqlu"INSERT INTO discussionboard values (2, 'JDBRAC','Welp, this was interesting, certainly expected us to feel for the toys but wow... they are awful!', '17/02/2021 12:48', 3, 9, false)",
    sqlu"INSERT INTO discussionboard values (3, 'HIYA','Robot fighting, what is not to love?', '23/02/2021 15:48', 3, 9, true)",

    sqlu"INSERT INTO payment values (1, 'John', 'QWAFSHG', 'ARHAGEFSDA', 'JTREHGEGSGF', 1)"
  )


}
