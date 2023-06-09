package itv.dispatch.output

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funsuite.AnyFunSuite
import itv.dispatch.views
import itv.dispatch.views.ToConsole

class ToConsoleFormatersSpec extends AnyFlatSpec {

  val spaceX = 10
  val spaceY = 10

  "Format x coordinate" should "zero value on 0" in {
    assert(ToConsole.formatX(0, spaceX) == 0)
  }
  // positive
  it should "have value 1 at coordinate 1" in {
    assert(ToConsole.formatX(1, spaceX) == 1)
  }
  it should "have value 5 at coordinate 5" in {
    assert(ToConsole.formatX(5, spaceX) == 5)
  }
  it should "have value 9 at coordinate 9" in {
    assert(ToConsole.formatX(9, spaceX) == 9)
  }
  it should "have value 10 at coordinate 10" in {
    assert(ToConsole.formatX(10, spaceX) == 10)
  }
  it should "have value 1 at coordinate 11" in {
    assert(ToConsole.formatX(11, spaceX) == 1)
  }
  it should "have value 4 at coordinate 14" in {
    assert(ToConsole.formatX(14, spaceX) == 4)
  }
  it should "have value 9 at coordinate 19" in {
    assert(ToConsole.formatX(19, spaceX) == 9)
  }
  it should "have value 10 at coordinate 20" in {
    assert(ToConsole.formatX(20, spaceX) == 10)
  }
  it should "have value 1 at coordinate 21" in {
    assert(ToConsole.formatX(21, spaceX) == 1)
  }

  // negative
  it should "have value 10 at coordinate -1" in {
    assert(ToConsole.formatX(-1, spaceX) == 10)
  }
  it should "have value 7 at coordinate -4" in {
    assert(ToConsole.formatX(-4, spaceX) == 7)
  }
  it should "have value 2 at coordinate -9" in {
    assert(ToConsole.formatX(-9, spaceX) == 2)
  }
  it should "have value 1 at coordinate -10" in {
    assert(ToConsole.formatX(-10, spaceX) == 1)
  }
  it should "have value 10 at coordinate -11" in {
    assert(ToConsole.formatX(-11, spaceX) == 10)
  }
  it should "have value 5 at coordinate -16" in {
    assert(ToConsole.formatX(-16, spaceX) == 5)
  }
  it should "have value 2 at coordinate -19" in {
    assert(ToConsole.formatX(-19, spaceX) == 2)
  }
  it should "have value 1 at coordinate -20" in {
    assert(ToConsole.formatX(-20, spaceX) == 1)
  }
  it should "have value 10 at coordinate -21" in {
    assert(ToConsole.formatX(-21, spaceX) == 10)
  }

  "Format y coordinate" should "zero value on 0" in {
    assert(ToConsole.formatY(0, spaceX) == 0)
  }
  // positive
  it should "have value 9 at coordinate 1" in {
    assert(ToConsole.formatY(1, spaceX) == 10)
  }
  it should "have value 6 at coordinate 5" in {
    assert(ToConsole.formatY(5, spaceX) == 6)
  }
  it should "have value 2 at coordinate 9" in {
    assert(ToConsole.formatY(9, spaceX) == 2)
  }
  it should "have value 1 at coordinate 10" in {
    assert(ToConsole.formatY(10, spaceX) == 1)
  }
  it should "have value 10 at coordinate 11" in {
    assert(ToConsole.formatY(11, spaceX) == 10)
  }
  it should "have value 7 at coordinate 14" in {
    assert(ToConsole.formatY(14, spaceX) == 7)
  }
  it should "have value 2 at coordinate 19" in {
    assert(ToConsole.formatY(19, spaceX) == 2)
  }
  it should "have value 1 at coordinate 20" in {
    assert(ToConsole.formatY(20, spaceX) == 1)
  }
  it should "have value 10 at coordinate 21" in {
    assert(ToConsole.formatY(21, spaceX) == 10)
  }

  // negative
  it should "have value 1 at coordinate -1" in {
    assert(ToConsole.formatY(-1, spaceY) == 1)
  }
  it should "have value 4 at coordinate -4" in {
    assert(ToConsole.formatY(-4, spaceY) == 4)
  }
  it should "have value 9 at coordinate -9" in {
    assert(ToConsole.formatY(-9, spaceY) == 9)
  }
  it should "have value 10 at coordinate -10" in {
    assert(ToConsole.formatY(-10, spaceY) == 10)
  }
  it should "have value 1 at coordinate -11" in {
    assert(ToConsole.formatY(-11, spaceY) == 1)
  }
  it should "have value 6 at coordinate -16" in {
    assert(ToConsole.formatY(-16, spaceY) == 6)
  }
  it should "have value 9 at coordinate -19" in {
    assert(ToConsole.formatY(-19, spaceY) == 9)
  }
  it should "have value 10 at coordinate -20" in {
    assert(ToConsole.formatY(-20, spaceY) == 10)
  }
  it should "have value 1 at coordinate -21" in {
    assert(ToConsole.formatY(-21, spaceY) == 1)
  }
}
