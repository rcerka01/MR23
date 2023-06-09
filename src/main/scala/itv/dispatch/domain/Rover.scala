package itv.dispatch.domain

import itv.dispatch.config.Config.{gridSizeX, gridSizeY}

enum Direction(val value: Int):
  case North extends Direction(1)
  case East extends Direction(2)
  case South extends Direction(3)
  case West extends Direction(4)

case class Rover (coordinates: Coordinates, direction: Direction)
