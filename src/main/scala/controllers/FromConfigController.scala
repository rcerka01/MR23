package controllers

import domain.{Command, Mountains, PreviousPath, Rover, State}

class FromConfigController(state: State) extends MovementController {
  // TODO
  override def go(commands: List[Command]): List[State] = Nil
//    commands.foreach { command =>
//
//      println(command)
//
//    }
}
