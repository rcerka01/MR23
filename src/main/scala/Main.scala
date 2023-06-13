import itv.dispatch.config.Config.{
  commandsConfigList,
  mountainsCoordConf,
  startDirectionConf,
  startPositionXconf,
  startPositionYconf
}
import itv.dispatch.controllers.FromConfigController.{commandInterpreter, go}
import itv.dispatch.controllers.FromConsoleController.runConsoleInput
import itv.dispatch.controllers.{
  FromAutopilotController,
  FromConfigController,
  FromConsoleController,
  MovementController
}
import itv.dispatch.domain
import itv.dispatch.domain.Command.{Anticlockwise, Clockwise, Forward}
import itv.dispatch.domain.{
  Command,
  Coordinates,
  Direction,
  Mountains,
  PreviousPath,
  Rover,
  State
}
import itv.dispatch.views.{Output, ToConsole}

import scala.io.StdIn

object Main {
  def runConsole(initState: State): Unit = {
    println(
      "Enter input source: 1 for Config, 2 for Console, 3 for Kafka, 4 for Autopilot, 5 for exit"
    )
    val choice = StdIn.readLine().toLowerCase()
    // important to keep correct imports
    choice match {
      case "1" =>
        val commands: List[String] = commandsConfigList
        val moves: List[State] = go(commandInterpreter(commands), initState)
        ToConsole(moves)
        runConsole(initState)
      case "2" =>
        runConsoleInput(initState)
        runConsole(initState)
      case "3" =>
        println("kafka")
        runConsole(initState)
      case "4" =>
        println("autopilot")
        runConsole(initState)
      case "5" =>
        println("Exiting...")
      case _ =>
        println("Invalid choice!")
        runConsole(initState)
    }
  }

  def main(args: Array[String]): Unit = {
    val rover = Rover(
      Coordinates(startPositionXconf, startPositionYconf),
      startDirectionConf
    )
    val mountains = domain.Mountains(mountainsCoordConf)
    val prevPath = PreviousPath(Nil)
    val initState = State(rover, prevPath, mountains)

    runConsole(initState)
  }
}
