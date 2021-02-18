package Persistence.Domain

import play.api.data.Form
import play.api.data.Forms._

object SearchOBJ {

  case class SearchValue(term: String)

  val searchForm = {
    Form (
      mapping (
        "term" -> nonEmptyText
      )(SearchValue.apply)(SearchValue.unapply)
    )
  }

}
