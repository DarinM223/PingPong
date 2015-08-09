package d_m.remote

import akka.actor.{ActorSystem, Props}

/**
 * Created by darin on 8/8/15.
 */
object PingPongRemote extends App {
  import com.typesafe.config.ConfigFactory

  val config = ConfigFactory.load()
  val system = ActorSystem("PingPongRemote", config.getConfig("PingPongRemote"))

  val supervisor = system.actorOf(Props[Supervisor], "supervisor")
  println(config.getInt("akka.remote.netty.tcp.port"))
}
