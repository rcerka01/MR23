package controllers

import domain.{Command, Coordinates, Mountains, PreviousPath, Rover, State}

class FromAutopilotController(state: State) extends MovementController {
  // TODO
  def calculateDirections(rover: Rover, target: Coordinates): List[Command] = Nil
  // TODO
  override def go(commands: List[Command]): List[State] = Nil
}
