package config

import com.typesafe.config.{Config, ConfigFactory}
import domain.{Command, Coordinates, Direction, Mountains}

import scala.jdk.CollectionConverters.*
import java.util

object Config {
  private val config: Config = ConfigFactory.load()

  lazy val gridSizeX: Int = config.getInt("grid_size_x")
  lazy val gridSizeY: Int = config.getInt("grid_size_y")

  lazy val startPositionX: Int = config.getInt("start_position_x")
  lazy val startPositionY: Int = config.getInt("start_position_y")
  lazy val startDirection: Direction = Direction.valueOf(config.getString("start_direction"))

  lazy val mountainsConfigList: List[Config] = config.getConfigList("mountains").asScala.toList
  lazy val mountainsCoordinates: List[Coordinates] = mountainsConfigList.map { mountainConfig =>
    Coordinates(mountainConfig.getInt("x"), mountainConfig.getInt("y"))
  }

  lazy val commandsConfigList: List[String] = config.getStringList("commands").asScala.toList
  lazy val commands: List[Command] = commandsConfigList.map(Command.valueOf)
}
