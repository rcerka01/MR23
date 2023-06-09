package itv.dispatch.views

import itv.dispatch.domain.{Mountains, PreviousPath, Rover, State}

trait Output {
   def apply(frames: List[State]): Unit
}
