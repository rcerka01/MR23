import config.Config.*
import controllers.{FromAutopilotController, FromConfigController, MovementController}
import domain.{Coordinates, Direction, Mountains, PreviousPath, Rover}
import views.{Output, ToConsole}

object Main extends App {
  private val rover = Rover(Coordinates(startPositionXconf, startPositionYconf), startDirectionConf)
  private val mountains = Mountains(mountainsCoordConf)
  private val prevPath = PreviousPath(Nil)

  // from conf
  private val movementController: MovementController =
    new FromConfigController(
      rover = rover,
      prevPath = prevPath,
      mountains = mountains,
      output = ToConsole)
  movementController.go(commandsConf)

//  // from autopilot
//  private val target = Coordinates(5,5)
//  private val movementController: FromAutopilotController =
//    new FromAutopilotController(
//      rover = rover,
//      prevPath = prevPath,
//      mountains = mountains,
//      output = ToConsole)
//  movementController.go(movementController.calculateDirections(rover, target))

}
