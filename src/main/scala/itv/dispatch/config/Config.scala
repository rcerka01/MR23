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
  lazy val startDirectionConf: Direction =
    Direction.valueOf(config.getString("start_direction"))

  private lazy val mountainsConfigList: List[Config] =
    config.getConfigList("mountains").asScala.toList
  lazy val mountainsCoordConf: List[Coordinates] = mountainsConfigList.map {
    mountainConfig =>
      Coordinates(mountainConfig.getInt("x"), mountainConfig.getInt("y"))
  }

  lazy val commandsConfigList: List[String] =
    config.getStringList("commands").asScala.toList

  lazy val kafkaTopic: String = config.getString("kafka.topic-name")
  lazy val kafkaBootstrapServers: String =
    config.getString("kafka.bootstrap-servers")
  lazy val kafkaGroupId: String = config.getString("kafka.group-id")
  lazy val kafkaPollFrequencyMilliseconds: Int =
    config.getInt("kafka.poll-frequency-milliseconds")
}
