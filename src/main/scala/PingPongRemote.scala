import akka.actor._

import java.io.File

object PingPongRemote extends App {
  import com.typesafe.config.ConfigFactory

  val config = ConfigFactory.load()
  val system = ActorSystem("PingPongRemote", config.getConfig("PingPongRemote"))

  val supervisor = system.actorOf(Props[Supervisor], "supervisor")
  println(config.getInt("akka.remote.netty.tcp.port"))
}
