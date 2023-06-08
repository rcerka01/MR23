import config.Config.*
import controllers.{FromAutopilotController, FromConfigController, MovementController}
import domain.{Coordinates, Direction, Mountains, PreviousPath, Rover, State}
import views.{Output, ToConsole}

object Main extends App {
  private val rover = Rover(Coordinates(startPositionXconf, startPositionYconf), startDirectionConf)
  private val mountains = Mountains(mountainsCoordConf)
  private val prevPath = PreviousPath(Nil)

  private val initState  = State(rover, prevPath, mountains)

  // from conf
  private val movementController: MovementController = new FromConfigController(initState)
  ToConsole.deliver(movementController.go(commandsConf))

//  // from autopilot
//  private val target = Coordinates(5,5)
//  private val movementController: FromAutopilotController = new FromAutopilotController(initState)
//  ToConsole.deliver(movementController.go(movementController.calculateDirections(rover, target)))
}
