package itv.dispatch.output

import itv.dispatch.controllers.MovementController
import itv.dispatch.domain.{Command, Coordinates, Direction, Rover}
import org.scalatest.flatspec.AnyFlatSpec

class CommandInterpreterSpec extends AnyFlatSpec with MovementController[Any] {
  val rover = Rover(
    coordinates = Coordinates(1, 1),
    direction = Direction.North
  )

  "Command interpreter" should "correctly update rover coordinates"
  // Clockwise tests
  it should "have direction East at Clockwise move from North" in {
    val resultRover = positionRover(Command.Clockwise, rover)
    assert(resultRover.coordinates.x == 1)
    assert(resultRover.coordinates.y == 1)
    assert(resultRover.direction == Direction.East)
  }
  it should "have direction South at Clockwise move from East" in {
    val resultRover =
      positionRover(Command.Clockwise, rover.copy(direction = Direction.East))
    assert(resultRover.coordinates.x == 1)
    assert(resultRover.coordinates.y == 1)
    assert(resultRover.direction == Direction.South)
  }
  it should "have direction West at Clockwise move from South" in {
    val resultRover =
      positionRover(Command.Clockwise, rover.copy(direction = Direction.South))
    assert(resultRover.coordinates.x == 1)
    assert(resultRover.coordinates.y == 1)
    assert(resultRover.direction == Direction.West)
  }
  it should "have direction North at Clockwise move from West" in {
    val resultRover =
      positionRover(Command.Clockwise, rover.copy(direction = Direction.West))
    assert(resultRover.coordinates.x == 1)
    assert(resultRover.coordinates.y == 1)
    assert(resultRover.direction == Direction.North)
  }

  // Anticlockwise tests
  it should "have direction West at Clockwise move from North" in {
    val resultRover = positionRover(Command.Anticlockwise, rover)
    assert(resultRover.coordinates.x == 1)
    assert(resultRover.coordinates.y == 1)
    assert(resultRover.direction == Direction.West)
  }
  it should "have direction South at Clockwise move from West" in {
    val resultRover = positionRover(
      Command.Anticlockwise,
      rover.copy(direction = Direction.West)
    )
    assert(resultRover.coordinates.x == 1)
    assert(resultRover.coordinates.y == 1)
    assert(resultRover.direction == Direction.South)
  }
  it should "have direction East at Clockwise move from South" in {
    val resultRover = positionRover(
      Command.Anticlockwise,
      rover.copy(direction = Direction.South)
    )
    assert(resultRover.coordinates.x == 1)
    assert(resultRover.coordinates.y == 1)
    assert(resultRover.direction == Direction.East)
  }
  it should "have direction North at Clockwise move from East" in {
    val resultRover = positionRover(
      Command.Anticlockwise,
      rover.copy(direction = Direction.East)
    )
    assert(resultRover.coordinates.x == 1)
    assert(resultRover.coordinates.y == 1)
    assert(resultRover.direction == Direction.North)
  }

  // Forward
  it should "correctly move forward from North" in {
    val resultRover = positionRover(Command.Forward, rover)
    assert(resultRover.coordinates.x == 1)
    assert(resultRover.coordinates.y == 2)
    assert(resultRover.direction == Direction.North)
  }
  it should "correctly move forward from East" in {
    val resultRover =
      positionRover(Command.Forward, rover.copy(direction = Direction.East))
    assert(resultRover.coordinates.x == 2)
    assert(resultRover.coordinates.y == 1)
    assert(resultRover.direction == Direction.East)
  }
  it should "correctly move forward from South" in {
    val resultRover =
      positionRover(Command.Forward, rover.copy(direction = Direction.South))
    assert(resultRover.coordinates.x == 1)
    assert(resultRover.coordinates.y == 0)
    assert(resultRover.direction == Direction.South)
  }
  it should "correctly move forward from West" in {
    val resultRover =
      positionRover(Command.Forward, rover.copy(direction = Direction.West))
    assert(resultRover.coordinates.x == 0)
    assert(resultRover.coordinates.y == 1)
    assert(resultRover.direction == Direction.West)
  }

  override def commandInterpreter(source: List[Any]): List[Command] = Nil
}
