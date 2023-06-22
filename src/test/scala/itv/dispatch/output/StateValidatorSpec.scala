package itv.dispatch.output

import itv.dispatch.controllers.MovementController
import itv.dispatch.domain.Command.Forward
import itv.dispatch.domain.{Command, Coordinates, Direction, Mountains, PreviousPath, Rover, State}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.should

class StateValidatorSpec extends AnyFlatSpec with MovementController[Any] {

  "Validators" should "prevent from impossible moves"
  // rover
  it should "not step on mountain if it crosses on x coordinate" in {
    val testRover = Rover(
      coordinates = Coordinates(1, 1),
      direction = Direction.East
    )
    val testInitState = State(testRover, PreviousPath(Nil), Mountains(List(Coordinates(2, 1))))
    val resultStates = go(List(Forward), testInitState)
    assert(resultStates == List(testInitState))
  }
  it should "not step on mountain if it crosses on y coordinate" in {
    val testRover = Rover(
      coordinates = Coordinates(1, 1),
      direction = Direction.North
    )
    val testInitState = State(testRover, PreviousPath(Nil), Mountains(List(Coordinates(1, 2))))
    val resultStates = go(List(Forward), testInitState)
    assert(resultStates == List(testInitState))
  }

  "Validators" should "prevent from surrealistic mountains on zero coordinates"
  // mountains
  it should "not accept state with mountain on x coordinate" in {
    val testRover = Rover(
      coordinates = Coordinates(1, 1),
      direction = Direction.North
    )
    val testInitState = State(testRover, PreviousPath(Nil), Mountains(List(Coordinates(0, 5))))
    val resultStates = go(List(Forward), testInitState)
    assert(resultStates == List(testInitState))
  }
  it should "not accept state with mountain on y coordinate" in {
    val testRover = Rover(
      coordinates = Coordinates(1, 1),
      direction = Direction.North
    )
    val testInitState = State(testRover, PreviousPath(Nil), Mountains(List(Coordinates(5, 0))))
    val resultStates = go(List(Forward), testInitState)
    assert(resultStates == List(testInitState))
  }

  override def commandInterpreter(source: List[Any]): List[Command] = Nil
}
