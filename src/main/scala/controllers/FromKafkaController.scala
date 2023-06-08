package controllers

import domain.{Command, Mountains, PreviousPath, Rover, State}

class FromKafkaController(state: State) extends MovementController {
  // TODO
  override def go(commands: List[Command]): List[State] = Nil
}
