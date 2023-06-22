package itv.dispatch.domain

import cats.data.{NonEmptyList, Validated}
import cats.implicits.*

case class StateError(msg: String) extends Error(msg)

case class State(rover: Rover, previousPath: PreviousPath, mountains: Mountains)

object State {
  private def noRoverOnMountain(
      rover: Rover,
      mountains: Mountains
  ): Validated[NonEmptyList[StateError], Rover] = {
    val crossings = mountains.coordinates.filter(c =>
      c.x == rover.coordinates.x && c.y == rover.coordinates.y
    )
    if (crossings.nonEmpty)
      StateError("Move impossible, Rover can not step on mountain!").invalidNel
    else rover.validNel
  }

  private def noMountainsOnZeroCoordinate(
      mountains: Mountains
  ): Validated[NonEmptyList[StateError], Mountains] = {
    val zeroCoordinates =
      mountains.coordinates.filter(c => c.x == 0 || c.y == 0)
    if (zeroCoordinates.nonEmpty)
      StateError("No mountains can be with zero coordinates!").invalidNel
    else mountains.validNel
  }

  def validate(state: State): Validated[NonEmptyList[StateError], State] =
    (
      noRoverOnMountain(state.rover, state.mountains),
      state.previousPath.validNel,
      noMountainsOnZeroCoordinate(state.mountains)
    ).mapN((_, _, _) => state)
}
