package itv.dispatch.controllers

import itv.dispatch.domain.{Command, State}

class FromKafkaController(state: State) extends MovementController[Any] {
  // TODO
  override def commandInterpreter(source: List[Any]): List[Command] = ???
}
