package views

import config.Config
import config.Config.{gridSizeX, gridSizeY}
import domain.{Coordinates, Mountains, PreviousPath, Rover, State}

object ToConsole extends Output {

  def deliver(frames: List[State]): Unit = {
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
            case _ if i == state.rover.coordinates.x && ii == state.rover.coordinates.y => print(roverSign)
            case _ if state.previousPath.coordinates.exists(c => c.x == ii && c.y == i) => print(prevPathSign)
            case _ if state.mountains.coordinates.exists(c => c.x == ii && c.y == i) => print(mountainSign)
            case _ => print(" ")
          }
        }
        println(verticalEdge)
      }
      println(horizontalEdge)
    }
  }
}
