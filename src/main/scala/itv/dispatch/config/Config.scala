package itv.dispatch.config

import com.typesafe.config.{Config, ConfigFactory}
import itv.dispatch.domain.{Command, Coordinates, Direction, Mountains}

import scala.jdk.CollectionConverters.*
import java.util

object Config {
  private val config: Config = ConfigFactory.load()

  lazy val gridSizeX: Int = config.getInt("grid_size_x")
  lazy val gridSizeY: Int = config.getInt("grid_size_y")

  lazy val startPositionXconf: Int = config.getInt("start_position_x")
  lazy val startPositionYconf: Int = config.getInt("start_position_y")
  lazy val startDirectionConf: Direction = Direction.valueOf(config.getString("start_direction"))

  private lazy val mountainsConfigList: List[Config] = config.getConfigList("mountains").asScala.toList
  lazy val mountainsCoordConf: List[Coordinates] = mountainsConfigList.map { mountainConfig =>
    Coordinates(mountainConfig.getInt("x"), mountainConfig.getInt("y"))
  }

  lazy val commandsConfigList: List[String] = config.getStringList("commands").asScala.toList
}
