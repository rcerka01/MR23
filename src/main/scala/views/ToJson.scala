package views

import domain.{Mountains, PreviousPath, Rover}

class ToJson extends Output {
  def deliver(rover: Rover, previousPath: PreviousPath, mountins: Mountains): Unit = ???
}
