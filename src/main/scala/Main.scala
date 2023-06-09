import itv.dispatch.config.Config.{
  commandsConfigList,
  mountainsCoordConf,
  startDirectionConf,
  startPositionXconf,
  startPositionYconf
}
import itv.dispatch.controllers.{
  FromAutopilotController,
  FromConfigController,
  MovementController
}
import itv.dispatch.domain
import itv.dispatch.domain.{
  Coordinates,
  Direction,
  Mountains,
  PreviousPath,
  Rover,
  State
}
import itv.dispatch.views.{Output, ToConsole}

object Main extends App {
  private val rover = Rover(
    Coordinates(startPositionXconf, startPositionYconf),
    startDirectionConf
  )
  private val mountains = domain.Mountains(mountainsCoordConf)
  private val prevPath = PreviousPath(Nil)
  private val initState = State(rover, prevPath, mountains)

  //ToConsole(List(initState))

  // from config
  private val movementController: MovementController = FromConfigController
  private val commands = movementController.getCommands(commandsConfigList)
  private val moves = movementController.go(commands, initState)
  ToConsole(moves)

}
