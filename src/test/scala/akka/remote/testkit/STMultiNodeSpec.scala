package akka.remote.testkit

import scala.language.implicitConversions

import org.scalatest.{BeforeAndAfterAll, WordSpecLike}
import org.scalatest.Matchers


trait STMultiNodeSpec extends MultiNodeSpecCallbacks
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def beforeAll(): Unit = multiNodeSpecBeforeAll()

  override def afterAll(): Unit = multiNodeSpecAfterAll()
}

//trait STMultiNodeSpec extends MultiNodeSpecCallbacks with WordSpecLike with Matchers with BeforeAndAfterAll {
//  self: MultiNodeSpec =>
//
//  override def beforeAll() = multiNodeSpecBeforeAll()
//
//  override def afterAll() = multiNodeSpecAfterAll()
//
//  // Might not be needed anymore if we find a nice way to tag all logging from a node
//  override implicit def convertToWordSpecStringWrapper(s: String): WordSpecStringWrapper =
//    new WordSpecStringWrapper(s"$s (on node '${self.myself.name}', $getClass)")
//}