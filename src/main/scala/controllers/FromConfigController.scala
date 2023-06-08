package controllers

import domain.{Command, Mountains, PreviousPath, Rover}
import views.Output

class FromConfigController(
                            val rover: Rover,
                            val prevPath: PreviousPath,
                            val mountains: Mountains,
                            val output: Output
                          )
  extends MovementController {
  
  // TODO
  override def go(commands: List[Command]): Unit = println("FromConfigController action")
}
