import config.Config.*
import domain.Mountains.*
import domain.{Coordinates, Direction, PreviousPath, Rover}
import views.ToConsole

object Main extends App {
//  println(Config.gridSizeX)
//  println(mountains)
  new ToConsole(
    rover = Rover(Coordinates(startPositionX, startPositionY), Direction.South),
    previousPath =  PreviousPath(List(Coordinates(1,1), Coordinates(2,1))),
    mountins = mountains
  )
}