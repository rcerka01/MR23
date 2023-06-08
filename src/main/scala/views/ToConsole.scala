package views

import config.Config
import config.Config.{gridSizeX, gridSizeY}
import domain.{Coordinates, Mountains, PreviousPath, Rover}

class ToConsole(val rover: Rover, previousPath: PreviousPath, mountins: Mountains) extends Output {
  val roverSign = "R"
  val mountainSign = "M"
  val prevPathSign = "*"

  val verticalEdge: String = "|"
  val horizontalEdge = " " + List.fill(gridSizeX)("-").mkString + " "

  println()
  println(s"Rover's direction is ${rover.direction}")
  println(horizontalEdge)
  for (i <- 1 to gridSizeY) {
    print(verticalEdge)
    for (ii <- 1 to gridSizeX) {
      (i, ii) match {
        case _ if i == rover.coordinates.x && ii == rover.coordinates.y => print(roverSign)
        case _ if previousPath.coordinates.exists( c => c.x == ii && c.y == i) => print(prevPathSign)
        case _ if mountins.coordinates.exists( c => c.x == ii && c.y == i) => print(mountainSign)
        case _ => print(" ")
      }
    }
    println(verticalEdge)
  }
  println(horizontalEdge)

}
