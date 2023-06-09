package itv.dispatch.controllers

import itv.dispatch.domain.{Command, State}

class FromKafkaController(state: State) extends MovementController {
  // TODO
  override def getCommands[A](source: List[A]): List[Command] = ???
}
