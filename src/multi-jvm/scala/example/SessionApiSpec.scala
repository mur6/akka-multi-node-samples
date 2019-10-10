package example

import org.scalatest.WordSpec
import org.scalatest.MustMatchers
import akka.remote.testkit.{MultiNodeConfig, STMultiNodeSpec}
import com.typesafe.config.ConfigFactory

object MultiNodeSampleConfig extends MultiNodeConfig {
  val seed = role("seed")
  val master = role("master")
  val worker1 = role("worker-1")
  val worker2 = role("worker-2")

  commonConfig(ConfigFactory.parseString(
    """
    akka.actor.provider="akka.cluster.ClusterActorRefProvider"
    """))
}

// # don't use sigar for tests, native lib not in path
// akka.cluster.metrics.collector-class = akka.cluster.JmxMetricsCollector
class WordsClusterSpec extends MultiNodeSpec(MultiNodeSampleConfig)
  with STMultiNodeSpec with ImplicitSender {

  import WordsClusterSpecConfig._

  def initialParticipants = roles.size

  val seedAddress = node(seed).address
  val masterAddress = node(master).address
  val worker1Address = node(worker1).address
  val worker2Address = node(worker2).address

  muteDeadLetters(classOf[Any])(system)
}

class SpecMultiJvmNode1 extends WordSpec with MustMatchers {
  "A node" should {
    "be able to say hello" in {
      val message = "Hello from node 1"
      message must be("Hello from node 1")
    }
  }
}

class SpecMultiJvmNode2 extends WordSpec with MustMatchers {
  "A node" should {
    "be able to say hello" in {
      val message = "Hello from node 2"
      message must be("Hello from node 2")
    }
  }
}