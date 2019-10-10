package sample.multinode

import akka.actor.Props
import akka.remote.testkit.{MultiNodeConfig, MultiNodeSpec}
import akka.testkit.ImplicitSender
import scala.concurrent.duration._
import example.TestActor


object MultiNodeSampleConfig extends MultiNodeConfig {
  val node1 = role("node1")
  val node2 = role("node2")
}

class MultiNodeSample extends MultiNodeSpec(MultiNodeSampleConfig) with STMultiNodeSpec with ImplicitSender {
  import MultiNodeSampleConfig._

  def initialParticipants = roles.size

  "A MultiNodeSample" must {
    "wait for all nodes to enter a barrier" in {
      enterBarrier("startup")
    }

    "send to and receive from a remote node" in {
      runOn(node1) {
        enterBarrier("deployed")
        val path = node(node2)
        println(path)
        val pActor = system.actorSelection(path / "user" / "test-actor-1")
        pActor ! TestActor.Command(20)
        expectMsg(10.seconds, TestActor.Response(21))
      }

      runOn(node2) {
        system.actorOf(Props[TestActor], "test-actor-1")
        enterBarrier("deployed")
      }

      enterBarrier("finished")
    }
  }
}

class SampleSpecMultiJvmNode1 extends MultiNodeSample
class SampleSpecMultiJvmNode2 extends MultiNodeSample