package itv.dispatch.controllers

import itv.dispatch.domain.{Command, Coordinates, Direction, Mountains, PreviousPath, Rover, State}

trait MovementController[A] {
  def commandInterpreter(source: List[A]): List[Command]
  
  def positionRover(command: Command, rover: Rover): Rover =
    command match {
      case Command.Forward =>
        rover.direction match {
          case Direction.North =>
            rover.copy(coordinates =
              Coordinates(rover.coordinates.x, rover.coordinates.y + 1)
            )
          case Direction.West =>
            rover.copy(coordinates =
              Coordinates(rover.coordinates.x - 1, rover.coordinates.y)
            )
          case Direction.South =>
            rover.copy(coordinates =
              Coordinates(rover.coordinates.x, rover.coordinates.y - 1)
            )
          case Direction.East =>
            rover.copy(coordinates =
              Coordinates(rover.coordinates.x + 1, rover.coordinates.y)
            )
        }
      case Command.Clockwise =>
        rover.direction match {
          case Direction.North => rover.copy(direction = Direction.East)
          case Direction.East => rover.copy(direction = Direction.South)
          case Direction.South => rover.copy(direction = Direction.West)
          case Direction.West => rover.copy(direction = Direction.North)
        }
      case Command.Anticlockwise =>
        rover.direction match {
          case Direction.North => rover.copy(direction = Direction.West)
          case Direction.East => rover.copy(direction = Direction.North)
          case Direction.South => rover.copy(direction = Direction.East)
          case Direction.West => rover.copy(direction = Direction.South)
        }
    }

  def go(commands: List[Command], initState: State): List[State] =
    commands.foldLeft(List[State](initState))((acc, command) => {
      val newRover: Rover = positionRover(command, acc.last.rover)
      val newPrevPath: PreviousPath = acc.last.previousPath.copy(coordinates =
        acc.last.previousPath.coordinates :+ acc.last.rover.coordinates
      )
      acc :+ State(
        rover = newRover,
        previousPath = newPrevPath,
        mountains = acc.last.mountains
      )
    })
}
