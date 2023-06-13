package itv.dispatch.output

import itv.dispatch.controllers.MovementController
import itv.dispatch.domain.{
  Command,
  Coordinates,
  Direction,
  Mountains,
  PreviousPath,
  Rover,
  State
}
import org.scalatest.flatspec.AnyFlatSpec

class MovementControllerSpec extends AnyFlatSpec with MovementController[Any] {
  val testMountins = Mountains(List(Coordinates(3, 4), Coordinates(5, 5)))
  val testCommands =
    List(Command.Forward, Command.Clockwise, Command.Anticlockwise)

  "Movements" should "generate correct states"
  // from North
  it should "be correct for all commands from direction North" in {
    val testRoverNorth = Rover(
      coordinates = Coordinates(1, 1),
      direction = Direction.North
    )
    val testInitStateNorth =
      State(testRoverNorth, PreviousPath(Nil), testMountins)
    val resultStates = go(testCommands, testInitStateNorth)
    val expected = List(
      State(
        rover = testRoverNorth.copy(coordinates = Coordinates(1, 1)),
        PreviousPath(Nil),
        testMountins
      ),
      State(
        rover = testRoverNorth.copy(coordinates = Coordinates(1, 2)),
        PreviousPath(List(Coordinates(1, 1))),
        testMountins
      ),
      State(
        rover = testRoverNorth
          .copy(coordinates = Coordinates(1, 2), direction = Direction.East),
        PreviousPath(List(Coordinates(1, 1), Coordinates(1, 2))),
        testMountins
      ),
      State(
        rover = testRoverNorth
          .copy(coordinates = Coordinates(1, 2), direction = Direction.North),
        PreviousPath(
          List(Coordinates(1, 1), Coordinates(1, 2), Coordinates(1, 2))
        ),
        testMountins
      )
    )
    assert(resultStates == expected)
  }

  // from East
  it should "be correct for all commands from direction East" in {
    val testRoverEast = Rover(
      coordinates = Coordinates(1, 1),
      direction = Direction.East
    )
    val testInitStateEast =
      State(testRoverEast, PreviousPath(Nil), testMountins)
    val resultStates = go(testCommands, testInitStateEast)
    val expected = List(
      State(
        rover = testRoverEast,
        PreviousPath(Nil),
        testMountins
      ),
      State(
        rover = testRoverEast.copy(coordinates = Coordinates(2, 1)),
        PreviousPath(List(Coordinates(1, 1))),
        testMountins
      ),
      State(
        rover = testRoverEast
          .copy(coordinates = Coordinates(2, 1), direction = Direction.South),
        PreviousPath(List(Coordinates(1, 1), Coordinates(2, 1))),
        testMountins
      ),
      State(
        rover = testRoverEast
          .copy(coordinates = Coordinates(2, 1), direction = Direction.East),
        PreviousPath(
          List(Coordinates(1, 1), Coordinates(2, 1), Coordinates(2, 1))
        ),
        testMountins
      )
    )
    assert(resultStates == expected)
  }

  // from South
  it should "be correct for all commands from direction South" in {
    val testRoverSouth = Rover(
      coordinates = Coordinates(1, 1),
      direction = Direction.South
    )
    val testInitStateSouth =
      State(testRoverSouth, PreviousPath(Nil), testMountins)
    val resultStates = go(testCommands, testInitStateSouth)
    val expected = List(
      State(
        rover = testRoverSouth,
        PreviousPath(Nil),
        testMountins
      ),
      State(
        rover = testRoverSouth.copy(coordinates = Coordinates(1, 0)),
        PreviousPath(List(Coordinates(1, 1))),
        testMountins
      ),
      State(
        rover = testRoverSouth
          .copy(coordinates = Coordinates(1, 0), direction = Direction.West),
        PreviousPath(List(Coordinates(1, 1), Coordinates(1, 0))),
        testMountins
      ),
      State(
        rover = testRoverSouth
          .copy(coordinates = Coordinates(1, 0), direction = Direction.South),
        PreviousPath(
          List(Coordinates(1, 1), Coordinates(1, 0), Coordinates(1, 0))
        ),
        testMountins
      )
    )
    assert(resultStates == expected)
  }

  // from West
  it should "be correct for all commands from direction West" in {
    val testRoverWest = Rover(
      coordinates = Coordinates(1, 1),
      direction = Direction.West
    )
    val testInitStateWest =
      State(testRoverWest, PreviousPath(Nil), testMountins)
    val resultStates = go(testCommands, testInitStateWest)
    val expected = List(
      State(
        rover = testRoverWest,
        PreviousPath(Nil),
        testMountins
      ),
      State(
        rover = testRoverWest.copy(coordinates = Coordinates(0, 1)),
        PreviousPath(List(Coordinates(1, 1))),
        testMountins
      ),
      State(
        rover = testRoverWest
          .copy(coordinates = Coordinates(0, 1), direction = Direction.North),
        PreviousPath(List(Coordinates(1, 1), Coordinates(0, 1))),
        testMountins
      ),
      State(
        rover = testRoverWest
          .copy(coordinates = Coordinates(0, 1), direction = Direction.West),
        PreviousPath(
          List(Coordinates(1, 1), Coordinates(0, 1), Coordinates(0, 1))
        ),
        testMountins
      )
    )
    assert(resultStates == expected)
  }
  def commandInterpreter(source: List[Any]): List[Command] = Nil
}
