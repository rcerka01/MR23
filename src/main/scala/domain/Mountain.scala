package domain

import config.Config.*

case class Mountains(coordinates: List[Coordinates])

object Mountains {
  val mountains = Mountains(mountainsCoordinates)
}
