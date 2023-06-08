package views

import domain.{Mountains, PreviousPath, Rover, State}

trait Output {
   def deliver(frames: List[State]): Unit
}
