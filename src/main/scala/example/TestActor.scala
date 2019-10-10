package example

import akka.actor.{Actor, ActorLogging}
import akka.cluster.sharding.ShardRegion


object TestActor {
//  val extractEntityId: ShardRegion.ExtractEntityId = {
//    case cmd: Command => (cmd.id.toString, cmd)
//  }
//
//  def extractShardId(nrOfShards: Int): ShardRegion.ExtractShardId = {
//    case cmd: Command =>
//      (Math.abs(cmd.id.hashCode()) % nrOfShards).toString
//  }

  case class Command(value: Int)
  case class Response(value: Int)
}

class TestActor extends Actor with ActorLogging {
  import TestActor._

  override def receive: Receive = {
    case Command(id) =>
      sender() ! Response(id + 1)
  }
}
