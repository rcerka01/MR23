package itv.dispatch

import itv.dispatch.config.Config.{kafkaBootstrapServers, kafkaGroupId, kafkaTopic}
import itv.dispatch.kafka.Kafka

import java.time.Duration

object Context {
  val kafka = new Kafka(kafkaTopic, kafkaBootstrapServers, kafkaGroupId)
}
