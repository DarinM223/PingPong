package d_m.local

import akka.actor._
import d_m.shared.Message

object PingPongLocal extends App {
  import com.typesafe.config.ConfigFactory

  val config = ConfigFactory.load()
  val system = ActorSystem("PingPongLocal", config.getConfig("PingPongLocal"))

  // Get remote supervisor
  val supervisor = system.actorSelection("akka.tcp://PingPongRemote@127.0.0.1:2552/user/supervisor")

  val printer = system.actorOf(Props[PingPongPrinter], "printer")

  // Listen to ping pong messages
  supervisor ! Message.Register(printer)

  // Remotely kick off the ping pong
  supervisor ! Message.Start
}
