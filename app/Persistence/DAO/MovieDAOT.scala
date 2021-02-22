package Persistence.DAO

import Persistence.Domain.BookingFormOBJ.Booking

import scala.concurrent.Future

trait MovieDAOT[T] {

  def readAll: Future[Seq[T]]
  def readById(id: Int): Future[Option[T]]
  def search(term: String): Future[Seq[T]]
  def totalPrice(booking: Booking): Future[BigDecimal]

}
