package itv.dispatch.controllers

import itv.dispatch.domain.Command.{Anticlockwise, Clockwise, Forward}
import itv.dispatch.domain.{Command, Direction, State}
import itv.dispatch.views.ToConsole

import scala.io.StdIn

object FromConsoleController extends MovementController[Command] {

  override def commandInterpreter(source: List[Command]): List[Command] = source

  def runConsoleInput(initState: State): Unit = {
    val movementController: MovementController[Command] = FromConsoleController
    val moves = movementController.go(Nil, initState)
    ToConsole(moves)

    println(
      "Enter your choice (f for Forward, c for Clockwise, a for Anticlockwise, q to quit):"
    )
    val command = StdIn.readLine().toLowerCase()
    command match {
      case "f" =>
        val action = commandInterpreter(List(Forward))
        val moves = go(action, initState)
        ToConsole(moves)
        runConsoleInput(moves.last)
      case "c" =>
        val action = commandInterpreter(List(Clockwise))
        val moves = go(action, initState)
        ToConsole(moves)
        runConsoleInput(moves.last)
      case "a" =>
        val action = commandInterpreter(List(Anticlockwise))
        val moves = go(action, initState)
        ToConsole(moves)
        runConsoleInput(moves.last)
      case "q" =>
        ()
      case _ =>
        println("Wrong command!")
        runConsoleInput(initState)
    }
  }
}
