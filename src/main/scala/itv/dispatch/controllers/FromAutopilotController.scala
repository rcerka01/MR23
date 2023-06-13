package itv.dispatch.controllers

import itv.dispatch.domain.{Command, Coordinates, Mountains, PreviousPath, Rover, State}

object FromAutopilotController extends MovementController[Any] {
  // TODO
  override def commandInterpreter(source: List[Any]): List[Command] = ???
}
