package d_m.remote

import akka.actor.{Actor, ActorRef, Props}
import d_m.shared.Message

class Supervisor extends Actor {
  import akka.actor.OneForOneStrategy
  import akka.actor.SupervisorStrategy._

  import scala.concurrent.duration._

  val pong = context.actorOf(Props(new Pong(self)), "pong")
  val ping = context.actorOf(Props(new Ping(pong, self)), "ping")

  var actors = List[ActorRef]()

  // Limit maximum number of restarts due to errors to 10
  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
      case _: Exception => Restart // always restart
    }

  def receive = {
    case Message.Start => ping ! Message.Start
    case Message.Register(actor) => actors = actor :: actors

    // Broadcast d_m.remote.Ping and d_m.remote.Pong messages to registered actors
    case Message.Ping => actors.foreach(_ ! Message.Ping)
    case Message.Pong => actors.foreach(_ ! Message.Pong)
  }
}
