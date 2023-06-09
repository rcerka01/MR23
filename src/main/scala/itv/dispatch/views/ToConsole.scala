package itv.dispatch.views

import itv.dispatch.config.Config
import Config.{gridSizeX, gridSizeY}
import itv.dispatch.domain.{Coordinates, Mountains, PreviousPath, Rover, State}
import scala.math.*

object ToConsole extends Output {

  def formatX(coord: Int, space: Int) = {
    // for positive
    if (coord > 0) {
      if (coord > space)
        if (coord % space == 0) space
        else coord % space
      else coord
      // for negative
    } else if (coord < 0){
      if (abs(coord) < space) space - abs(coord) + 1
      else if (abs(coord) % space == 0) 1
      else abs(abs(coord) % space - space) + 1
      // for  zero
    } else
      0
  }

  def formatY(coord: Int, space: Int) = {
    // for positive
    if (coord > 0) {
      val pos =
        if (abs(coord) > space)
          if (abs(coord) % space == 0) space
          else abs(coord) % space
        else coord
      val correctY = abs(space - pos + 1)
      correctY
      // for negative
    } else {
      if (abs(coord) > space)
        if (abs(coord) % space == 0) space
        else abs(coord) % space
      else abs(coord)
    }
  }

  def apply(frames: List[State]): Unit = {
    val roverSign = "R"
    val mountainSign = "M"
    val prevPathSign = "*"

    val verticalEdge: String = "|"
    val horizontalEdge = " " + List.fill(gridSizeX)("-").mkString + " "

    frames.foreach { state =>
      println()
      println(s"Rover's direction is ${state.rover.direction}")
      println(horizontalEdge)
      for (i <- 1 to gridSizeY) {
        print(verticalEdge)
        for (ii <- 1 to gridSizeX) {
          (i, ii) match {
            case _
                if ii == formatX(
                  state.rover.coordinates.x,
                  gridSizeX
                ) && i == formatY(
                  state.rover.coordinates.y,
                  gridSizeY
                ) =>
              print(roverSign)
            case _
                if state.previousPath.coordinates
                  .exists(c =>
                    formatX(c.x, gridSizeX) == ii && formatY(
                      c.y,
                      gridSizeY
                    ) == i
                  ) =>
              print(prevPathSign)
            case _
                if state.mountains.coordinates
                  .exists(c =>
                    formatX(c.x, gridSizeX) == ii && formatY(
                      c.y,
                      gridSizeY
                    ) == i
                  ) =>
              print(mountainSign)
            case _ => print(" ")
          }
        }
        println(verticalEdge)
      }
      println(horizontalEdge)
    }
  }
}
