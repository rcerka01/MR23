package itv.dispatch.kafka

import itv.dispatch.domain.Command
import itv.dispatch.domain.Command.Forward
import itv.dispatch.views.Output

import java.util.Properties
import org.apache.kafka.clients.producer.*
import org.apache.kafka.clients.consumer.*
import org.apache.kafka.common.serialization.{
  StringDeserializer,
  StringSerializer
}

import java.time.Duration
import java.util.concurrent.Future
import scala.jdk.CollectionConverters.*

class Kafka(topicName: String, bootstrapServers: String, groupId: String) {
  // producer
  private lazy val producer: KafkaProducer[String, String] = {
    val producerProperties = new Properties()
    producerProperties.put(
      ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
      bootstrapServers
    )
    producerProperties.put(
      ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      classOf[StringSerializer].getName
    )
    producerProperties.put(
      ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      classOf[StringSerializer].getName
    )
    new KafkaProducer[String, String](producerProperties)
  }

  def getProducer(): KafkaProducer[String, String] = producer

  def closeProducer(): Unit = {
    producer.close()
  }

  // consumer
  private val consumer = {
    val consumerProperties = new Properties()
    consumerProperties.put(
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
      bootstrapServers
    )
    consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
    consumerProperties.put(
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
      classOf[StringDeserializer].getName
    )
    consumerProperties.put(
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
      classOf[StringDeserializer].getName
    )
    new KafkaConsumer[String, String](consumerProperties)
  }

  def subscribe(): Unit = consumer.subscribe(List(topicName).asJava)

  def poll(prevalence: Duration): ConsumerRecords[String, String] =
    consumer.poll(prevalence)

  def closeConsumer(): Unit = {
    consumer.close()
  }
}
