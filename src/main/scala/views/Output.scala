package views

import domain.{Mountains, PreviousPath, Rover}

trait Output {
   def deliver(rover: Rover, previousPath: PreviousPath, mountins: Mountains): Unit
}
