package itv.dispatch.controllers

import itv.dispatch.config.Config.commandsConfigList
import itv.dispatch.domain.{Command, Coordinates, Direction, Mountains, PreviousPath, Rover, State}

object FromConfigController extends MovementController[String] {
  def commandInterpreter(source: List[String]): List[Command] =
    commandsConfigList.map(Command.valueOf)
}
