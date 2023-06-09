package itv.dispatch.controllers

import itv.dispatch.config.Config.commandsConfigList
import itv.dispatch.domain.{Command, Coordinates, Direction, Mountains, PreviousPath, Rover, State}

import scala.::

class CommandError(msg: String) extends Error(msg)

object FromConfigController extends MovementController {
  def getCommands[String](source: List[String]): List[Command] =
    commandsConfigList.map(Command.valueOf)
}
