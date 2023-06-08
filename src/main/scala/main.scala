import config.Config.*
import domain.Mountains.*
import domain.{Coordinates, Direction, PreviousPath, Rover}
import views.ToConsole

object Main extends App {
  println(commands)
  new ToConsole(
    rover = Rover(Coordinates(startPositionX, startPositionY), startDirection),
    previousPath =  PreviousPath(List(Coordinates(1,1), Coordinates(2,1))),
    mountins = mountains
  )
}