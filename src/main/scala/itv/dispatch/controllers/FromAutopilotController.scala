package itv.dispatch.controllers

import itv.dispatch.domain.{Command, Coordinates, Mountains, PreviousPath, Rover, State}

object FromAutopilotController extends MovementController {
  // TODO
  override def getCommands[A](source: List[A]): List[Command] = ???
}
