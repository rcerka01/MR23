package itv.dispatch

import itv.dispatch.Context.*
import itv.dispatch.config.Config.*
import itv.dispatch.controllers.FromConfigController.{commandInterpreter, go}
import itv.dispatch.controllers.FromConsoleController.runConsoleInput
import itv.dispatch.controllers.*
import itv.dispatch.domain
import itv.dispatch.domain.Command.{Anticlockwise, Clockwise, Forward}
import itv.dispatch.domain.*
import itv.dispatch.views.{Output, ToConsole}

import java.time.Duration
import scala.concurrent.Future
import scala.io.StdIn
import scala.util.Random
import scala.util.Random.nextInt
import scala.util.control.Breaks.{break, breakable}
implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global


object Main {

  def mainMenu(initState: State): Unit = {
    println(
      "Enter input source: 1 for Config, 2 for Console, 3 for Kafka, 4 for Autopilot, q for exit"
    )
    val choice = StdIn.readLine().toLowerCase()
    // important to keep correct imports
    choice match {
      case "1" =>
        val commands: List[String] = commandsConfigList
        val moves: List[State] =
          FromConfigController.go(commandInterpreter(commands), initState)
        ToConsole(moves)
        mainMenu(moves.last)
      case "2" =>
        ToConsole(FromConsoleController.go(Nil, initState))
        runConsoleInput(initState)
        mainMenu(initState)
      case "3" =>
        kafka.subscribe()
        val pollFrequency = Duration.ofMillis(kafkaPollFrequencyMilliseconds)
        FromKafkaController.pollAndCommit(pollFrequency, ToConsole, initState)
        mainMenu(initState)
      case "4" =>
        println("autopilot")
        mainMenu(initState)
      case "q" =>
        kafka.closeProducer()
        kafka.closeConsumer()
        println("Exiting...")
      case _ =>
        println("Invalid choice!")
        mainMenu(initState)
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

    mainMenu(initState)
  }
}
