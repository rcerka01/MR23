package controllers

import domain.{Command, Coordinates, Mountains, PreviousPath, Rover}
import views.Output

class FromAutopilotController(
                               val rover: Rover,
                               val prevPath: PreviousPath,
                               val mountains: Mountains,
                               val output: Output
                             )
  extends MovementController {
  // TODO
  def calculateDirections(rover: Rover, target: Coordinates): List[Command] = Nil
  // TODO
  override def go(commands: List[Command]): Unit = println("FromAutopiloControllert action")
}
