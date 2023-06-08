package controllers

import domain.{Command, Coordinates, Mountains, PreviousPath, Rover, State}

trait MovementController {
  def go(commands: List[Command]): List[State]
}
