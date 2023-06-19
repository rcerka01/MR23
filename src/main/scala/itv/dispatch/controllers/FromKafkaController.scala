package itv.dispatch.controllers

import itv.dispatch.domain.{Command, State}
import itv.dispatch.kafka.Kafka

import java.time.Duration
import scala.util.Random.*
import itv.dispatch.Context.*
import itv.dispatch.views.Output
import scala.annotation.tailrec

import scala.jdk.CollectionConverters.*
import org.apache.kafka.clients.producer.{
  Callback,
  KafkaProducer,
  ProducerRecord,
  RecordMetadata
}

object FromKafkaController extends MovementController[Command] {
  override def commandInterpreter(source: List[Command]): List[Command] = Nil

  def pollAndCommit(
      prevalence: Duration,
      output: Output,
      initState: State
  ): Unit = {
    @tailrec
    def pollAndCommitHelper(acc: List[State], currentState: State): Unit = {
      val records = kafka.poll(prevalence)
      if (records.isEmpty) {
        pollAndCommitHelper(acc, currentState)
      } else {
        val updatedStates = records.asScala.map { record =>
          val value = record.value()
          val command = Command.valueOf(value)
          val move = go(List(command), currentState)
          move.last
        }

        val updatedAcc = acc ::: updatedStates.toList
        output(List(updatedAcc.last))
        pollAndCommitHelper(updatedAcc, updatedStates.last)
      }
    }

    pollAndCommitHelper(List(initState), initState)
  }

// with var
//  def pollAndCommit(prevalence: Duration, output: Output, initState: State) = {
//    var acc: List[State] = List(initState)
//      while (true) {
//        val records = kafka.poll(prevalence)
//        for (record <- records.asScala) {
//          val value = record.value()
//          val command = Command.valueOf(value)
//          println(s"Kafka received command: $command")
//
//          val move = go(List(command), acc.last)
//          acc = acc :+ move.last
//          println(acc.length)
//          output(List(acc.last))
//        }
//      }
//    }
}
