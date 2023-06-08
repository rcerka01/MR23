package controllers

import domain.{Command, Coordinates, Mountains, PreviousPath, Rover}
import views.Output

trait MovementController {
  val rover: Rover
  val prevPath: PreviousPath
  val mountains: Mountains
  val output: Output

  def go(commands: List[Command]): Unit
}
