package itv.dispatch.controllers

import itv.dispatch.domain.{Command, State}

class FromConsoleController(state: State) extends MovementController {
  // TODO
  override def getCommands[A](source: List[A]): List[Command] = ???

  //  def readConsole(commands: List[Command], initState: State): List[State] = {
//    import scala.io.StdIn
//
//    def performAction(x: Int, y: Int): Unit = {
//      // Perform your desired action with x and y values
//      println(s"Performing action with X: $x, Y: $y")
//    }
//
//    def readLinesAndPerformAction(): Unit = {
//      var continue = true
//
//      while (continue) {
//        val input = StdIn.readLine()
//
//        input.split(" ") match {
//          case Array("exit") =>
//            continue = false
//          case Array(x, y) =>
//            try {
//              val xValue = x.toInt
//              val yValue = y.toInt
//              performAction(xValue, yValue)
//            } catch {
//              case _: NumberFormatException =>
//                println("Invalid input. Please enter valid X and Y values.")
//            }
//          case _ =>
//            println("Invalid input. Please enter X and Y values separated by a space.")
//        }
//      }
//    }
//
//    // Start reading lines and performing actions
//    readLinesAndPerformAction()
//    Nil
//  }
}