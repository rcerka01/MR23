package controllers

import domain.{Command, Mountains, PreviousPath, Rover}
import views.Output

class FromKafkaController(
                           val rover: Rover,
                           val prevPath: PreviousPath,
                           val mountains: Mountains,
                           val output: Output
                         )
  extends MovementController {
  
  // TODO
  override def go(commands: List[Command]): Unit = println("FromKafkaController action")
}
